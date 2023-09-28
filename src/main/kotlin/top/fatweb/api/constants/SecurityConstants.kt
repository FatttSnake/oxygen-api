package top.fatweb.api.constants

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

@Component
@ConfigurationProperties("app.security")
object SecurityConstants {
    var headerString = "Authorization"

    var tokenPrefix = "Bearer "

    var jwtTtl = 2L

    var jwtTtlUnit = TimeUnit.HOURS

    lateinit var jwtKey: String

    private fun ByteArray.hex(): String {
        return joinToString("") { "%02X".format(it) }
    }

    private fun String.md5(): String {
        val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
        return bytes.hex()
    }
}