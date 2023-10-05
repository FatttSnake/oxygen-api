package top.fatweb.api.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.User
import top.fatweb.api.mapper.UserMapper
import top.fatweb.api.service.IUserService

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-04
 */
@Service
class UserServiceImpl : ServiceImpl<UserMapper, User>(), IUserService
