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
 * @author FatttSnake
 * @since 1.0.0
 */
object JwtUtil {
    private fun getUUID() = UUID.randomUUID().toString().replace("-", "")

    /**
     * 生成加密后的秘钥 secretKey
     *
     * @return 密钥
     */
    private fun generalKey(): SecretKeySpec {
        val encodeKey = Base64.getDecoder().decode(SecurityProperties.jwtKey)
        return SecretKeySpec(encodeKey, 0, encodeKey.size, "AES")
    }

    private fun algorithm(): Algorithm = Algorithm.HMAC256(generalKey().toString())

    /**
     * 创建 token
     *
     * @param subject token 中存放的数据（json格式)
     * @param ttl token 生存时间
     * @param timeUnit ttl 时间单位
     * @param uuid 唯一 ID
     * @return jwt 串
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
     * 解析 jwt
     *
     * @param jwt jwt 串
     * @return 解析内容
     */
    fun parseJwt(jwt: String): DecodedJWT = JWT.require(algorithm()).build().verify(jwt)
}