package top.fatweb.oxygen.api.service.permission

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.permission.User
import top.fatweb.oxygen.api.param.permission.user.*
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.permission.UserWithInfoVo
import top.fatweb.oxygen.api.vo.permission.UserWithPasswordRoleInfoVo
import top.fatweb.oxygen.api.vo.permission.UserWithPowerInfoVo
import top.fatweb.oxygen.api.vo.permission.UserWithRoleInfoVo

/**
 * User service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IService
 * @see User
 */
interface IUserService : IService<User> {
    /**
     * Get user with power by username or email
     *
     * @param account Username or email
     * @return User object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see User
     */
    fun getUserWithPowerByAccount(account: String): User?

    /**
     * Get user information
     *
     * @return UserWithPowerInfoVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserWithPowerInfoVo
     */
    fun getInfo(): UserWithPowerInfoVo

    /**
     * Update user information
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun updateInfo(userInfoUpdateParam: UserInfoUpdateParam): Boolean

    /**
     * Get one user by ID
     *
     * @param id ID
     * @return UserWithRoleInfoVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserWithRoleInfoVo
     */
    fun getOne(id: Long): UserWithRoleInfoVo

    /**
     * Get user in page
     *
     * @param userGetParam Get user parameters
     * @return PageVo<UserWithRoleInfoVo> object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserGetParam
     * @see PageVo
     * @see UserWithRoleInfoVo
     */
    fun getPage(userGetParam: UserGetParam?): PageVo<UserWithRoleInfoVo>

    /**
     * Get all user as list
     *
     * @return List of UserWithInfoVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserWithInfoVo
     */
    fun getList(): List<UserWithInfoVo>

    /**
     * Add user
     *
     * @param userAddParam Add user parameters
     * @return UserWithPasswordRoleInfoVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserAddParam
     * @see UserWithPasswordRoleInfoVo
     */
    fun add(userAddParam: UserAddParam): UserWithPasswordRoleInfoVo

    /**
     * Update user
     *
     * @param userUpdateParam Update user parameters
     * @return UserWithRoleInfoVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserUpdateParam
     * @see UserWithRoleInfoVo
     */
    fun update(userUpdateParam: UserUpdateParam): UserWithRoleInfoVo

    /**
     * Update user password
     *
     * @param userUpdatePasswordParam Update user password parameters
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserUpdatePasswordParam
     */
    fun password(userUpdatePasswordParam: UserUpdatePasswordParam)

    /**
     * Delete user by ID
     *
     * @param id ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun deleteOne(id: Long)

    /**
     * Delete user by list
     *
     * @param userDeleteParam Delete user parameters
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserDeleteParam
     */
    fun delete(userDeleteParam: UserDeleteParam)

    /**
     * Get user IDs list by list of role IDs
     *
     * @param roleIds List of role IDs
     * @return User IDs list
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun getIdsWithRoleIds(roleIds: List<Long>): List<Long>

    /**
     * Get user IDs list by list of group IDs
     *
     * @param groupIds List of group IDs
     * @return User IDs list
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun getIdsWithGroupIds(groupIds: List<Long>): List<Long>
}
