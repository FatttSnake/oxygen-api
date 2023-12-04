package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.UserInfo
import top.fatweb.api.mapper.permission.UserInfoMapper
import top.fatweb.api.service.permission.IUserInfoService

/**
 * User information service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see UserInfoMapper
 * @see IUserInfoService
 */
@Service
class UserInfoServiceImpl : ServiceImpl<UserInfoMapper, UserInfo>(), IUserInfoService
