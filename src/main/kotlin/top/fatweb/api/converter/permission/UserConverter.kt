package top.fatweb.api.converter.permission

import top.fatweb.api.entity.permission.User
import top.fatweb.api.param.authentication.LoginParam
import top.fatweb.api.vo.permission.*

/**
 * User converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object UserConverter {
    fun loginParamToUser(loginParam: LoginParam) = User().apply {
        username = loginParam.username
        password = loginParam.password
    }

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
        elements = user.elements?.map {
            ElementConverter.elementToElementVo(it)
        },
        operations = user.operations?.map {
            OperationConverter.operationToOperationVo(it)
        }
    )

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
}