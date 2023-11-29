package top.fatweb.api.param.permission

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

/**
 * Login param
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "登录请求参数")
data class LoginParam(
    @Schema(description = "用户名", example = "test", required = true)
    @field:NotBlank(message = "Username can not be blank")
    val username: String? = null,

    @Schema(description = "密码", example = "test123456", required = true)
    @field:NotBlank(message = "Password can not be blank")
    val password: String? = null
)