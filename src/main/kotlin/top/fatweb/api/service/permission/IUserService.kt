package top.fatweb.api.service.permission

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.api.entity.permission.User
import top.fatweb.api.param.permission.user.*
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
 * @see IService
 * @see User
 */
interface IUserService : IService<User> {
    /**
     * Get user with power by username
     *
     * @param username Username
     * @return User object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see User
     */
    fun getUserWithPowerByUsername(username: String): User?

    /**
     * Get user information
     *
     * @return UserWithPowerInfoVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserWithPowerInfoVo
     */
    fun getInfo(): UserWithPowerInfoVo?

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
     * Get one user by ID
     *
     * @param id ID
     * @return UserWithRoleInfoVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserWithRoleInfoVo
     */
    fun getOne(id: Long): UserWithRoleInfoVo?

    /**
     * Get all user as list
     *
     * @return List of UserWithInfoVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserWithInfoVo
     */
    fun listAll(): List<UserWithInfoVo>

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
    fun add(userAddParam: UserAddParam): UserWithPasswordRoleInfoVo?

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
    fun update(userUpdateParam: UserUpdateParam): UserWithRoleInfoVo?

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
