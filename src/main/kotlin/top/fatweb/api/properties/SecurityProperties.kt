package top.fatweb.api.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

/**
 * Security properties
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Component
@ConfigurationProperties("app.security")
object SecurityProperties {
    /**
     * Key to get authentication from header
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var headerKey = "Authorization"

    /**
     * Prefix of token
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var tokenPrefix = "Bearer "

    /**
     * TTL of JWT
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var jwtTtl = 2L

    /**
     * TTL unit of JWT
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var jwtTtlUnit = TimeUnit.HOURS

    /**
     * Key of JWT
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var jwtKey = "FatWeb"

    /**
     * Issuer of JWT
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var jwtIssuer = "FatWeb"

    /**
     * TTL of redis
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var redisTtl = 20L

    /**
     * TTL unit of redis
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var redisTtlUnit = TimeUnit.MINUTES
}