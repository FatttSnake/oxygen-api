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
    var headerString = "Authorization"

    var tokenPrefix = "Bearer "

    var jwtTtl = 2L

    var jwtTtlUnit = TimeUnit.HOURS

    var jwtKey = "FatWeb"

    var jwtIssuer = "FatWeb"

    var redisTtl = 20L

    var redisTtlUnit = TimeUnit.MINUTES
}