package top.fatweb.api.vo.permission

import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.api.vo.permission.base.FuncVo
import top.fatweb.api.vo.permission.base.MenuVo
import top.fatweb.api.vo.permission.base.ModuleVo
import top.fatweb.api.vo.permission.base.OperationVo

/**
 * Set of power value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "权限集合返回参数")
data class PowerSetVo(
    @Schema(description = "模块列表")
    val moduleList: List<ModuleVo>?,

    @Schema(description = "菜单列表")
    val menuList: List<MenuVo>?,

    @Schema(description = "功能列表")
    val funcList: List<FuncVo>?,

    @Schema(description = "操作列表")
    val operationList: List<OperationVo>?
)
