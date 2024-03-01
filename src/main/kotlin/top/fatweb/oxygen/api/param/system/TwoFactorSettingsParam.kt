package top.fatweb.oxygen.api.param.system

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import top.fatweb.oxygen.api.annotation.Trim

/**
 * Two-factor settings parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Trim
@Schema(description = "双因素设置请求参数")
data class TwoFactorSettingsParam(
    /**
     * Issuer
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Trim
    @Schema(description = "发布者")
    var issuer: String?,

    /**
     * Length of secret key
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "密钥长度")
    @field:NotNull(message = "Length of secret key can not be null")
    @field:Min(value = 3, message = "The length of the key must be greater than or equal to 3")
    val secretKeyLength: Int?
)