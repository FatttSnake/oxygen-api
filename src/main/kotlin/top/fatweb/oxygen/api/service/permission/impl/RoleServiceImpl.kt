package top.fatweb.oxygen.api.service.permission.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.converter.permission.RoleConverter
import top.fatweb.oxygen.api.entity.permission.RPowerRole
import top.fatweb.oxygen.api.entity.permission.Role
import top.fatweb.oxygen.api.exception.DatabaseInsertException
import top.fatweb.oxygen.api.exception.DatabaseUpdateException
import top.fatweb.oxygen.api.exception.NoRecordFoundException
import top.fatweb.oxygen.api.mapper.permission.RoleMapper
import top.fatweb.oxygen.api.param.permission.role.*
import top.fatweb.oxygen.api.service.permission.*
import top.fatweb.oxygen.api.util.PageUtil
import top.fatweb.oxygen.api.util.RedisUtil
import top.fatweb.oxygen.api.util.WebUtil
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.permission.RoleWithPowerVo
import top.fatweb.oxygen.api.vo.permission.base.RoleVo

/**
 * Role service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see RedisUtil
 * @see IRPowerRoleService
 * @see IFuncService
 * @see IMenuService
 * @see IUserService
 * @see ServiceImpl
 * @see RoleMapper
 * @see Role
 * @see IRoleService
 */
@Service
class RoleServiceImpl(
    private val redisUtil: RedisUtil,
    private val rPowerRoleService: IRPowerRoleService,
    private val funcService: IFuncService,
    private val menuService: IMenuService,
    private val userService: IUserService
) : ServiceImpl<RoleMapper, Role>(), IRoleService {
    override fun getPage(roleGetParam: RoleGetParam?): PageVo<RoleWithPowerVo> {
        val roleIdsPage = Page<Long>(roleGetParam?.currentPage ?: 1, roleGetParam?.pageSize ?: 20)

        PageUtil.setPageSort(roleGetParam, roleIdsPage)

        val roleIdsIPage =
            baseMapper.selectPage(roleIdsPage, roleGetParam?.searchName, roleGetParam?.searchRegex ?: false)

        val rolePage = Page<Role>(roleIdsPage.current, roleIdsIPage.size, roleIdsIPage.total)
        if (roleIdsIPage.total > 0) {
            rolePage.setRecords(baseMapper.selectListWithPowerByIds(roleIdsIPage.records))
        }


        return RoleConverter.rolePageToRoleWithPowerPageVo(rolePage)
    }

    override fun getOne(id: Long): RoleWithPowerVo =
        baseMapper.selectOneById(id)?.let(RoleConverter::roleToRoleWithPowerVo) ?: throw NoRecordFoundException()

    override fun getList(): List<RoleVo> = this.list().map(RoleConverter::roleToRoleVo)

    @Transactional
    override fun add(roleAddParam: RoleAddParam): RoleVo {
        val fullPowerIds = roleAddParam.powerIds?.let(::getFullPowerIds)

        val role = RoleConverter.roleAddParamToRole(roleAddParam)
        if (baseMapper.insert(role) != 1) {
            throw DatabaseInsertException()
        }

        if (fullPowerIds.isNullOrEmpty()) {
            return RoleConverter.roleToRoleVo(role)
        }

        rPowerRoleService.saveBatch(fullPowerIds.map {
            RPowerRole().apply {
                roleId = role.id
                powerId = it
            }
        })
        return RoleConverter.roleToRoleVo(role)
    }

    @Transactional
    override fun update(roleUpdateParam: RoleUpdateParam): RoleVo {
        val fullPowerIds = roleUpdateParam.powerIds?.let(::getFullPowerIds)

        val role = RoleConverter.roleUpdateParamToRole(roleUpdateParam)

        if (baseMapper.updateById(role) != 1) {
            throw DatabaseUpdateException()
        }

        val oldPowerList = rPowerRoleService.list(
            KtQueryWrapper(RPowerRole()).select(RPowerRole::powerId).eq(RPowerRole::roleId, roleUpdateParam.id)
        ).map { it.powerId }
        val addPowerIds = HashSet<Long>()
        val removePowerIds = HashSet<Long>()
        fullPowerIds?.forEach(addPowerIds::add)
        oldPowerList.forEach {
            it?.let(removePowerIds::add)
        }
        removePowerIds.removeAll(addPowerIds)
        oldPowerList.toSet().let(addPowerIds::removeAll)

        removePowerIds.forEach {
            rPowerRoleService.remove(
                KtQueryWrapper(RPowerRole()).eq(
                    RPowerRole::roleId, roleUpdateParam.id
                ).eq(RPowerRole::powerId, it)
            )
        }

        addPowerIds.forEach {
            rPowerRoleService.save(RPowerRole().apply {
                roleId = roleUpdateParam.id
                powerId = it
            })
        }

        roleUpdateParam.id?.let { offlineUser(it) }

        return RoleConverter.roleToRoleVo(role)
    }

    override fun status(roleUpdateStatusParam: RoleUpdateStatusParam) {
        updateById(RoleConverter.roleUpdateStatusParamToRole(roleUpdateStatusParam)).let {
            if (!it) {
                throw DatabaseUpdateException()
            }

            if (!roleUpdateStatusParam.enable) {
                roleUpdateStatusParam.id?.let { id -> offlineUser(id) }
            }
        }
    }

    @Transactional
    override fun deleteOne(id: Long) {
        this.delete(RoleDeleteParam(listOf(id)))
    }

    @Transactional
    override fun delete(roleDeleteParam: RoleDeleteParam) {
        baseMapper.deleteBatchIds(roleDeleteParam.ids)
        rPowerRoleService.remove(KtQueryWrapper(RPowerRole()).`in`(RPowerRole::roleId, roleDeleteParam.ids))
        offlineUser(*roleDeleteParam.ids!!.toLongArray())
    }

    private fun getFullPowerIds(powerIds: List<Long>): Set<Long> {
        val fullPowerIds: HashSet<Long> = hashSetOf()
        powerIds.forEach {
            fullPowerIds.add(it)
            getElementParent(it / 100 * 100, fullPowerIds)
            getMenuParent(it / 10000 * 10000, fullPowerIds)
            fullPowerIds.add(it / 1000000 * 1000000)
        }

        return fullPowerIds
    }

    private fun getElementParent(id: Long, parentIds: HashSet<Long>) {
        parentIds.add(id)
        funcService.getById(id)?.parentId?.let {
            getElementParent(it, parentIds)
        }
    }

    private fun getMenuParent(id: Long, parentIds: HashSet<Long>) {
        parentIds.add(id)
        menuService.getById(id)?.parentId?.let {
            getMenuParent(it, parentIds)
        }
    }

    private fun offlineUser(vararg roleIds: Long) {
        val userIds = userService.getIdsWithRoleIds(roleIds.toList())
        WebUtil.offlineUser(redisUtil, *userIds.toLongArray())
    }
}
