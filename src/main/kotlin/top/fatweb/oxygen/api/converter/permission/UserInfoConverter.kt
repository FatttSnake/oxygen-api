package top.fatweb.oxygen.api.converter.permission

import top.fatweb.oxygen.api.entity.permission.UserInfo
import top.fatweb.oxygen.api.vo.permission.base.UserInfoVo

/**
 * Convert to UserInfoVo object
 *
 * @return UserInfoVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see UserInfo
 * @see UserInfoVo
 */
fun UserInfo.toVo() = UserInfoVo(
    id = this.id,
    userId = this.userId,
    nickname = this.nickname,
    avatar = this.avatar,
    email = this.email,
    createTime = this.createTime,
    updateTime = this.updateTime
)
