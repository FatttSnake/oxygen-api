package top.fatweb.oxygen.api.converter.permission

import top.fatweb.oxygen.api.entity.permission.UserInfo
import top.fatweb.oxygen.api.vo.permission.base.UserInfoVo

/**
 * User information converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object UserInfoConverter {
    /**
     * Convert UserInfo object into UserInfoVo object
     *
     * @param userInfo UserInfo object
     * @return UserInfoVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserInfo
     * @see UserInfoVo
     */
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