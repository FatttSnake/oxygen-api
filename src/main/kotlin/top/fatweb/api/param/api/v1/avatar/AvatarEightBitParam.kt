package top.fatweb.api.param.api.v1.avatar

import io.swagger.v3.oas.annotations.media.Schema

data class AvatarEightBitParam(
    @Schema(description = "性别", defaultValue = "male", allowableValues = ["male", "female"])
    val gender: String?,
) : AvatarBaseParam()
