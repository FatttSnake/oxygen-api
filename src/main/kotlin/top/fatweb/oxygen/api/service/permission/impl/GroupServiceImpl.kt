package top.fatweb.oxygen.api.service.permission.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.converter.permission.GroupConverter
import top.fatweb.oxygen.api.entity.permission.Group
import top.fatweb.oxygen.api.entity.permission.RRoleGroup
import top.fatweb.oxygen.api.mapper.permission.GroupMapper
import top.fatweb.oxygen.api.param.permission.group.*
import top.fatweb.oxygen.api.service.permission.IGroupService
import top.fatweb.oxygen.api.service.permission.IRRoleGroupService
import top.fatweb.oxygen.api.service.permission.IUserService
import top.fatweb.oxygen.api.util.PageUtil
import top.fatweb.oxygen.api.util.RedisUtil
import top.fatweb.oxygen.api.util.WebUtil
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.permission.GroupWithRoleVo
import top.fatweb.oxygen.api.vo.permission.base.GroupVo

/**
 * Group service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see RedisUtil
 * @see IRRoleGroupService
 * @see IUserService
 * @see ServiceImpl
 * @see GroupMapper
 * @see Group
 * @see IGroupService
 */
@Service
class GroupServiceImpl(
    private val redisUtil: RedisUtil,
    private val rRoleGroupService: IRRoleGroupService,
    private val userService: IUserService
) : ServiceImpl<GroupMapper, Group>(), IGroupService {
    override fun getPage(groupGetParam: GroupGetParam?): PageVo<GroupWithRoleVo> {
        val groupIdsPage = Page<Long>(groupGetParam?.currentPage ?: 1, groupGetParam?.pageSize ?: 20)

        PageUtil.setPageSort(groupGetParam, groupIdsPage)

        val groupIdsIPage =
            baseMapper.selectPage(groupIdsPage, groupGetParam?.searchName, groupGetParam?.searchRegex ?: false)
        val groupPage = Page<Group>(groupIdsPage.current, groupIdsIPage.size, groupIdsIPage.total)
        if (groupIdsIPage.total > 0) {
            groupPage.setRecords(baseMapper.selectListWithRoleByIds(groupIdsIPage.records))
        }

        return GroupConverter.groupPageToGroupWithRolePageVo(groupPage)
    }

    override fun getOne(id: Long): GroupWithRoleVo? {
        return baseMapper.selectOneById(id)?.let { GroupConverter.groupToGroupWithRoleVo(it) } ?: let { null }
    }

    override fun listAll(): List<GroupVo> {
        val groups = this.list()

        return groups.map { GroupConverter.groupToGroupVo(it) }
    }

    @Transactional
    override fun add(groupAddParam: GroupAddParam): GroupVo? {
        val group = GroupConverter.groupAddParamToGroup(groupAddParam)
        if (baseMapper.insert(group) == 1) {
            if (group.roles.isNullOrEmpty()) {
                return GroupConverter.groupToGroupVo(group)
            }

            if (rRoleGroupService.saveBatch(group.roles!!.map {
                    RRoleGroup().apply {
                        groupId = group.id
                        roleId = it.id
                    }
                })) {
                return GroupConverter.groupToGroupVo(group)
            }
        }

        return null
    }

    @Transactional
    override fun update(groupUpdateParam: GroupUpdateParam): GroupVo? {
        val group = GroupConverter.groupUpdateParamToGroup(groupUpdateParam)
        val oldRoleList = rRoleGroupService.list(
            KtQueryWrapper(RRoleGroup()).select(RRoleGroup::roleId).eq(RRoleGroup::groupId, groupUpdateParam.id)
        ).map { it.roleId }
        val addRoleIds = HashSet<Long>()
        val removeRoleIds = HashSet<Long>()
        groupUpdateParam.roleIds?.forEach { addRoleIds.add(it) }
        oldRoleList.forEach {
            if (it != null) {
                removeRoleIds.add(it)
            }
        }
        removeRoleIds.removeAll(addRoleIds)
        oldRoleList.toSet().let { addRoleIds.removeAll(it) }

        baseMapper.updateById(group)

        removeRoleIds.forEach {
            rRoleGroupService.remove(
                KtQueryWrapper(RRoleGroup()).eq(
                    RRoleGroup::groupId, groupUpdateParam.id
                ).eq(RRoleGroup::roleId, it)
            )
        }

        addRoleIds.forEach {
            rRoleGroupService.save(RRoleGroup().apply {
                groupId = groupUpdateParam.id
                roleId = it
            })
        }

        groupUpdateParam.id?.let { offlineUser(it) }

        return GroupConverter.groupToGroupVo(group)
    }

    override fun status(groupUpdateStatusParam: GroupUpdateStatusParam): Boolean {
        updateById(GroupConverter.groupUpdateStatusParamToGroup(groupUpdateStatusParam)).let {
            if (it && !groupUpdateStatusParam.enable) {
                groupUpdateStatusParam.id?.let { id -> offlineUser(id) }
            }

            return it
        }
    }

    @Transactional
    override fun deleteOne(id: Long) {
        this.delete(GroupDeleteParam(listOf(id)))
    }

    @Transactional
    override fun delete(groupDeleteParam: GroupDeleteParam) {
        baseMapper.deleteBatchIds(groupDeleteParam.ids)
        rRoleGroupService.remove(KtQueryWrapper(RRoleGroup()).`in`(RRoleGroup::groupId, groupDeleteParam.ids))
        offlineUser(*groupDeleteParam.ids.toLongArray())
    }

    private fun offlineUser(vararg groupIds: Long) {
        val userIds = userService.getIdsWithGroupIds(groupIds.toList())
        WebUtil.offlineUser(redisUtil, *userIds.toLongArray())
    }
}
