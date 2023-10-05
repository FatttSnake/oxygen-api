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
import java.util.concurrent.TimeUnit

@Service
class AuthenticationServiceImpl(
    private val authenticationManager: AuthenticationManager,
    private val redisUtil: RedisUtil
) : IAuthenticationService {
    override fun login(user: User): HashMap<String, String> {
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

        val hashMap = hashMapOf("token" to jwt)
        val redisKey = "${SecurityConstants.jwtIssuer}_login:" + jwt.substring(0, 32)
        redisUtil.setObject(redisKey, loginUser, 20, TimeUnit.MINUTES)

        return hashMap
    }

    override fun logout(token: String): Boolean =
        redisUtil.delObject("${SecurityConstants.jwtIssuer}_login:" + token.substring(0, 32))

    override fun renewToken(token: String): HashMap<String, String> {
        val oldRedisKey = "${SecurityConstants.jwtIssuer}_login:" + token.substring(0, 32)
        redisUtil.delObject(oldRedisKey)
        val jwt = JwtUtil.createJwt(WebUtil.getLoginUserId().toString())

        jwt ?: let {
            throw RuntimeException("Login failed")
        }

        val hashMap = hashMapOf("token" to jwt)
        val redisKey = "${SecurityConstants.jwtIssuer}_login:" + jwt.substring(0, 32)
        redisUtil.setObject(redisKey, WebUtil.getLoginUser(), 20, TimeUnit.MINUTES)

        return hashMap
    }
}