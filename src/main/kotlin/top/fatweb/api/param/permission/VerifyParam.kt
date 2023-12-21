package top.fatweb.api.param.permission

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

/**
 * Verify parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "验证请求参数")
data class VerifyParam(
    /**
     * Code
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "验证码", example = "9c4b8199-1dbe-4f6f-96a5-fe1d75cc6a65", required = true)
    @field:NotBlank(message = "Code can not be blank")
    val code: String?,

    /**
     * Username
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "用户名", example = "adb")
    val username: String?,

    /**
     * Nickname
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "昵称", example = "QwQ")
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