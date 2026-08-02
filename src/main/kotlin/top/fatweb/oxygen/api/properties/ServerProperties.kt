package top.fatweb.oxygen.api.properties

import jakarta.validation.Valid
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime

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
     * Admin properties
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     * @see AdminProperties
     */
    @field:Valid val admin: AdminProperties = AdminProperties(),

    /**
     * Security properties
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     * @see SecurityProperties
     */
    @field:Valid val security: SecurityProperties = SecurityProperties(),

    /**
     * Storage properties
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     * @see StorageProperties
     */
    @field:Valid val storage: StorageProperties = StorageProperties()
) {

    fun buildZoneDateTime(zoneId: ZoneId = ZoneId.systemDefault()): ZonedDateTime =
        LocalDateTime.parse(buildTime).atZone(ZoneId.of("UTC")).withZoneSameInstant(zoneId)
}
