package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.UserRole
import top.fatweb.api.mapper.permission.UserRoleMapper
import top.fatweb.api.service.permission.IUserRoleService

/**
 * <p>
 * 中间表-用户-角色 服务实现类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
@Service
class UserRoleServiceImpl : ServiceImpl<UserRoleMapper, UserRole>(), IUserRoleService
