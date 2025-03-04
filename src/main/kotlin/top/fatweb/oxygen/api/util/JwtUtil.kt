package top.fatweb.oxygen.api.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import top.fatweb.oxygen.api.properties.SecurityProperties
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Jwt util
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object JwtUtil {
    private fun getUUID() = UUID.randomUUID().toString().replace("-", "")

    private fun algorithm(): Algorithm = Algorithm.HMAC256(SecurityProperties.tokenSecret)

    /**
     * Generate access token
     *
     * @param subject Data stored in token (json format)
     * @return Access token
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    fun generateAccessToken(
        subject: String
    ) = generateJwt(
        subject = subject,
        ttl = SecurityProperties.accessTokenTtl,
        ttlUnit = SecurityProperties.accessTokenTtlUnit
    )

    /**
     * Generate refresh token
     *
     * @param subject Data stored in token (json format)
     * @return Refresh token
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    fun generateRefreshToken(
        subject: String
    ) = generateJwt(
        subject = subject,
        ttl = SecurityProperties.refreshTokenTtl,
        ttlUnit = SecurityProperties.refreshTokenTtlUnit
    )

    /**
     * Generate jwt token
     *
     * @param subject Data stored in token (json format)
     * @param ttl Life of token
     * @param ttlUnit Life util of token
     * @param uuid UUID
     * @return JWT token
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see TimeUnit
     */
    private fun generateJwt(
        subject: String,
        ttl: Long,
        ttlUnit: TimeUnit,
        uuid: String = getUUID()
    ): String? {
        val nowMillis = System.currentTimeMillis()
        val expMillis = nowMillis + ttlUnit.toMillis(ttl)

        return JWT.create().withJWTId(uuid).withSubject(subject).withIssuer(SecurityProperties.tokenIssuer)
            .withIssuedAt(Date(nowMillis)).withExpiresAt(Date(expMillis)).sign(algorithm())
    }

    /**
     * Parse JWT token
     *
     * @param jwt JWT token
     * @return Parsed content
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see DecodedJWT
     */
    fun parseJwt(jwt: String): DecodedJWT = JWT.require(algorithm()).build().verify(jwt)
}