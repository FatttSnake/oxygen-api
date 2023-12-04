package top.fatweb.api.settings

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class SystemSettings(
    var mail: MailSettings? = null
)
