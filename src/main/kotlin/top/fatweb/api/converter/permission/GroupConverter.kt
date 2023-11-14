package top.fatweb.api.converter.permission

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.api.entity.permission.Group
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.GroupVo

object GroupConverter {
    fun groupToGroupVo(group: Group) = GroupVo(
        id = group.id,
        name = group.name,
        enable = group.enable == 1,
        createTime = group.createTime,
        updateTime = group.updateTime
    )

    fun groupPageToGroupPageVo(groupPage: IPage<Group>): PageVo<GroupVo> = PageVo(
        total = groupPage.total,
        pages = groupPage.pages,
        size = groupPage.size,
        current = groupPage.current,
        records = groupPage.records.map {
            groupToGroupVo(it)
        }
    )
}