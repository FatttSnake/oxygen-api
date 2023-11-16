package top.fatweb.api.converter.permission

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.api.entity.permission.Power
import top.fatweb.api.entity.permission.Role
import top.fatweb.api.param.authentication.RoleAddParam
import top.fatweb.api.param.authentication.RoleChangeStatusParam
import top.fatweb.api.param.authentication.RoleUpdateParam
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.RoleVo
import top.fatweb.api.vo.permission.RoleWithPowerVo

/**
 * Role converter
 *
 * @author FatttSnake
 * @since 1.0.0
 */
object RoleConverter {
    fun roleToRoleVo(role: Role) = RoleVo(
        id = role.id,
        name = role.name,
        enable = role.enable == 1,
        createTime = role.createTime,
        updateTime = role.updateTime
    )

    fun roleToRoleWithPowerVo(role: Role) = RoleWithPowerVo(
        id = role.id,
        name = role.name,
        enable = role.enable == 1,
        createTime = role.createTime,
        updateTime = role.updateTime,
        modules = role.modules?.map { ModuleConverter.moduleToModuleVo(it) },
        menus = role.menus?.map { MenuConverter.menuToMenuVo(it) },
        elements = role.elements?.map { ElementConverter.elementToElementVo(it) },
        operations = role.operations?.map { OperationConverter.operationToOperationVo(it) }
    )

    fun rolePageToRoleWithPowerPageVo(rolePage: IPage<Role>) = PageVo(
        total = rolePage.total,
        pages = rolePage.pages,
        size = rolePage.size,
        current = rolePage.current,
        records = rolePage.records.map {
            roleToRoleWithPowerVo(it)
        }
    )

    fun roleAddParamToRole(roleAddParam: RoleAddParam) = Role().apply {
        name = roleAddParam.name
        enable = if (roleAddParam.enable == true) 1 else 0
        powers = roleAddParam.powerIds?.map { Power().apply { id = it } }
    }

    fun roleUpdateParamToRole(roleUpdateParam: RoleUpdateParam) = Role().apply {
        id = roleUpdateParam.id
        name = roleUpdateParam.name
        enable = if (roleUpdateParam.enable == true) 1 else 0
        powers = roleUpdateParam.powerIds?.map { Power().apply { id = it } }
    }

    fun roleChangeStatusParamToRole(roleChangeStatusParam: RoleChangeStatusParam) = Role().apply {
        id = roleChangeStatusParam.id
        enable = if (roleChangeStatusParam.enable) 1 else 0
    }
}