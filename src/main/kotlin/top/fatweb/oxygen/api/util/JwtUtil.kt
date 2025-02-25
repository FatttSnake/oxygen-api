package top.fatweb.oxygen.api.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import top.fatweb.oxygen.api.properties.SecurityProperties
import java.util.*
import java.util.concurrent.TimeUnit
import javax.crypto.spec.SecretKeySpec

/**
 * Jwt util
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object JwtUtil {
    private fun getUUID() = UUID.randomUUID().toString().replace("-", "")

    /**
     * Generate encrypted secret key
     *
     * @return Secret key
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    private fun generalKey(): SecretKeySpec {
        val encodeKey = Base64.getEncoder().encode(SecurityProperties.tokenSecret.toByteArray())
        return SecretKeySpec(encodeKey, 0, encodeKey.size, "AES")
    }

    private fun algorithm(): Algorithm = Algorithm.HMAC256(generalKey().toString())

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
        val nowDate = Date(nowMillis)
        val ttlMillis = ttlUnit.toMillis(ttl)
        val expMillis = nowMillis + ttlMillis
        val expDate = Date(expMillis)

        return JWT.create().withJWTId(uuid).withSubject(subject).withIssuer(SecurityProperties.tokenIssuer)
            .withIssuedAt(nowDate).withExpiresAt(expDate).sign(algorithm())
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