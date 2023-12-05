package top.fatweb.api.service.system

import top.fatweb.api.param.system.MailSendParam
import top.fatweb.api.param.system.MailSettingsParam
import top.fatweb.api.vo.system.MailSettingsVo

/**
 * Settings service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
interface ISettingsService {
    /**
     * Get mail settings
     *
     * @return MailSettingVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see MailSettingsVo
     */
    fun getMail(): MailSettingsVo?

    /**
     * Update mail settings
     *
     * @param mailSettingsParam Mail settings parameters
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see MailSettingsParam
     */
    fun updateMail(mailSettingsParam: MailSettingsParam)

    /**
     * Send mail
     *
     * @param mailSendParam Send mail parameters
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see MailSettingsParam
     */
    fun sendMail(mailSendParam: MailSendParam)
}