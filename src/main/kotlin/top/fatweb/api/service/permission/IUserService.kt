package top.fatweb.api.service.permission

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.api.entity.permission.User
import top.fatweb.api.param.permission.user.UserAddParam
import top.fatweb.api.param.permission.user.UserDeleteParam
import top.fatweb.api.param.permission.user.UserGetParam
import top.fatweb.api.param.permission.user.UserUpdateParam
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.UserWithInfoVo
import top.fatweb.api.vo.permission.UserWithPasswordRoleInfoVo
import top.fatweb.api.vo.permission.UserWithPowerInfoVo
import top.fatweb.api.vo.permission.UserWithRoleInfoVo

/**
 * User service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
interface IUserService : IService<User> {
    fun getUserWithPowerByUsername(username: String): User?

    fun getInfo(): UserWithPowerInfoVo?

    fun getPage(userGetParam: UserGetParam?): PageVo<UserWithRoleInfoVo>

    fun getOne(id: Long): UserWithRoleInfoVo?

    fun getList(): List<UserWithInfoVo>

    fun add(userAddParam: UserAddParam): UserWithPasswordRoleInfoVo?

    fun update(userUpdateParam: UserUpdateParam): UserWithRoleInfoVo?

    fun deleteOne(id: Long)

    fun delete(userDeleteParam: UserDeleteParam)
}
