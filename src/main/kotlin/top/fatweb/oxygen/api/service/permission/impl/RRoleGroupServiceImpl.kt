package top.fatweb.oxygen.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.entity.permission.RRoleGroup
import top.fatweb.oxygen.api.mapper.permission.RRoleGroupMapper
import top.fatweb.oxygen.api.service.permission.IRRoleGroupService

/**
 * Role group intermediate service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see RRoleGroupMapper
 * @see RRoleGroup
 * @see IRRoleGroupService
 */
@Service
class RRoleGroupServiceImpl : ServiceImpl<RRoleGroupMapper, RRoleGroup>(), IRRoleGroupService
