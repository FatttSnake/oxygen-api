package top.fatweb.oxygen.api.vo.system

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Base settings value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "基础设置返回参数")
data class BaseSettingsVo(
    /**
     * System name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:Schema(description = "系统名称")
    val systemName: String?,

    /**
     * Desktop protocol
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:Schema(description = "桌面端协议")
    val desktopProtocol: String?,

    /**
     * Application protocol
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:Schema(description = "移动端协议")
    val applicationProtocol: String?,

    /**
     * Token expiry buffer time(ms)
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:Schema(description = "Token 失效缓冲时间（毫秒）")
    val tokenExpiryBufferMs: Long?,

    /**
     * Token expiry check interval time(ms)
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:Schema(description = "Token 失效检查周期（毫秒）")
    val tokenExpiryCheckIntervalMs: Long?,

    /**
     * Turnstile site key
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:Schema(description = "Turnstile 站点标识")
    val turnstileSiteKey: String?,

    /**
     * Turnstile secret key
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:Schema(description = "Turnstile 密钥")
    val turnstileSecretKey: String?,

    /**
     * Home URL
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:Schema(description = "主页 URL")
    val homeUrl: String?,

    /**
     * Get Android app URL
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:Schema(description = "获取安卓端 URL")
    val getAndroidAppUrl: String?
)
