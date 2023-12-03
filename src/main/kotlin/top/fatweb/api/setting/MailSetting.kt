package top.fatweb.api.setting

data class MailSetting(
    var host: String? = null,
    var port: Int? = null,
    var username: String? = null,
    var password: String? = null,
    var from: String? = null,
)