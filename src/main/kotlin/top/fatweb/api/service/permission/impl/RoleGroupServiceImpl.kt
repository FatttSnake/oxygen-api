package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.RoleGroup
import top.fatweb.api.mapper.permission.RoleGroupMapper
import top.fatweb.api.service.permission.IRoleGroupService

/**
 * Role group intermediate service implement
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@Service
class RoleGroupServiceImpl : ServiceImpl<RoleGroupMapper, RoleGroup>(), IRoleGroupService
