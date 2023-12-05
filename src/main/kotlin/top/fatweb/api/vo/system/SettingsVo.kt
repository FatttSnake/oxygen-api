package top.fatweb.api.vo.system

/**
 * System settings value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
data class SettingsVo(
    /**
     * MailSettingVo object
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see MailSettingsVo
     */
    val mail: MailSettingsVo?
)