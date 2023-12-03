package top.fatweb.api.param.system

data class SettingParam (
    val mail: MailSettingParam?
) {
    data class MailSettingParam (
        val host: String,
        val port: Int,
        val username: String,
        val password: String,
        val from: String
    )
}