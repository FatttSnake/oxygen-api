package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import top.fatweb.api.constant.SecurityConstants
import top.fatweb.api.entity.permission.LoginUser
import top.fatweb.api.entity.permission.User
import top.fatweb.api.exception.TokenHasExpiredException
import top.fatweb.api.service.permission.IAuthenticationService
import top.fatweb.api.service.permission.IUserService
import top.fatweb.api.util.JwtUtil
import top.fatweb.api.util.RedisUtil
import top.fatweb.api.util.WebUtil
import top.fatweb.api.vo.authentication.LoginVo
import top.fatweb.api.vo.authentication.TokenVo
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class AuthenticationServiceImpl(
    private val authenticationManager: AuthenticationManager,
    private val redisUtil: RedisUtil,
    private val userService: IUserService
) : IAuthenticationService {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

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

        val redisKey = "${SecurityConstants.jwtIssuer}_login:" + jwt
        redisUtil.setObject(redisKey, loginUser, SecurityConstants.redisTtl, SecurityConstants.redisTtlUnit)

        return LoginVo(jwt, loginUser.user.currentLoginTime, loginUser.user.currentLoginIp)
    }

    override fun logout(token: String): Boolean = redisUtil.delObject("${SecurityConstants.jwtIssuer}_login:" + token)

    override fun renewToken(token: String): TokenVo {
        val loginUser = WebUtil.getLoginUser() ?: let { throw TokenHasExpiredException() }

        val oldRedisKey = "${SecurityConstants.jwtIssuer}_login:" + token
        redisUtil.delObject(oldRedisKey)
        val jwt = JwtUtil.createJwt(WebUtil.getLoginUserId().toString())

        jwt ?: let {
            throw RuntimeException("Login failed")
        }

        val redisKey = "${SecurityConstants.jwtIssuer}_login:" + jwt
        redisUtil.setObject(
            redisKey, loginUser, SecurityConstants.redisTtl, SecurityConstants.redisTtlUnit
        )

        return TokenVo(jwt)
    }
}