package top.fatweb.oxygen.api.vo.permission

import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.oxygen.api.vo.permission.base.FuncVo
import top.fatweb.oxygen.api.vo.permission.base.MenuVo
import top.fatweb.oxygen.api.vo.permission.base.ModuleVo
import top.fatweb.oxygen.api.vo.permission.base.OperationVo

/**
 * Set of power value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "权限集合返回参数")
data class PowerSetVo(
    /**
     * List of ModuleVo object
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ModuleVo
     */
    @Schema(description = "模块列表")
    val moduleList: List<ModuleVo>?,

    /**
     * List of MenuVo object
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see MenuVo
     */
    @Schema(description = "菜单列表")
    val menuList: List<MenuVo>?,

    /**
     * List of FuncVo object
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see FuncVo
     */
    @Schema(description = "功能列表")
    val funcList: List<FuncVo>?,

    /**
     * List of OperationVo object
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see OperationVo
     */
    @Schema(description = "操作列表")
    val operationList: List<OperationVo>?
)
