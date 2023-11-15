package top.fatweb.api.param.authentication

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

@Schema(description = "用户组更改状态请求参数")
data class GroupChangeStatusParam(
    @Schema(description = "用户组 ID")
    @field:Min(0)
    val id: Long,

    @Schema(description = "启用", allowableValues = ["true", "false"])
    @field:NotNull
    val enable: Boolean
)