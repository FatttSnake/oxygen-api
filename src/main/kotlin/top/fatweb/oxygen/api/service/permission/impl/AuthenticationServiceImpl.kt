package top.fatweb.oxygen.api.service.permission.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.annotation.EventLogRecord
import top.fatweb.oxygen.api.entity.permission.LoginUser
import top.fatweb.oxygen.api.entity.permission.User
import top.fatweb.oxygen.api.entity.permission.UserInfo
import top.fatweb.oxygen.api.entity.system.EventLog
import top.fatweb.oxygen.api.exception.*
import top.fatweb.oxygen.api.http.TurnstileApi
import top.fatweb.oxygen.api.param.permission.*
import top.fatweb.oxygen.api.properties.SecurityProperties
import top.fatweb.oxygen.api.service.api.v1.IAvatarService
import top.fatweb.oxygen.api.service.permission.IAuthenticationService
import top.fatweb.oxygen.api.service.permission.IUserInfoService
import top.fatweb.oxygen.api.service.permission.IUserService
import top.fatweb.oxygen.api.service.system.ISensitiveWordService
import top.fatweb.oxygen.api.settings.BaseSettings
import top.fatweb.oxygen.api.settings.SettingsOperator
import top.fatweb.oxygen.api.settings.TwoFactorSettings
import top.fatweb.oxygen.api.util.*
import top.fatweb.oxygen.api.vo.permission.LoginVo
import top.fatweb.oxygen.api.vo.permission.RegisterVo
import top.fatweb.oxygen.api.vo.permission.TokenVo
import top.fatweb.oxygen.api.vo.permission.TwoFactorVo
import java.io.StringWriter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

/**
 * Authentication service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see VelocityEngine
 * @see AuthenticationManager
 * @see PasswordEncoder
 * @see RedisUtil
 * @see TurnstileApi
 * @see IUserService
 * @see IUserInfoService
 * @see ISensitiveWordService
 * @see IAvatarService
 * @see IAuthenticationService
 */
@Service
class AuthenticationServiceImpl(
    private val velocityEngine: VelocityEngine,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder,
    private val redisUtil: RedisUtil,
    private val turnstileApi: TurnstileApi,
    private val userService: IUserService,
    private val userInfoService: IUserInfoService,
    private val sensitiveWordService: ISensitiveWordService,
    private val avatarService: IAvatarService
) : IAuthenticationService {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @EventLogRecord(EventLog.Event.REGISTER)
    @Transactional
    override fun register(
        request: HttpServletRequest,
        response: HttpServletResponse,
        registerParam: RegisterParam
    ): RegisterVo {
        verifyCaptcha(registerParam.captchaCode!!)
        sensitiveWordService.checkSensitiveWord(registerParam.username!!)

        val user = User().apply {
            username = registerParam.username
            password = passwordEncoder.encode(registerParam.password)
            verify =
                "${
                    LocalDateTime.now(ZoneOffset.UTC).toInstant(ZoneOffset.UTC).toEpochMilli()
                }-${UUID.randomUUID()}-${UUID.randomUUID()}-${UUID.randomUUID()}"
            locking = 0
            enable = 1
        }
        userService.save(user)
        userInfoService.save(UserInfo().apply {
            userId = user.id
            nickname = registerParam.username
            avatar = avatarService.randomBase64(null).base64
            email = registerParam.email
        })

        sendVerifyMail(user.username!!, user.verify!!, registerParam.email!!)

        val loginVo = this.login(
            request = request,
            response = response,
            account = registerParam.username!!,
            password = registerParam.password!!
        )

        return RegisterVo(
            refreshToken = loginVo.refreshToken,
            accessToken = loginVo.accessToken,
            userId = loginVo.userId
        )
    }

    @Transactional
    override fun resend() {
        val user = userService.getById(WebUtil.getLoginUserId())

        user.verify ?: throw NoVerificationRequiredException()

        if (LocalDateTime.ofInstant(Instant.ofEpochMilli(user.verify!!.split("-").first().toLong()), ZoneOffset.UTC)
                .isAfter(LocalDateTime.now(ZoneOffset.UTC).minusMinutes(5))
        ) {
            throw RequestTooFrequentException()
        }

        user.verify =
            "${
                LocalDateTime.now(ZoneOffset.UTC).toInstant(ZoneOffset.UTC).toEpochMilli()
            }-${UUID.randomUUID()}-${UUID.randomUUID()}-${UUID.randomUUID()}"
        user.updateTime = LocalDateTime.now(ZoneOffset.UTC)
        userService.updateById(user)

        WebUtil.getLoginUser()?.user?.userInfo?.email?.let {
            sendVerifyMail(user.username!!, user.verify!!, it)
        } ?: throw AccessDeniedException("Access Denied")
    }

    @EventLogRecord(EventLog.Event.VERIFY)
    @Transactional
    override fun verify(verifyParam: VerifyParam) {
        val user = userService.getById(WebUtil.getLoginUserId())
        user.verify ?: throw NoVerificationRequiredException()
        if (LocalDateTime.ofInstant(Instant.ofEpochMilli(user.verify!!.split("-").first().toLong()), ZoneOffset.UTC)
                .isBefore(LocalDateTime.now(ZoneOffset.UTC).minusHours(2)) || user.verify != verifyParam.code
        ) {
            throw VerificationCodeErrorOrExpiredException()
        }

        if (verifyParam.nickname.isNullOrBlank() || verifyParam.avatar.isNullOrBlank()) {
            throw AccountNeedInitException()
        }
        sensitiveWordService.checkSensitiveWord(verifyParam.nickname!!)

        userService.update(
            KtUpdateWrapper(User()).eq(User::id, user.id).set(User::verify, null)
                .set(User::updateTime, LocalDateTime.now(ZoneOffset.UTC))
        )
        userInfoService.update(
            KtUpdateWrapper(UserInfo()).eq(UserInfo::userId, user.id).set(UserInfo::nickname, verifyParam.nickname)
                .set(UserInfo::avatar, verifyParam.avatar)
        )
    }

    @Transactional
    override fun forget(request: HttpServletRequest, forgetParam: ForgetParam) {
        verifyCaptcha(forgetParam.captchaCode!!)

        val user = userService.getUserWithPowerByAccount(forgetParam.email!!)
        user ?: throw UserNotFoundException()

        user.forget?.let {
            if (LocalDateTime.ofInstant(Instant.ofEpochMilli(it.split("-").first().toLong()), ZoneOffset.UTC)
                    .isAfter(LocalDateTime.now(ZoneOffset.UTC).minusMinutes(5))
            ) {
                throw RequestTooFrequentException()
            }
        }

        val code = "${
            LocalDateTime.now(ZoneOffset.UTC).toInstant(ZoneOffset.UTC).toEpochMilli()
        }-${UUID.randomUUID()}-${UUID.randomUUID()}-${UUID.randomUUID()}"
        userService.update(KtUpdateWrapper(User()).eq(User::id, user.id).set(User::forget, code))
        sendRetrieveMail(user.username!!, WebUtil.getRequestIp(request), code, forgetParam.email!!)
    }

    @Transactional
    override fun retrieve(request: HttpServletRequest, retrieveParam: RetrieveParam) {
        verifyCaptcha(retrieveParam.captchaCode!!)

        val codeStrings = retrieveParam.code!!.split("-")
        if (codeStrings.size != 16) {
            throw RetrieveCodeErrorOrExpiredException()
        }
        try {
            if (LocalDateTime.ofInstant(Instant.ofEpochMilli(codeStrings.first().toLong()), ZoneOffset.UTC)
                    .isBefore(LocalDateTime.now(ZoneOffset.UTC).minusHours(2))
            ) {
                throw RetrieveCodeErrorOrExpiredException()
            }
        } catch (e: Exception) {
            throw RetrieveCodeErrorOrExpiredException()
        }

        val user = userService.getOne(KtQueryWrapper(User()).eq(User::forget, retrieveParam.code))
            ?: throw RetrieveCodeErrorOrExpiredException()
        val userInfo = userInfoService.getOne(KtQueryWrapper(UserInfo()).eq(UserInfo::userId, user.id))

        userService.update(
            KtUpdateWrapper(User()).eq(User::id, user.id).set(User::forget, null)
                .set(User::password, passwordEncoder.encode(retrieveParam.password!!))
        )

        WebUtil.offlineUser(redisUtil, user.id!!)

        sendPasswordChangedMail(user.username!!, WebUtil.getRequestIp(request), userInfo!!.email!!)
    }

    @EventLogRecord(EventLog.Event.LOGIN)
    override fun login(request: HttpServletRequest, response: HttpServletResponse, loginParam: LoginParam): LoginVo {
        if (loginParam.twoFactorCode.isNullOrBlank()) {
            verifyCaptcha(loginParam.captchaCode!!)
        }

        return this.login(
            request = request,
            response = response,
            account = loginParam.account!!,
            password = loginParam.password!!,
            twoFactorCode = loginParam.twoFactorCode
        )
    }

    override fun createTwoFactor(): TwoFactorVo {
        val user = userService.getById(WebUtil.getLoginUserId()) ?: throw UserNotFoundException()

        if (!user.twoFactor.isNullOrBlank() && !user.twoFactor!!.endsWith("?")) {
            throw AlreadyHasTwoFactorException()
        }

        val secretKey =
            TOTPUtil.generateSecretKey(SettingsOperator.getTwoFactorValue(TwoFactorSettings::secretKeyLength, 16))
        val qrCodeSVGBase64 = TOTPUtil.generateQRCodeSVGBase64(
            SettingsOperator.getTwoFactorValue(TwoFactorSettings::issuer, "OxygenToolbox"),
            user.username!!,
            secretKey
        )

        userService.update(KtUpdateWrapper(User()).eq(User::id, user.id).set(User::twoFactor, "${secretKey}?"))

        return TwoFactorVo(qrCodeSVGBase64)
    }

    override fun validateTwoFactor(twoFactorValidateParam: TwoFactorValidateParam): Boolean {
        val user = userService.getById(WebUtil.getLoginUserId()) ?: throw UserNotFoundException()
        if (user.twoFactor.isNullOrBlank()) {
            throw NoTwoFactorFoundException()
        }
        if (!user.twoFactor!!.endsWith("?")) {
            throw AlreadyHasTwoFactorException()
        }
        val secretKey = user.twoFactor!!.substring(0, user.twoFactor!!.length - 1)

        if (TOTPUtil.validateCode(secretKey, twoFactorValidateParam.code!!)) {
            userService.update(KtUpdateWrapper(User()).eq(User::id, user.id).set(User::twoFactor, secretKey))
            return true
        }

        return false
    }

    override fun removeTwoFactor(twoFactorRemoveParam: TwoFactorRemoveParam): Boolean {
        val user = userService.getById(WebUtil.getLoginUserId()) ?: throw UserNotFoundException()
        if (user.twoFactor.isNullOrBlank() || user.twoFactor!!.endsWith("?")) {
            throw NoTwoFactorFoundException()
        }

        if (TOTPUtil.validateCode(user.twoFactor!!, twoFactorRemoveParam.code!!)) {
            userService.update(KtUpdateWrapper(User()).eq(User::id, user.id).set(User::twoFactor, null))
            return true
        }

        return false
    }

    @EventLogRecord(EventLog.Event.LOGOUT)
    override fun logout(token: String): Boolean {
        var redisKeyPattern = "${SecurityProperties.tokenIssuer}_access_*:${token}"
        var redisKeys = redisUtil.keys(redisKeyPattern)
        if (redisKeys.isEmpty()) {
            return false
        }
        redisUtil.delObject(redisKeys)

        val refreshToken = Regex("${SecurityProperties.tokenIssuer}_access_.*?_(.*):.*").matchEntire(redisKeys.first())?.groupValues?.getOrNull(1)
        redisKeyPattern = "${SecurityProperties.tokenIssuer}_token_*:${refreshToken}"
        redisKeys = redisUtil.keys(redisKeyPattern)
        if (redisKeys.isEmpty()) {
            return false
        }
        redisUtil.delObject(redisKeys)

        return true
    }

    override fun refreshToken(response: HttpServletResponse, refreshToken: String?): TokenVo {
        refreshToken ?: throw TokenRefreshErrorException()
        JwtUtil.parseJwt(refreshToken)

        var redisKeyPattern = "${SecurityProperties.tokenIssuer}_token_*:${refreshToken}"
        var redisKeys = redisUtil.keys(redisKeyPattern)
        if (redisKeys.isEmpty()) {
            throw TokenHasExpiredException()
        }

        val loginUser = redisUtil.getObject<LoginUser>(redisKeys.first()) ?: throw TokenHasExpiredException()
        val userId = loginUser.user.id.toString()
        val newRefreshToken = JwtUtil.generateRefreshToken(userId) ?: throw TokenRefreshErrorException()
        val newAccessToken = JwtUtil.generateAccessToken(userId) ?: throw TokenRefreshErrorException()

        var redisKey = "${SecurityProperties.tokenIssuer}_token_${userId}:${newRefreshToken}"
        redisUtil.setObject(
            key = redisKey,
            value = loginUser,
            timeout = SecurityProperties.refreshTokenTtl,
            timeUnit = SecurityProperties.refreshTokenTtlUnit
        )
        redisKey = "${SecurityProperties.tokenIssuer}_access_${userId}_${newRefreshToken}:${newAccessToken}"
        redisUtil.setObject(
            key = redisKey,
            value = loginUser,
            timeout = SecurityProperties.accessTokenTtl,
            timeUnit = SecurityProperties.accessTokenTtlUnit
        )

        val cookie = Cookie("refresh_token", newRefreshToken).apply {
            path = "/token"
            maxAge = SecurityProperties.refreshTokenTtlUnit.toSeconds(SecurityProperties.refreshTokenTtl).toInt()
            isHttpOnly = true
        }
        response.addCookie(cookie)

        redisUtil.delObject(redisKeys)
        redisKeyPattern = "${SecurityProperties.tokenIssuer}_access_*_${refreshToken}:*"
        redisKeys = redisUtil.keys(redisKeyPattern)
        redisUtil.delObject(redisKeys)

        return TokenVo(
            refreshToken = newRefreshToken,
            accessToken = newAccessToken,
        )
    }

    private fun sendVerifyMail(username: String, code: String, email: String) {
        val velocityContext = VelocityContext().apply {
            put("appName", SettingsOperator.getAppValue(BaseSettings::appName, "氧工具"))
            put("appUrl", SettingsOperator.getAppValue(BaseSettings::appUrl, "http://localhost"))
            put("username", username)
            put(
                "verifyUrl",
                SettingsOperator.getAppValue(BaseSettings::verifyUrl, "http://localhost/verify?code=\${verifyCode}")
                    .replace(
                        Regex("(?<=([^\\\\]))\\$\\{verifyCode}"), code
                    )
            )
        }
        val template = velocityEngine.getTemplate("templates/email-verify-account-cn.vm")

        val stringWriter = StringWriter()
        template.merge(velocityContext, stringWriter)

        MailUtil.sendSimpleMail(
            "验证您的账号", stringWriter.toString(), true,
            email
        )
    }

    private fun sendRetrieveMail(username: String, ip: String, code: String, email: String) {
        val velocityContext = VelocityContext().apply {
            put("appName", SettingsOperator.getAppValue(BaseSettings::appName, "氧工具"))
            put("appUrl", SettingsOperator.getAppValue(BaseSettings::appUrl, "http://localhost"))
            put("username", username)
            put("ipAddress", ip)
            put(
                "retrieveUrl",
                SettingsOperator.getAppValue(
                    BaseSettings::retrieveUrl,
                    "http://localhost/retrieve?code=\${retrieveCode}"
                )
                    .replace(
                        Regex("(?<=([^\\\\]))\\$\\{retrieveCode}"), code
                    )
            )
        }
        val template = velocityEngine.getTemplate("templates/email-retrieve-password-cn.vm")

        val stringWriter = StringWriter()
        template.merge(velocityContext, stringWriter)

        MailUtil.sendSimpleMail(
            "找回您的密码", stringWriter.toString(), true,
            email
        )
    }

    private fun sendPasswordChangedMail(username: String, ip: String, email: String) {
        val velocityContext = VelocityContext().apply {
            put("appName", SettingsOperator.getAppValue(BaseSettings::appName, "氧工具"))
            put("appUrl", SettingsOperator.getAppValue(BaseSettings::appUrl, "http://localhost"))
            put("username", username)
            put("ipAddress", ip)
        }
        val template = velocityEngine.getTemplate("templates/email-password-changed-cn.vm")

        val stringWriter = StringWriter()
        template.merge(velocityContext, stringWriter)

        MailUtil.sendSimpleMail(
            "您的密码已更改", stringWriter.toString(), true,
            email
        )
    }

    private fun login(
        request: HttpServletRequest,
        response: HttpServletResponse,
        account: String,
        password: String,
        twoFactorCode: String? = null
    ): LoginVo {
        val usernamePasswordAuthenticationToken =
            UsernamePasswordAuthenticationToken(account, password)
        val authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken) ?: throw LoginFailedException()

        val loginUser = authentication.principal as LoginUser
        loginUser.user.password = ""

        if (!loginUser.user.twoFactor.isNullOrBlank() && !loginUser.user.twoFactor!!.endsWith("?")) {
            if (twoFactorCode.isNullOrBlank()) {
                throw NeedTwoFactorException()
            }
            if (!TOTPUtil.validateCode(loginUser.user.twoFactor!!, twoFactorCode)) {
                throw TwoFactorVerificationCodeErrorException()
            }
        }

        logger.info("用户登录 [用户名: '{}', IP: '{}']", loginUser.username, WebUtil.getRequestIp(request))
        userService.update(User().apply {
            currentLoginIp = WebUtil.getRequestIp(request)
            currentLoginTime = LocalDateTime.now(ZoneOffset.UTC)
            lastLoginIp = loginUser.user.currentLoginIp
            lastLoginTime = loginUser.user.currentLoginTime
        }, KtUpdateWrapper(User()).eq(User::username, loginUser.username))

        val userId = loginUser.user.id.toString()
        val refreshToken = JwtUtil.generateRefreshToken(userId) ?: throw LoginFailedException()
        val accessToken = JwtUtil.generateAccessToken(userId) ?: throw LoginFailedException()

        var redisKey = "${SecurityProperties.tokenIssuer}_token_${userId}:${refreshToken}"
        redisUtil.setObject(
            key = redisKey,
            value = loginUser,
            timeout = SecurityProperties.refreshTokenTtl,
            timeUnit = SecurityProperties.refreshTokenTtlUnit
        )
        redisKey = "${SecurityProperties.tokenIssuer}_access_${userId}_${refreshToken}:${accessToken}"
        redisUtil.setObject(
            key = redisKey,
            value = loginUser,
            timeout = SecurityProperties.accessTokenTtl,
            timeUnit = SecurityProperties.accessTokenTtlUnit
        )

        val cookie = Cookie("refresh_token", refreshToken).apply {
            path = "/token"
            maxAge = SecurityProperties.refreshTokenTtlUnit.toSeconds(SecurityProperties.refreshTokenTtl).toInt()
            isHttpOnly = true
        }
        response.addCookie(cookie)

        return LoginVo(
            refreshToken = refreshToken,
            accessToken = accessToken,
            userId = loginUser.user.id,
            lastLoginTime = loginUser.user.currentLoginTime,
            lastLoginIp = loginUser.user.currentLoginIp
        )
    }

    private fun verifyCaptcha(captchaCode: String) {
        try {
            val siteverifyResponse = turnstileApi.siteverify(captchaCode)
            if (!siteverifyResponse.success) {
                throw InvalidCaptchaCodeException()
            }
        } catch (e: Exception) {
            throw InvalidCaptchaCodeException()
        }
    }
}