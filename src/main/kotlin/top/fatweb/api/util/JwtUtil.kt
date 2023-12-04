package top.fatweb.api.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import top.fatweb.api.properties.SecurityProperties
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
        val encodeKey = Base64.getDecoder().decode(SecurityProperties.jwtKey)
        return SecretKeySpec(encodeKey, 0, encodeKey.size, "AES")
    }

    private fun algorithm(): Algorithm = Algorithm.HMAC256(generalKey().toString())

    /**
     * Create token
     *
     * @param subject Data stored in token (json format)
     * @param ttl TTL of token
     * @param timeUnit TTL unit of token
     * @param uuid UUID
     * @return JWT token
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see TimeUnit
     */
    fun createJwt(
        subject: String,
        ttl: Long = SecurityProperties.jwtTtl,
        timeUnit: TimeUnit = SecurityProperties.jwtTtlUnit,
        uuid: String = getUUID()
    ): String? {
        val nowMillis = System.currentTimeMillis()
        val nowDate = Date(nowMillis)
        val unitTtl = (ttl * when (timeUnit) {
            TimeUnit.DAYS -> 24 * 60 * 60 * 1000
            TimeUnit.HOURS -> 60 * 60 * 1000
            TimeUnit.MINUTES -> 60 * 1000
            TimeUnit.SECONDS -> 1000
            TimeUnit.MILLISECONDS -> 1
            TimeUnit.NANOSECONDS -> 1 / 1000
            TimeUnit.MICROSECONDS -> 1 / 1000 / 1000
        })
        val expMillis = nowMillis + unitTtl
        val expDate = Date(expMillis)

        return JWT.create().withJWTId(uuid).withSubject(subject).withIssuer(SecurityProperties.jwtIssuer)
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