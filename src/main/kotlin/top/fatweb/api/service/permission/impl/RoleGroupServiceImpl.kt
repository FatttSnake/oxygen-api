package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.RoleGroup
import top.fatweb.api.mapper.permission.RoleGroupMapper
import top.fatweb.api.service.permission.IRoleGroupService

/**
 * <p>
 * 中间表-角色-用户组 服务实现类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
@Service
class RoleGroupServiceImpl : ServiceImpl<RoleGroupMapper, RoleGroup>(), IRoleGroupService
