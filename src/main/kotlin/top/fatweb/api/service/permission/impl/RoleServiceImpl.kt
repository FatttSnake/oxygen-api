package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.api.converter.permission.RoleConverter
import top.fatweb.api.entity.permission.PowerRole
import top.fatweb.api.entity.permission.Role
import top.fatweb.api.mapper.permission.RoleMapper
import top.fatweb.api.param.permission.role.*
import top.fatweb.api.service.permission.*
import top.fatweb.api.util.PageUtil
import top.fatweb.api.util.RedisUtil
import top.fatweb.api.util.WebUtil
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.base.RoleVo
import top.fatweb.api.vo.permission.RoleWithPowerVo

/**
 * Role service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Service
class RoleServiceImpl(
    private val redisUtil: RedisUtil,
    private val powerRoleService: IPowerRoleService,
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

    override fun getOne(id: Long): RoleWithPowerVo? {
        return baseMapper.selectOneById(id)?.let { RoleConverter.roleToRoleWithPowerVo(it) } ?: let { null }
    }

    override fun listAll(): List<RoleVo> {
        val roles = this.list()

        return roles.map { RoleConverter.roleToRoleVo(it) }
    }


    @Transactional
    override fun add(roleAddParam: RoleAddParam): RoleVo? {
        val fullPowerIds = roleAddParam.powerIds?.let { getFullPowerIds(it) }

        val role = RoleConverter.roleAddParamToRole(roleAddParam)
        if (baseMapper.insert(role) == 1) {
            if (fullPowerIds.isNullOrEmpty()) {
                return RoleConverter.roleToRoleVo(role)
            }

            if (powerRoleService.saveBatch(fullPowerIds.map {
                    PowerRole().apply {
                        roleId = role.id
                        powerId = it
                    }
                })) {
                return RoleConverter.roleToRoleVo(role)
            }
        }

        return null
    }

    @Transactional
    override fun update(roleUpdateParam: RoleUpdateParam): RoleVo? {
        val fullPowerIds = roleUpdateParam.powerIds?.let { getFullPowerIds(it) }

        val role = RoleConverter.roleUpdateParamToRole(roleUpdateParam)
        val oldPowerList = powerRoleService.list(
            KtQueryWrapper(PowerRole()).select(PowerRole::powerId).eq(PowerRole::roleId, roleUpdateParam.id)
        ).map { it.powerId }
        val addPowerIds = HashSet<Long>()
        val removePowerIds = HashSet<Long>()
        fullPowerIds?.forEach { addPowerIds.add(it) }
        oldPowerList.forEach {
            if (it != null) {
                removePowerIds.add(it)
            }
        }
        removePowerIds.removeAll(addPowerIds)
        oldPowerList.toSet().let { addPowerIds.removeAll(it) }

        baseMapper.updateById(role)

        removePowerIds.forEach {
            powerRoleService.remove(
                KtQueryWrapper(PowerRole()).eq(
                    PowerRole::roleId, roleUpdateParam.id
                ).eq(PowerRole::powerId, it)
            )
        }

        addPowerIds.forEach {
            powerRoleService.save(PowerRole().apply {
                roleId = roleUpdateParam.id
                powerId = it
            })
        }

        roleUpdateParam.id?.let { offlineUser(it) }

        return RoleConverter.roleToRoleVo(role)
    }

    override fun changeStatus(roleChangeStatusParam: RoleChangeStatusParam): Boolean {
        updateById(RoleConverter.roleChangeStatusParamToRole(roleChangeStatusParam)).let {
            if (it && !roleChangeStatusParam.enable) {
                roleChangeStatusParam.id?.let { id -> offlineUser(id) }
            }

            return it
        }
    }

    @Transactional
    override fun deleteOne(id: Long) {
        this.delete(RoleDeleteParam(listOf(id)))
    }

    @Transactional
    override fun delete(roleDeleteParam: RoleDeleteParam) {
        baseMapper.deleteBatchIds(roleDeleteParam.ids)
        powerRoleService.remove(KtQueryWrapper(PowerRole()).`in`(PowerRole::roleId, roleDeleteParam.ids))
        offlineUser(*roleDeleteParam.ids.toLongArray())
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
        val userIds = userService.selectIdsWithRoleIds(roleIds.toList())
        WebUtil.offlineUser(redisUtil, *userIds.toLongArray())
    }
}
