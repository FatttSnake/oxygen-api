package top.fatweb.oxygen.api.service.permission.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.converter.permission.toEntity
import top.fatweb.oxygen.api.converter.permission.toVo
import top.fatweb.oxygen.api.converter.permission.toVoWithRole
import top.fatweb.oxygen.api.entity.permission.Group
import top.fatweb.oxygen.api.entity.permission.RRoleGroup
import top.fatweb.oxygen.api.mapper.permission.GroupMapper
import top.fatweb.oxygen.api.param.permission.group.*
import top.fatweb.oxygen.api.service.permission.IGroupService
import top.fatweb.oxygen.api.service.permission.IRRoleGroupService
import top.fatweb.oxygen.api.service.permission.IUserService
import top.fatweb.oxygen.api.util.*
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

        setPageSort(groupGetParam, groupIdsPage)

        val groupIdsIPage =
            baseMapper.selectPage(groupIdsPage, groupGetParam?.searchName, groupGetParam?.searchRegex ?: false)
        val groupPage = Page<Group>(groupIdsPage.current, groupIdsIPage.size, groupIdsIPage.total)
        if (groupIdsIPage.total > 0) {
            groupPage.setRecords(baseMapper.selectListWithRoleByIds(groupIdsIPage.records))
        }

        return groupPage.toVoWithRole()
    }

    override fun getOne(id: Long): GroupWithRoleVo =
        queryOrThrowException { baseMapper.selectOneById(id)?.let(Group::toVoWithRole) }

    override fun getList(): List<GroupVo> = this.list().map(Group::toVo)

    @Transactional
    override fun add(groupAddParam: GroupAddParam): GroupVo {
        val group = groupAddParam.toEntity()
        saveOrThrowException { this.save(group) }

        if (group.roles.isNullOrEmpty()) {
            return group.toVo()
        }

        saveOrThrowException {
            rRoleGroupService.saveBatch(group.roles!!.map {
                RRoleGroup().apply {
                    groupId = group.id
                    roleId = it.id
                }
            })
        }

        return group.toVo()
    }

    @Transactional
    override fun update(groupUpdateParam: GroupUpdateParam) {
        val group = groupUpdateParam.toEntity()

        updateOrThrowException { this.updateById(group) }

        val oldRoleList = rRoleGroupService.list(
            KtQueryWrapper(RRoleGroup()).select(RRoleGroup::roleId).eq(RRoleGroup::groupId, groupUpdateParam.id)
        ).map { it.roleId }
        val addRoleIds = HashSet<Long>()
        val removeRoleIds = HashSet<Long>()
        groupUpdateParam.roleIds?.forEach(addRoleIds::add)
        oldRoleList.forEach {
            it?.let(removeRoleIds::add)
        }
        removeRoleIds.removeAll(addRoleIds)
        oldRoleList.toSet().let(addRoleIds::removeAll)

        removeRoleIds.forEach {
            rRoleGroupService.remove(
                KtQueryWrapper(RRoleGroup()).eq(
                    RRoleGroup::groupId, groupUpdateParam.id
                ).eq(RRoleGroup::roleId, it)
            )
        }

        addRoleIds.forEach {
            saveOrThrowException {
                rRoleGroupService.save(RRoleGroup().apply {
                    groupId = groupUpdateParam.id
                    roleId = it
                })
            }
        }

        groupUpdateParam.id?.let { offlineUser(it) }
    }

    override fun status(groupUpdateStatusParam: GroupUpdateStatusParam) {
        updateById(groupUpdateStatusParam.toEntity()).let {
            updateOrThrowException { it }

            if (!groupUpdateStatusParam.enable) {
                groupUpdateStatusParam.id?.let { id -> offlineUser(id) }
            }
        }
    }

    @Transactional
    override fun deleteOne(id: Long) {
        this.delete(GroupDeleteParam(listOf(id)))
    }

    @Transactional
    override fun delete(groupDeleteParam: GroupDeleteParam) {
        this.removeBatchByIds(groupDeleteParam.ids)
        rRoleGroupService.remove(KtQueryWrapper(RRoleGroup()).`in`(RRoleGroup::groupId, groupDeleteParam.ids))
        offlineUser(*groupDeleteParam.ids!!.toLongArray())
    }

    private fun offlineUser(vararg groupIds: Long) {
        val userIds = userService.getIdsByGroupIds(groupIds.toList())
        offlineUser(redisUtil, *userIds.toLongArray())
    }
}
