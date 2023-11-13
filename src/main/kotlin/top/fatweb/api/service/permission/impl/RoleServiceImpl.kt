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
import top.fatweb.api.param.authentication.RoleAddParam
import top.fatweb.api.param.authentication.RoleChangeStatusParam
import top.fatweb.api.param.authentication.RoleGetParam
import top.fatweb.api.param.authentication.RoleUpdateParam
import top.fatweb.api.service.permission.IPowerRoleService
import top.fatweb.api.service.permission.IRoleService
import top.fatweb.api.util.PageUtil
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.RoleVo
import top.fatweb.api.vo.permission.RoleWithPowerVo

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
@Service
class RoleServiceImpl(
    private val powerRoleService: IPowerRoleService
) : ServiceImpl<RoleMapper, Role>(), IRoleService {
    override fun getPage(roleGetParam: RoleGetParam?): PageVo<RoleWithPowerVo> {
        val roleIdsPage = Page<Long>(roleGetParam?.currentPage ?: 1, roleGetParam?.pageSize ?: 20)

        PageUtil.setPageSort(roleGetParam, roleIdsPage)

        val roleIdsIPage = baseMapper.selectPage(roleIdsPage)

        val rolePage = Page<Role>(roleIdsPage.current, roleIdsIPage.size, roleIdsIPage.total)
        rolePage.setRecords(baseMapper.getWithPowerByList(roleIdsIPage.records))


        return RoleConverter.rolePageToRoleWithPowerPageVo(rolePage)
    }

    override fun getOne(id: Long): RoleWithPowerVo? {
        return baseMapper.selectOne(id)?.let { RoleConverter.roleToRoleWithPowerVo(it) } ?: let { null }
    }


    @Transactional
    override fun add(roleAddParam: RoleAddParam): RoleVo? {
        val fullPowerIds: HashSet<Long> = hashSetOf()
        roleAddParam.powerIds?.forEach {
            fullPowerIds.add(it)
            fullPowerIds.add(it / 100 * 100)
            fullPowerIds.add(it / 10000 * 10000)
            fullPowerIds.add(it / 1000000 * 1000000)
        }
        val role = RoleConverter.roleAddParamToRole(roleAddParam)
        if (baseMapper.insert(role) == 1) {
            if (fullPowerIds.isEmpty()) {
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
        val fullPowerIds: HashSet<Long> = hashSetOf()
        roleUpdateParam.powerIds?.forEach {
            fullPowerIds.add(it)
            fullPowerIds.add(it / 100 * 100)
            fullPowerIds.add(it / 10000 * 10000)
            fullPowerIds.add(it / 1000000 * 1000000)
        }
        val role = RoleConverter.roleUpdateParamToRole(roleUpdateParam)
        val oldPowerList = baseMapper.getPowerList(roleUpdateParam.id)
        val addPowerIds = HashSet<Long>()
        val removePowerIds = HashSet<Long>()
        fullPowerIds.forEach { addPowerIds.add(it) }
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

        return RoleConverter.roleToRoleVo(role)
    }

    override fun changeStatus(roleChangeStatusParam: RoleChangeStatusParam): Boolean {
        return updateById(RoleConverter.roleChangeStatusParamToRole(roleChangeStatusParam))
    }
}
