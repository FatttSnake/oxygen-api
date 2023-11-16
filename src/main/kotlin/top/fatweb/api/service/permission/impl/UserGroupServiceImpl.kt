package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.UserGroup
import top.fatweb.api.mapper.permission.UserGroupMapper
import top.fatweb.api.service.permission.IUserGroupService

/**
 * User group intermediate service implement
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@Service
class UserGroupServiceImpl : ServiceImpl<UserGroupMapper, UserGroup>(), IUserGroupService
