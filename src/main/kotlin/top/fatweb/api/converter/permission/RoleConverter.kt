package top.fatweb.api.converter.permission

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.api.entity.permission.Power
import top.fatweb.api.entity.permission.Role
import top.fatweb.api.param.authentication.RoleAddParam
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.RoleVo

object RoleConverter {
    fun rolePageToRolePageVo(rolePage: IPage<Role>): PageVo<RoleVo> = PageVo(
        rolePage.total,
        rolePage.pages,
        rolePage.size,
        rolePage.current,
        rolePage.records.map {
            RoleVo(
                id = it.id,
                name = it.name,
                enable = it.enable == 1
            )
        }
    )

    fun roleAddParamToRole(roleAddParam: RoleAddParam): Role = Role().apply {
        name = roleAddParam.name
        enable = if (roleAddParam.enable) 1 else 0
        powers = roleAddParam.powerIds?.map { Power().apply { id = it } }
    }

    fun roleToRoleVo(role: Role): RoleVo = RoleVo(
        id = role.id,
        name = role.name,
        enable = role.enable == 1
    )
}