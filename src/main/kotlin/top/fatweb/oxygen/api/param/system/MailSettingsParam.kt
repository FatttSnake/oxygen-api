package top.fatweb.oxygen.api.param.system

import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.oxygen.api.settings.MailSecurityType

/**
 * Mail settings parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "邮件设置请求参数")
data class MailSettingsParam(
    /**
     * Host
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "SMTP 服务器")
    val host: String?,

    /**
     * Port
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "端口号")
    val port: Int?,

    /**
     * Security type
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "安全类型", allowableValues = ["None", "SSL/TLS", "StartTls"], defaultValue = "None")
    val securityType: MailSecurityType? = MailSecurityType.NONE,

    /**
     * Username
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "用户名")
    val username: String?,

    /**
     * Password
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "密码")
    val password: String?,

    /**
     * Sender
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "发送者")
    val from: String?,

    /**
     * Sender name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "发送者名称")
    val fromName: String?
)