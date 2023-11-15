package top.fatweb.api.vo.permission

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "菜单返回参数")
data class MenuVo(
    val id: Long?,

    @Schema(description = "菜单名", example = "System")
    val name: String?,

    @Schema(description = "URL", example = "/system")
    val url: String?,

    @Schema(description = "父 ID")
    val parentId: Long?,

    @Schema(description = "模块 ID")
    val moduleId: Long?
)
