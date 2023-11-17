package top.fatweb.api.param.api.v1.avatar

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max

data class AvatarGitHubParam(
    @Schema(description = "元素大小", defaultValue = "400")
    @field:Max(1000, message = "Element size must be less than or equal to 1000")
    val elementSize: Int = 400,

    @Schema(description = "精确度", defaultValue = "3")
    val precision: Int = 3
) : AvatarBaseParam()
