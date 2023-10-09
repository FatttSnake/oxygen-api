package top.fatweb.api.constant

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
@ConfigurationProperties("app.security")
object SecurityConstants {
    var headerString = "Authorization"

    var tokenPrefix = "Bearer "

    var jwtTtl = 2L

    var jwtTtlUnit = TimeUnit.HOURS

    var jwtKey = "FatWeb"

    var jwtIssuer = "FatWeb"

    var redisTtl = 20L

    var redisTtlUnit = TimeUnit.MINUTES
}