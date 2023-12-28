package top.fatweb.oxygen.api.util

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import top.fatweb.oxygen.api.entity.permission.LoginUser
import top.fatweb.oxygen.api.properties.SecurityProperties

/**
 * Web util
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object WebUtil {
    /**
     * Get the user currently calling api
     *
     * @return LoginUser object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LoginUser
     */
    fun getLoginUser(): LoginUser? = if (SecurityContextHolder.getContext().authentication.principal is String) null
    else SecurityContextHolder.getContext().authentication.principal as LoginUser

    /**
     * Get ID of the user currently calling api
     *
     * @return User ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun getLoginUserId(): Long? = getLoginUser()?.user?.id

    /**
     * Get username of the user currently calling api
     *
     * @return Username
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun getLoginUsername(): String? = getLoginUser()?.user?.username

    /**
     * Get token of the user currently calling api
     *
     * @return Token
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun getToken(tokenWithPrefix: String): String = tokenWithPrefix.removePrefix(SecurityProperties.tokenPrefix)

    /**
     * Get token of the user currently calling api
     *
     * @return Token
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun getToken(request: HttpServletRequest): String = getToken(request.getHeader(SecurityProperties.headerKey))

    /**
     * Offline user
     *
     * @param redisUtil RedisUtil object
     * @param userIds List of user IDs
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see RedisUtil
     */
    fun offlineUser(redisUtil: RedisUtil, vararg userIds: Long) {
        val keys = HashSet<String>()
        userIds.forEach {
            keys.addAll(redisUtil.keys("${SecurityProperties.jwtIssuer}_login_${it}:*"))
        }

        redisUtil.delObject(keys)
    }
}