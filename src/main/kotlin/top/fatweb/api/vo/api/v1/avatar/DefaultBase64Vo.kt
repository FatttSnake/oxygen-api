package top.fatweb.api.vo.api.v1.avatar

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "默认随机头像 Base64 返回参数")
data class DefaultBase64Vo(
    @Schema(description = "base64")
    val base64: String?
)
