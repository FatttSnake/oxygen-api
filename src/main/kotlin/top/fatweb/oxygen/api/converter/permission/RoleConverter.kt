package top.fatweb.oxygen.api.converter.permission

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.oxygen.api.entity.permission.Power
import top.fatweb.oxygen.api.entity.permission.Role
import top.fatweb.oxygen.api.param.permission.role.RoleAddParam
import top.fatweb.oxygen.api.param.permission.role.RoleUpdateParam
import top.fatweb.oxygen.api.param.permission.role.RoleUpdateStatusParam
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.permission.RoleWithPowerVo
import top.fatweb.oxygen.api.vo.permission.base.RoleVo

/**
 * Role converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object RoleConverter {
    /**
     * Convert Role object into RoleVo object
     *
     * @param role Role object
     * @return RoleVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Role
     * @see RoleVo
     */
    fun roleToRoleVo(role: Role) = RoleVo(
        id = role.id,
        name = role.name,
        enable = role.enable?.let { it == 1},
        createTime = role.createTime,
        updateTime = role.updateTime
    )

    /**
     * Convert Role object into RoleWithPowerVo object
     *
     * @param role Role object
     * @return RoleWithPowerVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Role
     * @see RoleWithPowerVo
     */
    fun roleToRoleWithPowerVo(role: Role) = RoleWithPowerVo(
        id = role.id,
        name = role.name,
        enable = role.enable?.let { it == 1},
        createTime = role.createTime,
        updateTime = role.updateTime,
        modules = role.modules?.map(ModuleConverter::moduleToModuleVo),
        menus = role.menus?.map(MenuConverter::menuToMenuVo),
        funcs = role.funcs?.map(FuncConverter::funcToFuncVo),
        operations = role.operations?.map(OperationConverter::operationToOperationVo)
    )

    /**
     * Convert IPage<Role> object into PageVo object
     *
     * @param rolePage IPage<Role> object
     * @return PageVo<RoleWithPowerVo> object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see IPage
     * @see Role
     * @see PageVo
     * @see RoleWithPowerVo
     */
    fun rolePageToRoleWithPowerPageVo(rolePage: IPage<Role>) = PageVo(
        total = rolePage.total,
        pages = rolePage.pages,
        size = rolePage.size,
        current = rolePage.current,
        records = rolePage.records.map(::roleToRoleWithPowerVo)
    )

    /**
     * Convert RoleAddParam object into Role object
     *
     * @param roleAddParam RoleAddParam object
     * @return Role object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see RoleAddParam
     * @see Role
     */
    fun roleAddParamToRole(roleAddParam: RoleAddParam) = Role().apply {
        name = roleAddParam.name
        enable = if (roleAddParam.enable) 1 else 0
        powers = roleAddParam.powerIds?.map { Power().apply { id = it } }
    }

    /**
     * Convert RoleUpdateParam into Role object
     *
     * @param roleUpdateParam RoleUpdateParam object
     * @return Role object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see RoleUpdateParam
     * @see Role
     */
    fun roleUpdateParamToRole(roleUpdateParam: RoleUpdateParam) = Role().apply {
        id = roleUpdateParam.id
        name = roleUpdateParam.name
        enable = if (roleUpdateParam.enable) 1 else 0
        powers = roleUpdateParam.powerIds?.map { Power().apply { id = it } }
    }

    /**
     * Convert RoleUpdateStatusParam object into Role object
     *
     * @param roleUpdateStatusParam RoleUpdateStatusParam object
     * @return Role object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see RoleUpdateStatusParam
     * @see Role
     */
    fun roleUpdateStatusParamToRole(roleUpdateStatusParam: RoleUpdateStatusParam) = Role().apply {
        id = roleUpdateStatusParam.id
        enable = if (roleUpdateStatusParam.enable) 1 else 0
    }
}