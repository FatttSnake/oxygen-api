package top.fatweb.api.vo.permission

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "角色返回参数")
data class RoleWithPowerVo(
    @JsonSerialize(using = ToStringSerializer::class)
    val id: Long?,

    @Schema(description = "角色名", example = "Role")
    val name: String?,

    @Schema(description = "启用", example = "true")
    val enable: Boolean?
)
