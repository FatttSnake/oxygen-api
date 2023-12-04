package top.fatweb.api.settings

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class MailSettings(
    var host: String? = null,
    var port: Int? = null,
    var username: String? = null,
    var password: String? = null,
    var from: String? = null,
)