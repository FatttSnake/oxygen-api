package top.fatweb.oxygen.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.entity.permission.PowerRole
import top.fatweb.oxygen.api.mapper.permission.PowerRoleMapper
import top.fatweb.oxygen.api.service.permission.IPowerRoleService

/**
 * Power role intermediate service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see PowerRoleMapper
 * @see PowerRole
 * @see IPowerRoleService
 */
@Service
class PowerRoleServiceImpl : ServiceImpl<PowerRoleMapper, PowerRole>(), IPowerRoleService
