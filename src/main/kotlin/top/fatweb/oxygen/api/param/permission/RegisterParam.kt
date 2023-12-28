package top.fatweb.oxygen.api.param.permission

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

/**
 * Register parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "注册请求参数")
data class RegisterParam(
    /**
     * Username
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "用户名", required = true, example = "abc")
    @field:NotBlank(message = "Username can not be blank")
    @field:Pattern(regexp = "[a-zA-Z-_][0-9a-zA-Z-_]{2,38}", message = "Illegal username")
    val username: String?,

    /**
     * Email
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "邮箱", required = true, example = "user@email.com")
    @field:NotBlank(message = "Email can not be blank")
    @field:Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$", message = "Illegal email address")
    val email: String?,

    /**
     * Password
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "密码", required = true)
    @field:NotBlank(message = "Password can not be blank")
    @field:Size(min = 10, max = 30)
    val password: String?
)