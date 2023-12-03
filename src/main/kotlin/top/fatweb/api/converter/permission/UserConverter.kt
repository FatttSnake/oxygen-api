package top.fatweb.api.converter.permission

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.api.entity.permission.Group
import top.fatweb.api.entity.permission.Role
import top.fatweb.api.entity.permission.User
import top.fatweb.api.entity.permission.UserInfo
import top.fatweb.api.param.permission.LoginParam
import top.fatweb.api.param.permission.user.UserAddParam
import top.fatweb.api.param.permission.user.UserUpdateParam
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.UserWithInfoVo
import top.fatweb.api.vo.permission.UserWithPasswordRoleInfoVo
import top.fatweb.api.vo.permission.UserWithPowerInfoVo
import top.fatweb.api.vo.permission.UserWithRoleInfoVo
import top.fatweb.avatargenerator.GitHubAvatar

/**
 * User converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object UserConverter {
    /**
     * Convert LoginParam object into User object
     *
     * @param loginParam LoginParam object
     * @return User object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LoginParam
     * @see User
     */
    fun loginParamToUser(loginParam: LoginParam) = User().apply {
        username = loginParam.username
        password = loginParam.password
    }

    /**
     * Convert User object into UserWithPowerInfoVo object
     *
     * @param user User object
     * @return UserWithPowerInfoVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see User
     * @see UserWithPowerInfoVo
     */
    fun userToUserWithPowerInfoVo(user: User) = UserWithPowerInfoVo(
        id = user.id,
        username = user.username,
        locking = user.locking?.let { it == 1 },
        expiration = user.expiration,
        credentialsExpiration = user.credentialsExpiration,
        enable = user.enable?.let { it == 1 },
        currentLoginTime = user.currentLoginTime,
        currentLoginIp = user.currentLoginIp,
        lastLoginTime = user.lastLoginTime,
        lastLoginIp = user.lastLoginIp,
        createTime = user.createTime,
        updateTime = user.updateTime,
        userInfo = user.userInfo?.let {
            UserInfoConverter.userInfoToUserInfoVo(it)
        },
        modules = user.modules?.map {
            ModuleConverter.moduleToModuleVo(it)
        },
        menus = user.menus?.map {
            MenuConverter.menuToMenuVo(it)
        },
        funcs = user.funcs?.map {
            FuncConverter.funcToFuncVo(it)
        },
        operations = user.operations?.map {
            OperationConverter.operationToOperationVo(it)
        }
    )

    /**
     * Convert User object into UserWithRoleInfoVo object
     *
     * @param user User object
     * @return UserWithRoleInfoVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see User
     * @see UserWithRoleInfoVo
     */
    fun userToUserWithRoleInfoVo(user: User) = UserWithRoleInfoVo(
        id = user.id,
        username = user.username,
        locking = user.locking?.let { it == 1 },
        expiration = user.expiration,
        credentialsExpiration = user.credentialsExpiration,
        enable = user.enable?.let { it == 1 },
        currentLoginTime = user.currentLoginTime,
        currentLoginIp = user.currentLoginIp,
        lastLoginTime = user.lastLoginTime,
        lastLoginIp = user.lastLoginIp,
        createTime = user.createTime,
        updateTime = user.updateTime,
        userInfo = user.userInfo?.let {
            UserInfoConverter.userInfoToUserInfoVo(it)
        },
        roles = user.roles?.map {
            RoleConverter.roleToRoleVo(it)
        },
        groups = user.groups?.map {
            GroupConverter.groupToGroupVo(it)
        }
    )

    /**
     * Convert User object into UserWithInfoVo object
     *
     * @param user User object
     * @return UserWithInfoVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see User
     * @see UserWithInfoVo
     */
    fun userToUserWithInfoVo(user: User) = UserWithInfoVo(
        id = user.id,
        username = user.username,
        locking = user.locking?.let { it == 1 },
        expiration = user.expiration,
        credentialsExpiration = user.credentialsExpiration,
        enable = user.enable?.let { it == 1 },
        currentLoginTime = user.currentLoginTime,
        currentLoginIp = user.currentLoginIp,
        lastLoginTime = user.lastLoginTime,
        lastLoginIp = user.lastLoginIp,
        createTime = user.createTime,
        updateTime = user.updateTime,
        userInfo = user.userInfo?.let {
            UserInfoConverter.userInfoToUserInfoVo(it)
        }
    )

    /**
     * Convert User object into UserWithPasswordRoleInfoVo object
     *
     * @param user User object
     * @return UserWithPasswordRoleInfoVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see User
     * @see UserWithPasswordRoleInfoVo
     */
    fun userToUserWithPasswordRoleInfoVo(user: User) = UserWithPasswordRoleInfoVo(
        id = user.id,
        username = user.username,
        password = user.password,
        locking = user.locking?.let { it == 1 },
        expiration = user.expiration,
        credentialsExpiration = user.credentialsExpiration,
        enable = user.enable?.let { it == 1 },
        currentLoginTime = user.currentLoginTime,
        currentLoginIp = user.currentLoginIp,
        lastLoginTime = user.lastLoginTime,
        lastLoginIp = user.lastLoginIp,
        createTime = user.createTime,
        updateTime = user.updateTime,
        userInfo = user.userInfo?.let {
            UserInfoConverter.userInfoToUserInfoVo(it)
        },
        roles = user.roles?.map {
            RoleConverter.roleToRoleVo(it)
        },
        groups = user.groups?.map {
            GroupConverter.groupToGroupVo(it)
        }
    )

    /**
     * Convert UserAddParam object into User object
     *
     * @param userAddParam UserAddParam object
     * @return User object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserAddParam
     * @see User
     */
    fun userAddParamToUser(userAddParam: UserAddParam) = User().apply {
        username = userAddParam.username
        password = userAddParam.password
        locking = if (userAddParam.locking) 1 else 0
        expiration = userAddParam.expiration
        credentialsExpiration = userAddParam.credentialsExpiration
        enable = if (userAddParam.enable) 1 else 0
        userInfo = UserInfo().apply {
            nickname = userAddParam.nickname ?: userAddParam.username
            avatar = userAddParam.avatar ?: GitHubAvatar.newAvatarBuilder().build()
                .createAsBase64((Long.MIN_VALUE..Long.MAX_VALUE).random())
            email = userAddParam.email
        }
        roles = userAddParam.roleIds?.map { Role().apply { id = it } }
        groups = userAddParam.groupIds?.map { Group().apply { id = it } }
    }

    /**
     * Convert UserUpdateParam object into User object
     *
     * @param userUpdateParam UserUpdateParam object
     * @return User object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserUpdateParam
     * @see User
     */
    fun userUpdateParamToUser(userUpdateParam: UserUpdateParam) = User().apply {
        id = userUpdateParam.id
        username = userUpdateParam.username
        locking = if (userUpdateParam.locking && userUpdateParam.id != 0L) 1 else 0
        expiration = if (userUpdateParam.id != 0L) userUpdateParam.expiration else null
        credentialsExpiration = userUpdateParam.credentialsExpiration
        enable = if (userUpdateParam.enable || userUpdateParam.id == 0L) 1 else 0
        userInfo = UserInfo().apply {
            nickname = userUpdateParam.nickname
            avatar = userUpdateParam.avatar
            email = userUpdateParam.email
        }
        roles = if (userUpdateParam.id != 0L) userUpdateParam.roleIds?.map { Role().apply { id = it } } else null
        groups = if (userUpdateParam.id != 0L) userUpdateParam.groupIds?.map { Group().apply { id = it } } else null
    }

    /**
     * Convert IPage<User> object into PageVo<UserWithRoleInfoVo> object
     *
     * @param userPage IPage<User> object
     * @return PageVo<UserWithRoleInfoVo> object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see IPage
     * @see User
     * @see PageVo
     * @see UserWithRoleInfoVo
     */
    fun userPageToUserWithRoleInfoPageVo(userPage: IPage<User>) = PageVo(
        total = userPage.total,
        pages = userPage.pages,
        size = userPage.size,
        current = userPage.current,
        records = userPage.records.map {
            userToUserWithRoleInfoVo(it)
        }
    )
}