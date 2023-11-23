package top.fatweb.api.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
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
    lateinit var version: String

    lateinit var buildTime: String

    fun buildZoneDateTime(zoneId: ZoneId = ZoneId.systemDefault()): ZonedDateTime =
        LocalDateTime.parse(buildTime).atZone(ZoneId.of("UTC")).withZoneSameInstant(zoneId)
}