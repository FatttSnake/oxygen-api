package top.fatweb.oxygen.api.param.system

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Base settings parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "基础设置请求参数")
data class BaseSettingsParam(
    /**
     * Application name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "应用名称")
    val appName: String?,

    /**
     * Application URL
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "应用 URL")
    val appUrl: String?,

    /**
     * Verify URL
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "验证邮箱 URL")
    val verifyUrl: String?,

    /**
     * Retrieve URL
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "找回密码 URL")
    val retrieveUrl: String?
)