package top.fatweb.oxygen.api.service.system

import top.fatweb.oxygen.api.param.system.BaseSettingsParam
import top.fatweb.oxygen.api.param.system.MailSendParam
import top.fatweb.oxygen.api.param.system.MailSettingsParam
import top.fatweb.oxygen.api.param.system.TwoFactorSettingsParam
import top.fatweb.oxygen.api.vo.system.BaseSettingsVo
import top.fatweb.oxygen.api.vo.system.MailSettingsVo
import top.fatweb.oxygen.api.vo.system.TwoFactorSettingsVo

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

    /**
     * Get two-factor settings
     *
     * @return TwoFactorSettingsVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see TwoFactorSettingsVo
     */
    fun getTwoFactor(): TwoFactorSettingsVo?

    /**
     * Update two-factor settings
     *
     * @param twoFactorSettingsParam Two-factor settings parameters
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see TwoFactorSettingsParam
     */
    fun updateTwoFactor(twoFactorSettingsParam: TwoFactorSettingsParam)
}