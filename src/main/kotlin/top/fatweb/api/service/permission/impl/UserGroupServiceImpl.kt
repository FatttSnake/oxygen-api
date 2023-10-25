package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.UserGroup
import top.fatweb.api.mapper.permission.UserGroupMapper
import top.fatweb.api.service.permission.IUserGroupService

/**
 * <p>
 * 中间表-用户-用户组 服务实现类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
@Service
class UserGroupServiceImpl : ServiceImpl<UserGroupMapper, UserGroup>(), IUserGroupService
