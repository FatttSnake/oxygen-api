package top.fatweb.api.param.authentication

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class RoleAddParam(
    @Schema(description = "角色名称")
    @field:NotBlank
    val name: String,

    @Schema(description = "启用")
    val enable: Boolean = true,

    @Schema(description = "权限 ID 列表")
    val powerIds: List<Long>? = null
)
