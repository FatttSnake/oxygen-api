package top.fatweb.oxygen.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.entity.permission.RUserRole
import top.fatweb.oxygen.api.mapper.permission.RUserRoleMapper
import top.fatweb.oxygen.api.service.permission.IRUserRoleService

/**
 * User role intermediate service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see RUserRoleMapper
 * @see IRUserRoleService
 */
@Service
class RUserRoleServiceImpl : ServiceImpl<RUserRoleMapper, RUserRole>(), IRUserRoleService
