package top.fatweb.api.converter.permission

import top.fatweb.api.entity.permission.UserInfo
import top.fatweb.api.vo.permission.base.UserInfoVo

/**
 * User information converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object UserInfoConverter {
    fun userInfoToUserInfoVo(userInfo: UserInfo) = UserInfoVo(
        id = userInfo.id,
        userId = userInfo.userId,
        nickname = userInfo.nickname,
        avatar = userInfo.avatar,
        email = userInfo.email,
        createTime = userInfo.createTime,
        updateTime = userInfo.updateTime
    )
}