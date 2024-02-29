package top.fatweb.oxygen.api.param.permission

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

/**
 * Validate two-factor parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "验证二步验证请求参数")
data class TwoFactorValidateParam(
    /**
     * Code
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "验证码")
    @field:NotBlank(message = "Code can not be blank")
    val code: String?
)
