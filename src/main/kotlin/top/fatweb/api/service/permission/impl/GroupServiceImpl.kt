package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.Group
import top.fatweb.api.mapper.permission.GroupMapper
import top.fatweb.api.service.permission.IGroupService

/**
 * <p>
 * 用户组 服务实现类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
@Service
class GroupServiceImpl : ServiceImpl<GroupMapper, Group>(), IGroupService
