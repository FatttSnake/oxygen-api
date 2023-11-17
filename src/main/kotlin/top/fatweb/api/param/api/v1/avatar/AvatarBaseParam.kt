package top.fatweb.api.param.api.v1.avatar

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Pattern

open class AvatarBaseParam {
    @Schema(description = "种子")
    var seed: Long? = null

    @Schema(description = "图像大小", defaultValue = "128")
    @field:Max(256, message = "Size must be less than or equal to 256")
    var size: Int? = null

    @Schema(description = "外边距", defaultValue = "0")
    var margin: Int? = null

    @Schema(description = "内边距", defaultValue = "0")
    var padding: Int? = null

    @Schema(defaultValue = "颜色列表", example = "#FFFFFFAA")
    var colors: List<String>? = null

    @Schema(defaultValue = "背景颜色", example = "#FFFFFFAA")
    @field:Pattern(regexp = "^#[0-9a-fA-F]{6}|#[0-9a-fA-F]{8}$", message = "Background color must be a hex color code")
    var background: String? = null

}
