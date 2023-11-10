package top.fatweb.api.vo.permission

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "权限集合返回参数")
data class PowerSetVo(
    @Schema(description = "模块列表")
    val moduleList: List<ModuleVo>?,

    @Schema(description = "菜单列表")
    val menuList: List<MenuVo>?,

    @Schema(description = "页面元素列表")
    val elementList: List<ElementVo>?,

    @Schema(description = "功能列表")
    val operationList: List<OperationVo>?
)
