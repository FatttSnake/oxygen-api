package top.fatweb.oxygen.api.component.security

import org.springframework.stereotype.Component
import top.fatweb.oxygen.api.component.storage.RedisProvider
import top.fatweb.oxygen.api.properties.ServerProperties
import java.security.SecureRandom
import java.util.UUID

/**
 * CSRF token manager
 *
 * Stores and validates CSRF tokens in Redis.
 * Used for cross-origin CSRF protection on token refresh endpoint.
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.2.0
 * @see RedisProvider
 * @see ServerProperties
 */
@Component
class CsrfTokenManager(
    private val redisProvider: RedisProvider,
    private val serverProperties: ServerProperties
) {
    private val secureRandom = SecureRandom()

    /**
     * Generate a CSRF token, store in Redis, and return it
     *
     * @param userId User ID to associate the token with
     * @return Generated CSRF token
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    fun generateToken(userId: Long): String {
        val token = secureRandom.nextLong().let { UUID(it, it).toString().replace("-", "") }
        val key = redisKey(userId)
        redisProvider.setObject(
            key = key,
            value = token,
            timeout = serverProperties.security.refreshTokenTtl,
            timeUnit = serverProperties.security.refreshTokenTtlUnit
        )
        return token
    }

    /**
     * Validate a CSRF token against Redis
     *
     * @param userId User ID associated with the token
     * @param token Token to validate
     * @return true if valid, false otherwise
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    fun validateToken(userId: Long, token: String): Boolean {
        val key = redisKey(userId)
        val stored = redisProvider.getObject<String>(key) ?: return false
        return stored == token
    }

    /**
     * Remove a CSRF token from Redis
     *
     * @param userId User ID to remove token for
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    fun removeToken(userId: Long) {
        redisProvider.delObject(redisKey(userId))
    }

    private fun redisKey(userId: Long) =
        "${serverProperties.security.tokenIssuer}_csrf_$userId"
}
