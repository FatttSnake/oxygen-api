package top.fatweb.api.settings

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum class MailSecurityType(val code: String) {
    NONE("None"),
    SSL_TLS("SSL/TLS"),
    START_TLS("StartTls");

    @JsonCreator
    fun fromCode(code: String): MailSecurityType? {
        entries.forEach {
            if (it.code == code) {
                return it
            }
        }
        return null
    }

    @JsonValue
    override fun toString(): String {
        return code
    }
}