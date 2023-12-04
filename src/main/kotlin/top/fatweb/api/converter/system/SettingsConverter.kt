package top.fatweb.api.converter.system

import top.fatweb.api.settings.MailSettings
import top.fatweb.api.settings.SystemSettings
import top.fatweb.api.vo.system.SettingsVo

object SettingsConverter {
    private fun mailSettingsToMailSettingsVo(mailSettings: MailSettings) = SettingsVo.MailSettingsVo(
        host = mailSettings.host,
        port = mailSettings.port,
        username = mailSettings.username,
        password = mailSettings.password,
        from = mailSettings.from
    )

    fun systemSettingsToSettingsVo(systemSettings: SystemSettings) = SettingsVo(
        mail = systemSettings.mail?.let { mailSettingsToMailSettingsVo(it) }
    )
}