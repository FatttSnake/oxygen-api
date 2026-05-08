package top.fatweb.oxygen.api.properties

import jakarta.validation.constraints.NotBlank
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit

/**
 * Server properties
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Validated
@ConfigurationProperties("app")
data class ServerProperties(
    /**
     * App name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val appName: String,

    /**
     * Version
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val version: String,

    /**
     * Build time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val buildTime: String,

    /**
     * Startup time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LocalDateTime
     */
    val startupTime: LocalDateTime = LocalDateTime.now(ZoneOffset.UTC),

    /**
     * Turnstile secret key
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @field:NotBlank val turnstileSecretKey: String,

    /**
     * Admin properties
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     * @see Admin
     */
    val admin: Admin = Admin(),

    /**
     * Security properties
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     * @see Security
     */
    val security: Security = Security()
) {
    /**
     * Admin properties
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    data class Admin(
        /**
         * Username
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.2.0
         */
        @field:NotBlank val username: String = "admin",

        /**
         * Password
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.2.0
         */
        val password: String? = null,

        /**
         * Nickname
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.2.0
         */
        @field:NotBlank val nickname: String = "Administrator",

        /**
         * Email
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.2.0
         */
        @field:NotBlank val email: String = "admin@mail.com"
    )

    data class Security(
        /**
         * Key to get authentication from header
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.2.0
         */
        @field:NotBlank val headerKey: String = "Authorization",

        /**
         * Prefix of token
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.2.0
         */
        @field:NotBlank val tokenPrefix: String = "Bearer ",

        /**
         * Secret to generate token
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.2.0
         */
        @field:NotBlank val tokenSecret: String = "Oxygen",

        /**
         * Issuer of token
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.2.0
         */
        @field:NotBlank val tokenIssuer: String = "Oxygen",

        /**
         * Life of access token
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.2.0
         */
        val accessTokenTtl: Long = 2L,

        /**
         * Life util of access token
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.2.0
         * @see TimeUnit
         */
        val accessTokenTtlUnit: TimeUnit = TimeUnit.HOURS,

        /**
         * Life of refresh token
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.2.0
         */
        val refreshTokenTtl: Long = 128L,

        /**
         * Life util of refresh token
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.2.0
         * @see TimeUnit
         */
        val refreshTokenTtlUnit: TimeUnit = TimeUnit.DAYS
    )

    fun buildZoneDateTime(zoneId: ZoneId = ZoneId.systemDefault()): ZonedDateTime =
        LocalDateTime.parse(buildTime).atZone(ZoneId.of("UTC")).withZoneSameInstant(zoneId)
}
