package top.fatweb.api.converter.permission

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.api.entity.permission.Power
import top.fatweb.api.entity.permission.Role
import top.fatweb.api.param.authentication.RoleAddParam
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.*

object RoleConverter {
    fun roleToRoleVo(role: Role): RoleVo = RoleVo(
        id = role.id,
        name = role.name,
        enable = role.enable == 1
    )

    fun roleToRoleWithPowerVo(role: Role) = RoleWithPowerVo(
        id = role.id,
        name = role.name,
        enable = role.enable == 1,
        modules = role.modules?.map { ModuleConverter.moduleToModuleVo(it) },
        menus = role.menus?.map { MenuConverter.menuToMenuVo(it) },
        elements = role.elements?.map { ElementConverter.elementToElementVo(it) },
        operations = role.operations?.map { OperationConverter.operationToOperationVo(it) }
    )

    fun rolePageToRoleWithPowerPageVo(rolePage: IPage<Role>): PageVo<RoleWithPowerVo> = PageVo(
        total = rolePage.total,
        pages = rolePage.pages,
        size = rolePage.size,
        current = rolePage.current,
        records = rolePage.records.map {
            roleToRoleWithPowerVo(it)
        }
    )

    fun roleAddParamToRole(roleAddParam: RoleAddParam): Role = Role().apply {
        name = roleAddParam.name
        enable = if (roleAddParam.enable == true) 1 else 0
        powers = roleAddParam.powerIds?.map { Power().apply { id = it } }
    }
}