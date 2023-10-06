package top.fatweb.api.service.permission.impl

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import top.fatweb.api.constant.SecurityConstants
import top.fatweb.api.entity.permission.LoginUser
import top.fatweb.api.entity.permission.User
import top.fatweb.api.service.permission.IAuthenticationService
import top.fatweb.api.util.JwtUtil
import top.fatweb.api.util.RedisUtil
import top.fatweb.api.util.WebUtil
import top.fatweb.api.vo.LoginVo
import top.fatweb.api.vo.TokenVo

@Service
class AuthenticationServiceImpl(
    private val authenticationManager: AuthenticationManager,
    private val redisUtil: RedisUtil
) : IAuthenticationService {
    override fun login(user: User): LoginVo {
        val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(user.username, user.password)
        val authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken)
        authentication ?: let {
            throw RuntimeException("Login failed")
        }

        val loginUser = authentication.principal as LoginUser
        loginUser.user.password = ""
        val userId = loginUser.user.id.toString()
        val jwt = JwtUtil.createJwt(userId)

        jwt ?: let {
            throw RuntimeException("Login failed")
        }

        val redisKey = "${SecurityConstants.jwtIssuer}_login:" + jwt
        redisUtil.setObject(redisKey, loginUser, SecurityConstants.redisTtl, SecurityConstants.redisTtlUnit)

        return LoginVo(jwt)
    }

    override fun logout(token: String): Boolean =
        redisUtil.delObject("${SecurityConstants.jwtIssuer}_login:" + token)

    override fun renewToken(token: String): TokenVo {
        val oldRedisKey = "${SecurityConstants.jwtIssuer}_login:" + token
        redisUtil.delObject(oldRedisKey)
        val jwt = JwtUtil.createJwt(WebUtil.getLoginUserId().toString())

        jwt ?: let {
            throw RuntimeException("Login failed")
        }

        val redisKey = "${SecurityConstants.jwtIssuer}_login:" + jwt
        redisUtil.setObject(
            redisKey,
            WebUtil.getLoginUser(),
            SecurityConstants.redisTtl,
            SecurityConstants.redisTtlUnit
        )

        return TokenVo(jwt)
    }
}