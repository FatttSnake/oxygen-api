package top.fatweb.api.converter

import top.fatweb.api.entity.permission.User
import top.fatweb.api.param.authentication.LoginParam
import top.fatweb.api.vo.authentication.*

object UserConverter {
    fun loginParamToUser(loginParam: LoginParam): User {
        val user = User().apply {
            username = loginParam.username
            password = loginParam.password
        }

        return user
    }

    fun userToUserInfoVo(user: User) = UserInfoVo(
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
                parentId = it.parentId
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
}