package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import top.fatweb.api.annotation.EventLogRecord
import top.fatweb.api.entity.permission.LoginUser
import top.fatweb.api.entity.permission.User
import top.fatweb.api.entity.system.EventLog
import top.fatweb.api.exception.TokenHasExpiredException
import top.fatweb.api.properties.SecurityProperties
import top.fatweb.api.service.permission.IAuthenticationService
import top.fatweb.api.service.permission.IUserService
import top.fatweb.api.util.JwtUtil
import top.fatweb.api.util.RedisUtil
import top.fatweb.api.util.WebUtil
import top.fatweb.api.vo.permission.LoginVo
import top.fatweb.api.vo.permission.TokenVo
import java.time.LocalDateTime
import java.time.ZoneOffset

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
    private val authenticationManager: AuthenticationManager,
    private val redisUtil: RedisUtil,
    private val userService: IUserService
) : IAuthenticationService {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @EventLogRecord(EventLog.Event.LOGIN)
    override fun login(request: HttpServletRequest, user: User): LoginVo {
        val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(user.username, user.password)
        val authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken)
        authentication ?: let {
            throw RuntimeException("Login failed")
        }

        val loginUser = authentication.principal as LoginUser
        loginUser.user.password = ""

        logger.info("用户登录 [用户名: '{}', IP: '{}']", user.username, request.remoteAddr)
        userService.update(User().apply {
            currentLoginIp = request.remoteAddr
            currentLoginTime = LocalDateTime.now(ZoneOffset.UTC)
            lastLoginIp = loginUser.user.currentLoginIp
            lastLoginTime = loginUser.user.currentLoginTime
        }, KtUpdateWrapper(User()).eq(User::username, user.username))

        val userId = loginUser.user.id.toString()
        val jwt = JwtUtil.createJwt(userId)

        jwt ?: let {
            throw RuntimeException("Login failed")
        }

        val redisKey = "${SecurityProperties.jwtIssuer}_login_${userId}:" + jwt
        redisUtil.setObject(redisKey, loginUser, SecurityProperties.redisTtl, SecurityProperties.redisTtlUnit)

        return LoginVo(jwt, loginUser.user.currentLoginTime, loginUser.user.currentLoginIp)
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
}