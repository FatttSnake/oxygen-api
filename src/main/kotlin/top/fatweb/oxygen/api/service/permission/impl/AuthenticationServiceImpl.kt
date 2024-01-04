package top.fatweb.oxygen.api.service.permission.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import jakarta.servlet.http.HttpServletRequest
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
import top.fatweb.oxygen.api.util.JwtUtil
import top.fatweb.oxygen.api.util.MailUtil
import top.fatweb.oxygen.api.util.RedisUtil
import top.fatweb.oxygen.api.util.WebUtil
import top.fatweb.oxygen.api.vo.permission.LoginVo
import top.fatweb.oxygen.api.vo.permission.RegisterVo
import top.fatweb.oxygen.api.vo.permission.TokenVo
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
 * @see AuthenticationManager
 * @see RedisUtil
 * @see IUserService
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
    override fun register(request: HttpServletRequest, registerParam: RegisterParam): RegisterVo {
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

        val loginVo = this.login(request, registerParam.username!!, registerParam.password!!)

        return RegisterVo(token = loginVo.token, userId = loginVo.userId)
    }

    @Transactional
    override fun resend() {
        val user = userService.getById(WebUtil.getLoginUserId()) ?: throw AccessDeniedException("Access Denied")

        user.verify ?: throw NoVerificationRequiredException()

        if (LocalDateTime.ofInstant(Instant.ofEpochMilli(user.verify!!.split("-").first().toLong()), ZoneOffset.UTC)
                .isAfter(LocalDateTime.now(ZoneOffset.UTC).minusMinutes(5))
        ) {
            throw RequestTooFrequent()
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
        val user = userService.getById(WebUtil.getLoginUserId()) ?: throw AccessDeniedException("Access Denied")
        user.verify ?: throw NoVerificationRequiredException()
        if (LocalDateTime.ofInstant(Instant.ofEpochMilli(user.verify!!.split("-").first().toLong()), ZoneOffset.UTC)
                .isBefore(LocalDateTime.now(ZoneOffset.UTC).minusHours(2)) || user.verify != verifyParam.code
        ) {
            throw VerificationCodeErrorOrExpiredException()
        }

        if (verifyParam.nickname.isNullOrBlank() || verifyParam.avatar.isNullOrBlank()) {
            throw AccountNeedInitException()
        }
        sensitiveWordService.checkSensitiveWord(verifyParam.nickname)

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
        user ?: let { throw UserNotFoundException() }

        user.forget?.let {
            if (LocalDateTime.ofInstant(Instant.ofEpochMilli(it.split("-").first().toLong()), ZoneOffset.UTC)
                    .isAfter(LocalDateTime.now(ZoneOffset.UTC).minusMinutes(5))
            ) {
                throw RequestTooFrequent()
            }
        }

        val code = "${
            LocalDateTime.now(ZoneOffset.UTC).toInstant(ZoneOffset.UTC).toEpochMilli()
        }-${UUID.randomUUID()}-${UUID.randomUUID()}-${UUID.randomUUID()}"
        userService.update(KtUpdateWrapper(User()).eq(User::id, user.id).set(User::forget, code))
        sendRetrieveMail(user.username!!, request.remoteAddr, code, forgetParam.email)
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

        sendPasswordChangedMail(user.username!!, request.remoteAddr, userInfo!!.email!!)
    }

    @EventLogRecord(EventLog.Event.LOGIN)
    override fun login(request: HttpServletRequest, loginParam: LoginParam): LoginVo {
        verifyCaptcha(loginParam.captchaCode!!)

        return this.login(request, loginParam.account!!, loginParam.password!!)
    }

    @EventLogRecord(EventLog.Event.LOGOUT)
    override fun logout(token: String): Boolean {
        val loginUser = WebUtil.getLoginUser() ?: let { throw TokenHasExpiredException() }

        return redisUtil.delObject("${SecurityProperties.jwtIssuer}_login_${loginUser.user.id}:" + token)
    }

    override fun renewToken(token: String): TokenVo {
        val loginUser = WebUtil.getLoginUser() ?: let { throw TokenHasExpiredException() }

        val oldRedisKey = "${SecurityProperties.jwtIssuer}_login_${loginUser.user.id}:" + token
        redisUtil.delObject(oldRedisKey)
        val jwt = JwtUtil.createJwt(WebUtil.getLoginUserId().toString())

        jwt ?: let {
            throw RuntimeException("Login failed")
        }

        val redisKey = "${SecurityProperties.jwtIssuer}_login_${loginUser.user.id}:" + jwt
        redisUtil.setObject(
            redisKey, loginUser, SecurityProperties.redisTtl, SecurityProperties.redisTtlUnit
        )

        return TokenVo(jwt)
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

    private fun login(request: HttpServletRequest, account: String, password: String): LoginVo {
        val usernamePasswordAuthenticationToken =
            UsernamePasswordAuthenticationToken(account, password)
        val authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken)
        authentication ?: let {
            throw RuntimeException("Login failed")
        }

        val loginUser = authentication.principal as LoginUser
        loginUser.user.password = ""

        logger.info("用户登录 [用户名: '{}', IP: '{}']", loginUser.username, request.remoteAddr)
        userService.update(User().apply {
            currentLoginIp = request.remoteAddr
            currentLoginTime = LocalDateTime.now(ZoneOffset.UTC)
            lastLoginIp = loginUser.user.currentLoginIp
            lastLoginTime = loginUser.user.currentLoginTime
        }, KtUpdateWrapper(User()).eq(User::username, loginUser.username))

        val userId = loginUser.user.id.toString()
        val jwt = JwtUtil.createJwt(userId)

        jwt ?: let {
            throw RuntimeException("Login failed")
        }

        val redisKey = "${SecurityProperties.jwtIssuer}_login_${userId}:" + jwt
        redisUtil.setObject(redisKey, loginUser, SecurityProperties.redisTtl, SecurityProperties.redisTtlUnit)

        return LoginVo(jwt, loginUser.user.id, loginUser.user.currentLoginTime, loginUser.user.currentLoginIp)
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