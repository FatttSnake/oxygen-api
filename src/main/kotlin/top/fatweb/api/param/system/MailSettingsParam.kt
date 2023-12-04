package top.fatweb.api.param.system

data class MailSettingsParam (
    val host: String?,
    val port: Int?,
    val username: String?,
    val password: String?,
    val from: String?
)