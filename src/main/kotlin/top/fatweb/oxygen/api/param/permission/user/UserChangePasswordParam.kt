package top.fatweb.oxygen.api.param.permission.user

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

/**
 * Change password of user parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "用户更改密码请求参数")
data class UserChangePasswordParam(
    /**
     * Original password
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "原密码", required = true)
    @field:NotBlank(message = "Original password can not be blank")
    val originalPassword: String?,

    /**
     * New password
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "原密码", required = true)
    @field:NotBlank(message = "New password can not be blank")
    @field:Size(min = 10, max = 30, message = "New password must be 10-30 characters")
    val newPassword: String?
)
