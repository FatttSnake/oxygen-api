package top.fatweb.api.param.system

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

/**
 * Mail send parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "邮件发送请求参数")
data class MailSendParam(
    /**
     * Receiver
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "接收者", required = true, example = "user@email.com")
    @field:NotBlank
    val to: String?
)
