package top.fatweb.oxygen.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.entity.permission.UserGroup
import top.fatweb.oxygen.api.mapper.permission.UserGroupMapper
import top.fatweb.oxygen.api.service.permission.IUserGroupService

/**
 * User group intermediate service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see UserGroupMapper
 * @see UserGroup
 * @see IUserGroupService
 */
@Service
class UserGroupServiceImpl : ServiceImpl<UserGroupMapper, UserGroup>(), IUserGroupService
