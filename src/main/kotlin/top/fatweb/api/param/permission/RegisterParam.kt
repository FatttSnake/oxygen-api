package top.fatweb.api.param.permission

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

/**
 * Register parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "注册请求参数")
data class RegisterParam(
    /**
     * Email
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "邮箱", example = "guest@fatweb.top", required = true)
    @field:Email(message = "Illegal email address")
    val email: String?,

    /**
     * Password
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "密码", example = "test123456", required = true)
    @field:NotBlank(message = "Password can not be blank")
    val password: String?
)