package top.fatweb.api.vo.system

data class SettingsVo(
    val mail: MailSettingsVo?
) {
    data class MailSettingsVo(
        val host: String?,
        val port: Int?,
        val username: String?,
        val password: String?,
        val from: String?
    )
}