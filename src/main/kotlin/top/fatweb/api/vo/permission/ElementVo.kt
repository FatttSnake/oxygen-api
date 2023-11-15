package top.fatweb.api.vo.permission

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "页面元素返回参数")
data class ElementVo(
    val id: Long?,

    @Schema(description = "元素名", example = "AddButton")
    val name: String?,

    @Schema(description = "父 ID")
    val parentId: Long?,

    @Schema(description = "菜单 ID")
    val menuId: Long?
)
