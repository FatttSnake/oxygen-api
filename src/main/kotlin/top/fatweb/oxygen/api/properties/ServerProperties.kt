package top.fatweb.oxygen.api.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime

/**
 * Application properties
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Component
@ConfigurationProperties("app")
object ServerProperties {
    /**
     * App name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    lateinit var appName: String

    /**
     * Version
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    lateinit var version: String

    /**
     * Build time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    lateinit var buildTime: String

    /**
     * Startup time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val startupTime: LocalDateTime = LocalDateTime.now(ZoneOffset.UTC)

    fun buildZoneDateTime(zoneId: ZoneId = ZoneId.systemDefault()): ZonedDateTime =
        LocalDateTime.parse(buildTime).atZone(ZoneId.of("UTC")).withZoneSameInstant(zoneId)
}