package top.fatweb.api.param.permission

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

/**
 * Login parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "登录请求参数")
data class LoginParam(
    /**
     * Account
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "账户", example = "test", required = true)
    @field:NotBlank(message = "Account can not be blank")
    val account: String?,

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