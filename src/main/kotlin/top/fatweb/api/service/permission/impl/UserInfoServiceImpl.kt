package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.UserInfo
import top.fatweb.api.mapper.permission.UserInfoMapper
import top.fatweb.api.service.permission.IUserInfoService

/**
 * <p>
 * 用户资料表 服务实现类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-30
 */
@Service
class UserInfoServiceImpl : ServiceImpl<UserInfoMapper, UserInfo>(), IUserInfoService
