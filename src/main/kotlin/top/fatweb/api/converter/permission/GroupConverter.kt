package top.fatweb.api.converter.permission

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.api.entity.permission.Group
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.GroupVo

object GroupConverter {
    fun groupPageToGroupPageVo(groupPage: IPage<Group>): PageVo<GroupVo> = PageVo(
        groupPage.total,
        groupPage.pages,
        groupPage.size,
        groupPage.current,
        groupPage.records.map {
            GroupVo(
                id = it.id,
                name = it.name,
                enable = it.enable == 1
            )
        }
    )
}