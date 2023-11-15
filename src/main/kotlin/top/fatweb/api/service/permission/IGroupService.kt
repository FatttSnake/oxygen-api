package top.fatweb.api.service.permission

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.api.entity.permission.Group
import top.fatweb.api.param.authentication.*
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.GroupVo
import top.fatweb.api.vo.permission.GroupWithRoleVo

/**
 * <p>
 * 用户组表 服务类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
interface IGroupService : IService<Group> {
    fun getPage(groupGetParam: GroupGetParam?): PageVo<GroupWithRoleVo>

    fun getOne(id: Long): GroupWithRoleVo?

    fun listAll(): List<GroupVo>

    fun add(groupAddParam: GroupAddParam): GroupVo?

    fun update(groupUpdateParam: GroupUpdateParam): GroupVo?

    fun changeStatus(groupChangeStatusParam: GroupChangeStatusParam): Boolean

    fun deleteOne(id: Long)

    fun delete(groupDeleteParam: GroupDeleteParam)
}
