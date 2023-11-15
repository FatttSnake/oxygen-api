package top.fatweb.api.converter.permission

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.api.entity.permission.Group
import top.fatweb.api.entity.permission.Role
import top.fatweb.api.param.authentication.GroupAddParam
import top.fatweb.api.param.authentication.GroupChangeStatusParam
import top.fatweb.api.param.authentication.GroupUpdateParam
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.GroupVo
import top.fatweb.api.vo.permission.GroupWithRoleVo

object GroupConverter {
    fun groupToGroupVo(group: Group) = GroupVo(
        id = group.id,
        name = group.name,
        enable = group.enable == 1,
        createTime = group.createTime,
        updateTime = group.updateTime
    )

    fun groupToGroupWithRoleVo(group: Group) = GroupWithRoleVo(
        id = group.id,
        name = group.name,
        enable = group.enable == 1,
        createTime = group.createTime,
        updateTime = group.updateTime,
        roles = group.roles?.map { RoleConverter.roleToRoleVo(it) }
    )

    fun groupPageToGroupWithRolePageVo(groupPage: IPage<Group>): PageVo<GroupWithRoleVo> = PageVo(
        total = groupPage.total,
        pages = groupPage.pages,
        size = groupPage.size,
        current = groupPage.current,
        records = groupPage.records.map {
            groupToGroupWithRoleVo(it)
        }
    )

    fun groupAddParamToGroup(groupAddParam: GroupAddParam) = Group().apply {
        name = groupAddParam.name
        enable = if (groupAddParam.enable == true) 1 else 0
        roles = groupAddParam.roleIds?.map { Role().apply { id = it } }
    }

    fun groupUpdateParamToGroup(groupUpdateParam: GroupUpdateParam) = Group().apply {
        id = groupUpdateParam.id
        name = groupUpdateParam.name
        enable = if (groupUpdateParam.enable == true) 1 else 0
        roles = groupUpdateParam.roleIds?.map { Role().apply { id = it } }
    }

    fun groupChangeStatusParamToGroup(groupChangeStatusParam: GroupChangeStatusParam) = Group().apply {
        id = groupChangeStatusParam.id
        enable = if (groupChangeStatusParam.enable) 1 else 0
    }
}