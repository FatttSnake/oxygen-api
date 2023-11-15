package top.fatweb.api.param.authentication

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

@Schema(description = "角色更改状态请求参数")
data class RoleChangeStatusParam(
    @Schema(description = "角色 ID")
    @field:Min(0)
    val id: Long,

    @Schema(description = "启用", allowableValues = ["true", "false"])
    @field:NotNull
    val enable: Boolean
)