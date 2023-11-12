package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.core.metadata.IPage
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
import top.fatweb.api.service.permission.IPowerRoleService
import top.fatweb.api.service.permission.IRoleService
import top.fatweb.api.util.PageUtil

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
    override fun getPage(roleGetParam: RoleGetParam?): IPage<Role> {
        val rolePage = Page<Role>(roleGetParam?.currentPage ?: 1, roleGetParam?.pageSize ?: 20)

        PageUtil.setPageSort(roleGetParam, rolePage)

        return baseMapper.selectPage(rolePage)
    }


    @Transactional
    override fun add(roleAddParam: RoleAddParam): Role? {
        val role = RoleConverter.roleAddParamToRole(roleAddParam)
        if (baseMapper.insert(role) == 1) {
            if (roleAddParam.powerIds.isNullOrEmpty()) {
                return role
            }

            if (powerRoleService.saveBatch(
                    roleAddParam.powerIds.map {
                        PowerRole().apply {
                            roleId = role.id
                            powerId = it
                        }
                    }
                )
            ) {
                return role
            }
        }

        return null
    }

    override fun changeStatus(roleChangeStatusParam: RoleChangeStatusParam): Boolean {
        return updateById(RoleConverter.roleChangeStatusParamToRole(roleChangeStatusParam))
    }
}
