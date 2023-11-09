package top.fatweb.api.converter.permission

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.api.entity.permission.Power
import top.fatweb.api.entity.permission.Role
import top.fatweb.api.param.authentication.RoleAddParam
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.*

object RoleConverter {
    fun rolePageToRoleWithPowerPageVo(rolePage: IPage<Role>): PageVo<RoleWithPowerVo> = PageVo(
        rolePage.total,
        rolePage.pages,
        rolePage.size,
        rolePage.current,
        rolePage.records.map {
            RoleWithPowerVo(
                id = it.id,
                name = it.name,
                enable = it.enable == 1,
                modules = it.modules?.map { module ->
                    ModuleVo(
                        id = module.id,
                        name = module.name,
                        powerId = module.powerId
                    )
                },
                menus = it.menus?.map { menu ->
                    MenuVo(
                        id = menu.id,
                        name = menu.name,
                        url = menu.url,
                        powerId = menu.powerId,
                        parentId = menu.powerId,
                        moduleId = menu.moduleId
                    )
                },
                elements = it.elements?.map { element ->
                    ElementVo(
                        id = element.id,
                        name = element.name,
                        powerId = element.powerId,
                        parentId = element.parentId,
                        menuId = element.menuId
                    )
                },
                operations = it.operations?.map { operation ->
                    OperationVo(
                        id = operation.id,
                        name = operation.name,
                        code = operation.code,
                        powerId = operation.powerId,
                        elementId = operation.elementId
                    )
                }
            )
        }
    )

    fun roleAddParamToRole(roleAddParam: RoleAddParam): Role = Role().apply {
        name = roleAddParam.name
        enable = if (roleAddParam.enable == true) 1 else 0
        powers = roleAddParam.powerIds?.map { Power().apply { id = it } }
    }

    fun roleToRoleVo(role: Role): RoleVo = RoleVo(
        id = role.id,
        name = role.name,
        enable = role.enable == 1
    )
}