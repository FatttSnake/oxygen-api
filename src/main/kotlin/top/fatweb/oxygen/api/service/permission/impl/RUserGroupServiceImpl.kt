package top.fatweb.oxygen.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.entity.permission.RUserGroup
import top.fatweb.oxygen.api.mapper.permission.RUserGroupMapper
import top.fatweb.oxygen.api.service.permission.IRUserGroupService

/**
 * User group intermediate service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see RUserGroupMapper
 * @see RUserGroup
 * @see IRUserGroupService
 */
@Service
class RUserGroupServiceImpl : ServiceImpl<RUserGroupMapper, RUserGroup>(), IRUserGroupService