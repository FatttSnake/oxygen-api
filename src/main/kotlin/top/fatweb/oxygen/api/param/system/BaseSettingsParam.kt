package top.fatweb.oxygen.api.param.system

import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.oxygen.api.annotation.Trim

/**
 * Base settings parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Trim
@Schema(description = "基础设置请求参数")
data class BaseSettingsParam(
    /**
     * Application name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Trim
    @Schema(description = "应用名称")
    var appName: String?,

    /**
     * Application URL
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Trim
    @Schema(description = "应用 URL")
    var appUrl: String?,

    /**
     * Verify URL
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Trim
    @Schema(description = "验证邮箱 URL")
    var verifyUrl: String?,

    /**
     * Retrieve URL
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Trim
    @Schema(description = "找回密码 URL")
    var retrieveUrl: String?
)