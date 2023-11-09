package top.fatweb.api.service.permission

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.api.entity.permission.Role
import top.fatweb.api.param.authentication.RoleAddParam
import top.fatweb.api.param.authentication.RoleGetParam
import top.fatweb.api.vo.permission.RoleVo

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
interface IRoleService : IService<Role> {
    fun getPage(roleGetParam: RoleGetParam?): IPage<Role>

    fun add(roleAddParam: RoleAddParam): RoleVo?
}
