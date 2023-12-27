package top.fatweb.api.service.system

import top.fatweb.api.param.system.BaseSettingsParam
import top.fatweb.api.param.system.MailSendParam
import top.fatweb.api.param.system.MailSettingsParam
import top.fatweb.api.vo.system.BaseSettingsVo
import top.fatweb.api.vo.system.MailSettingsVo

/**
 * Settings service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
interface ISettingsService {
    /**
     * Get base settings
     *
     * @return BaseSettingsVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see BaseSettingsVo
     */
    fun getBase(): BaseSettingsVo?

    /**
     * Update base settings
     *
     * @param baseSettingsParam Base settings parameters
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see BaseSettingsParam
     */
    fun updateBase(baseSettingsParam: BaseSettingsParam)

    /**
     * Get mail settings
     *
     * @return MailSettingsVo object
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