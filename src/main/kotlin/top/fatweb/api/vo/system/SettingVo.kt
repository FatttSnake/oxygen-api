package top.fatweb.api.vo.system

data class SettingVo(
    val mail: MailSettingVo?
) {
    data class MailSettingVo(
        val host: String?,
        val port: Int?,
        val username: String?,
        val password: String?,
        val from: String?
    )
}