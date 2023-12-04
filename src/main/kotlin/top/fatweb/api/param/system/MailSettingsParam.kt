package top.fatweb.api.param.system

import io.swagger.v3.oas.annotations.media.Schema

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
    val from: String?
)