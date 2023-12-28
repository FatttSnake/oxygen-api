package top.fatweb.oxygen.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.entity.permission.RoleGroup
import top.fatweb.oxygen.api.mapper.permission.RoleGroupMapper
import top.fatweb.oxygen.api.service.permission.IRoleGroupService

/**
 * Role group intermediate service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see RoleGroupMapper
 * @see RoleGroup
 * @see IRoleGroupService
 */
@Service
class RoleGroupServiceImpl : ServiceImpl<RoleGroupMapper, RoleGroup>(), IRoleGroupService
