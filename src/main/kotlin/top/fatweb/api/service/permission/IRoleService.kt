package top.fatweb.api.service.permission

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.api.entity.permission.Role
import top.fatweb.api.param.authentication.RoleAddParam
import top.fatweb.api.param.authentication.RoleChangeStatusParam
import top.fatweb.api.param.authentication.RoleGetParam
import top.fatweb.api.param.authentication.RoleUpdateParam
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.RoleVo
import top.fatweb.api.vo.permission.RoleWithPowerVo

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
interface IRoleService : IService<Role> {
    fun getPage(roleGetParam: RoleGetParam?): PageVo<RoleWithPowerVo>

    fun getOne(id: Long): RoleWithPowerVo?

    fun add(roleAddParam: RoleAddParam): RoleVo?

    fun update(roleUpdateParam: RoleUpdateParam): RoleVo?

    fun changeStatus(roleChangeStatusParam: RoleChangeStatusParam): Boolean
}
