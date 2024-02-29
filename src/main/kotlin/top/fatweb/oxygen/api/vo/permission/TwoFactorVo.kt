package top.fatweb.oxygen.api.vo.permission

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Two-factor value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "二步验证返回参数")
data class TwoFactorVo (
    /**
     * QR code SVG as base64
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "二维码 SVG Base64")
    val qrCodeSVGBase64: String?
)