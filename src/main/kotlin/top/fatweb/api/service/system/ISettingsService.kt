package top.fatweb.api.service.system

import top.fatweb.api.param.system.MailSettingsParam
import top.fatweb.api.vo.system.SettingsVo

/**
 * Settings service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
interface ISettingsService {
    /**
     * Get all settings
     *
     * @return SettingVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see SettingsVo
     */
    fun get(): SettingsVo

    /**
     * Update mail settings
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see MailSettingsParam
     */
    fun updateMail(mailSettingsParam: MailSettingsParam)
}