package top.fatweb.oxygen.api.param.permission

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

/**
 * Verify email parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "验证邮箱请求参数")
data class VerifyParam(
    /**
     * Code
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "验证码", required = true)
    @field:NotBlank(message = "Code can not be blank")
    val code: String?,

    /**
     * Nickname
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "昵称", example = "QwQ")
    @field:Pattern(regexp = "^.{3,20}$", message = "Nickname must be 3-20 characters")
    val nickname: String?,

    /**
     * Avatar
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "头像")
    val avatar: String?
)