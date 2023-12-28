package top.fatweb.oxygen.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.entity.permission.UserRole
import top.fatweb.oxygen.api.mapper.permission.UserRoleMapper
import top.fatweb.oxygen.api.service.permission.IUserRoleService

/**
 * User role intermediate service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see UserRoleMapper
 * @see IUserRoleService
 */
@Service
class UserRoleServiceImpl : ServiceImpl<UserRoleMapper, UserRole>(), IUserRoleService
