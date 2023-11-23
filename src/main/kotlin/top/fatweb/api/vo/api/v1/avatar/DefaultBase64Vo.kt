package top.fatweb.api.vo.api.v1.avatar

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Default base64 value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "默认随机头像 Base64 返回参数")
data class DefaultBase64Vo(
    @Schema(description = "base64")
    val base64: String?
)
