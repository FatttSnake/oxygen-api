package top.fatweb.api.service.permission

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.api.entity.permission.Group
import top.fatweb.api.param.permission.group.*
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.base.GroupVo
import top.fatweb.api.vo.permission.GroupWithRoleVo

/**
 * Group service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
interface IGroupService : IService<Group> {
    fun getPage(groupGetParam: GroupGetParam?): PageVo<GroupWithRoleVo>

    fun getOne(id: Long): GroupWithRoleVo?

    fun listAll(): List<GroupVo>

    fun add(groupAddParam: GroupAddParam): GroupVo?

    fun update(groupUpdateParam: GroupUpdateParam): GroupVo?

    fun status(groupUpdateStatusParam: GroupUpdateStatusParam): Boolean

    fun deleteOne(id: Long)

    fun delete(groupDeleteParam: GroupDeleteParam)
}
