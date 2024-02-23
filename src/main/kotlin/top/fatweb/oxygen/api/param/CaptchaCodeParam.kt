package top.fatweb.oxygen.api.param

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

/**
 * Captcha code parameter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
open class CaptchaCodeParam {
    /**
     * Captcha code
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "验证码", required = true)
    @field:NotBlank(message = "Captcha code can not be blank")
    var captchaCode: String? = null
}