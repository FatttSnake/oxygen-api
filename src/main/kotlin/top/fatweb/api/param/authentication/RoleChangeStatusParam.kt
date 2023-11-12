package top.fatweb.api.param.authentication

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class RoleChangeStatusParam(
    @Schema(description = "角色 ID")
    @field:Pattern(regexp = "^\\d+$", message = "ID must be number")
    @JsonSerialize(using = ToStringSerializer::class)
    val id: Long,

    @Schema(description = "启用", allowableValues = ["true", "false"])
    @field:NotNull
    val enable: Boolean
)