package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.PowerRole
import top.fatweb.api.mapper.permission.PowerRoleMapper
import top.fatweb.api.service.permission.IPowerRoleService

/**
 * <p>
 * 中间表-权限-角色 服务实现类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
@Service
class PowerRoleServiceImpl : ServiceImpl<PowerRoleMapper, PowerRole>(), IPowerRoleService
