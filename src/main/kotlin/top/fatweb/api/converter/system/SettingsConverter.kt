package top.fatweb.api.converter.system

import top.fatweb.api.settings.MailSettings
import top.fatweb.api.settings.SystemSettings
import top.fatweb.api.vo.system.SettingsVo

/**
 * Settings converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object SettingsConverter {
    /**
     * Convert MailSettings object into MailSettingsVo object
     *
     * @param mailSettings MailSettings object
     * @return MailSettingsVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see MailSettings
     * @see SettingsVo.MailSettingsVo
     */
    private fun mailSettingsToMailSettingsVo(mailSettings: MailSettings) = SettingsVo.MailSettingsVo(
        host = mailSettings.host,
        port = mailSettings.port,
        username = mailSettings.username,
        password = mailSettings.password,
        from = mailSettings.from
    )

    /**
     * Convert SystemSettings object into SettingVo object
     *
     * @param systemSettings SystemSettings object
     * @return SettingsVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see SystemSettings
     * @see SettingsVo
     */
    fun systemSettingsToSettingsVo(systemSettings: SystemSettings) = SettingsVo(
        mail = systemSettings.mail?.let { mailSettingsToMailSettingsVo(it) }
    )
}