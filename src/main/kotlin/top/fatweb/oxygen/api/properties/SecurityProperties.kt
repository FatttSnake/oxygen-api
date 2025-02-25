package top.fatweb.oxygen.api.properties

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
     * Secret to generate token
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    var tokenSecret = "Oxygen"

    /**
     * Issuer of token
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    var tokenIssuer = "Oxygen"

    /**
     * Life of access token
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    var accessTokenTtl = 2L

    /**
     * Life util of access token
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    var accessTokenTtlUnit = TimeUnit.HOURS

    /**
     * Life of refresh token
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    var refreshTokenTtl = 128L

    /**
     * Life util of refresh token
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    var refreshTokenTtlUnit = TimeUnit.DAYS
}