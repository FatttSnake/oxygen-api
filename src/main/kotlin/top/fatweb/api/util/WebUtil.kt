package top.fatweb.api.util

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import top.fatweb.api.entity.permission.LoginUser
import top.fatweb.api.properties.SecurityProperties

/**
 * Web util
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object WebUtil {
    fun getLoginUser() = if (SecurityContextHolder.getContext().authentication.principal is String) null
    else SecurityContextHolder.getContext().authentication.principal as LoginUser

    fun getLoginUserId() = getLoginUser()?.user?.id

    fun getLoginUsername() = getLoginUser()?.user?.username

    fun getToken(tokenWithPrefix: String) = tokenWithPrefix.removePrefix(SecurityProperties.tokenPrefix)

    fun getToken(request: HttpServletRequest) = getToken(request.getHeader(SecurityProperties.headerString))

    fun offlineUser(redisUtil: RedisUtil, vararg userIds: Long) {
        val keys = HashSet<String>()
        userIds.forEach {
            keys.addAll(redisUtil.keys("${SecurityProperties.jwtIssuer}_login_${it}:*"))
        }

        redisUtil.delObject(keys)
    }
}