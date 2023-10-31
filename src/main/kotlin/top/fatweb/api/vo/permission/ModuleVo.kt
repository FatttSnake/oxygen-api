package top.fatweb.api.vo.permission

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "模块返回参数")
data class ModuleVo(
    val id: Long?,

    @Schema(description = "模块名", example = "系统")
    val name: String?,

    @Schema(description = "权限 ID")
    val powerId: Long?
)
