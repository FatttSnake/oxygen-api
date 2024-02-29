package top.fatweb.oxygen.api.param.permission

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import top.fatweb.oxygen.api.annotation.Trim
import top.fatweb.oxygen.api.param.CaptchaCodeParam

/**
 * Login parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see CaptchaCodeParam
 */
@Trim
@Schema(description = "登录请求参数")
data class LoginParam(
    /**
     * Account
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Trim
    @Schema(description = "账户", required = true, example = "test")
    @field:NotBlank(message = "Account can not be blank")
    var account: String?,

    /**
     * Password
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "密码", required = true)
    @field:NotBlank(message = "Password can not be blank")
    val password: String?,

    /**
     * Two-factor code
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "二步验证码")
    val twoFactorCode: String?
) : CaptchaCodeParam()