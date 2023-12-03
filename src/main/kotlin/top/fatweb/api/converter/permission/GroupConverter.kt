package top.fatweb.api.converter.permission

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.api.entity.permission.Group
import top.fatweb.api.entity.permission.Role
import top.fatweb.api.param.permission.group.GroupAddParam
import top.fatweb.api.param.permission.group.GroupUpdateStatusParam
import top.fatweb.api.param.permission.group.GroupUpdateParam
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.base.GroupVo
import top.fatweb.api.vo.permission.GroupWithRoleVo

/**
 * Group converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object GroupConverter {
    /**
     * Convert Group object into GroupVo object
     *
     * @param group Group object
     * @return GroupVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Group
     * @see GroupVo
     */
    fun groupToGroupVo(group: Group) = GroupVo(
        id = group.id,
        name = group.name,
        enable = group.enable == 1,
        createTime = group.createTime,
        updateTime = group.updateTime
    )

    /**
     * Convert Group object into GroupWithRoleVo object
     *
     * @param group Group object
     * @return GroupWithRoleVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Group
     * @see GroupWithRoleVo
     */
    fun groupToGroupWithRoleVo(group: Group) = GroupWithRoleVo(
        id = group.id,
        name = group.name,
        enable = group.enable == 1,
        createTime = group.createTime,
        updateTime = group.updateTime,
        roles = group.roles?.map { RoleConverter.roleToRoleVo(it) }
    )

    /**
     * Convert IPage<Group> object into PageVo<GroupWithRoleVo> object
     *
     * @param groupPage IPage<Group> object
     * @return PageVo<GroupWithRoleVo> object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see IPage
     * @see Group
     * @see PageVo
     * @see GroupWithRoleVo
     */
    fun groupPageToGroupWithRolePageVo(groupPage: IPage<Group>) = PageVo(
        total = groupPage.total,
        pages = groupPage.pages,
        size = groupPage.size,
        current = groupPage.current,
        records = groupPage.records.map {
            groupToGroupWithRoleVo(it)
        }
    )

    /**
     * Convert GroupAddParam object into Group object
     *
     * @param groupAddParam GroupAddParam object
     * @return Group object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see GroupAddParam
     * @see Group
     */
    fun groupAddParamToGroup(groupAddParam: GroupAddParam) = Group().apply {
        name = groupAddParam.name
        enable = if (groupAddParam.enable) 1 else 0
        roles = groupAddParam.roleIds?.map { Role().apply { id = it } }
    }

    /**
     * Convert GroupUpdateParam object into Group object
     *
     * @param groupUpdateParam GroupUpdateParam object
     * @return Group object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see GroupUpdateParam
     * @see Group
     */
    fun groupUpdateParamToGroup(groupUpdateParam: GroupUpdateParam) = Group().apply {
        id = groupUpdateParam.id
        name = groupUpdateParam.name
        enable = if (groupUpdateParam.enable) 1 else 0
        roles = groupUpdateParam.roleIds?.map { Role().apply { id = it } }
    }

    /**
     * Convert GroupUpdateStatusParam object into Group object
     *
     * @param groupUpdateStatusParam GroupUpdateStatusParam object
     * @return Group object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see GroupUpdateStatusParam
     * @see Group
     */
    fun groupUpdateStatusParamToGroup(groupUpdateStatusParam: GroupUpdateStatusParam) = Group().apply {
        id = groupUpdateStatusParam.id
        enable = if (groupUpdateStatusParam.enable) 1 else 0
    }
}