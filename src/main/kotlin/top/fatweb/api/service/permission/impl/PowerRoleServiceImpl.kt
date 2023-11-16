package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.PowerRole
import top.fatweb.api.mapper.permission.PowerRoleMapper
import top.fatweb.api.service.permission.IPowerRoleService

/**
 * Power role intermediate service implement
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@Service
class PowerRoleServiceImpl : ServiceImpl<PowerRoleMapper, PowerRole>(), IPowerRoleService
