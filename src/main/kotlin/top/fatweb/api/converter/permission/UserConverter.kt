package top.fatweb.api.converter.permission

import top.fatweb.api.entity.permission.User
import top.fatweb.api.param.authentication.LoginParam
import top.fatweb.api.vo.permission.*

object UserConverter {
    fun loginParamToUser(loginParam: LoginParam): User {
        val user = User().apply {
            username = loginParam.username
            password = loginParam.password
        }

        return user
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
        userInfo = user.userInfo?.let { UserInfoVo(
            id = it.id,
            userId = it.userId,
            nickname = it.nickname,
            avatar = it.avatar,
            email = it.email,
            createTime = it.createTime,
            updateTime = it.updateTime
        ) },
        modules = user.modules?.map {
            ModuleVo(
                id = it.id,
                name = it.name,
                powerId = it.powerId
            )
        },
        menus = user.menus?.map {
            MenuVo(
                id = it.id,
                name = it.name,
                url = it.url,
                powerId = it.powerId,
                parentId = it.parentId,
                moduleId = it.moduleId
            )
        },
        elements = user.elements?.map {
            ElementVo(
                id = it.id,
                name = it.name,
                powerId = it.powerId,
                parentId = it.parentId,
                menuId = it.menuId
            )
        },
        operations = user.operations?.map {
            OperationVo(
                id = it.id,
                name = it.name,
                code = it.code,
                powerId = it.powerId,
                elementId = it.elementId
            )
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
        userInfo = user.userInfo?.let { UserInfoVo(
            id = it.id,
            userId = it.userId,
            nickname = it.nickname,
            avatar = it.avatar,
            email = it.email,
            createTime = it.createTime,
            updateTime = it.updateTime
        ) },
        roles = user.roles?.map {
            RoleVo(
                id = it.id,
                name = it.name,
                enable = it.enable == 1
            )
        },
        groups = user.groups?.map {
            GroupVo(
                id = it.id,
                name = it.name,
                enable = it.enable == 1
            )
        }
    )
}