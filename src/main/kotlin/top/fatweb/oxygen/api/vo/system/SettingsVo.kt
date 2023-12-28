package top.fatweb.oxygen.api.vo.system

import io.swagger.v3.oas.annotations.media.Schema

/**
 * System settings value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */

@Schema(description = "系统设置返回参数")
data class SettingsVo(
    /**
     * MailSettingVo object
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see MailSettingsVo
     */
    @Schema(description = "邮件设置")
    val mail: MailSettingsVo?
)