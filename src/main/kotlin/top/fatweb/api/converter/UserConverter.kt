package top.fatweb.api.converter

import top.fatweb.api.entity.permission.User
import top.fatweb.api.param.authentication.LoginParam

object UserConverter {
    fun loginParamToUser(loginParam: LoginParam): User {
        val user = User().apply {
            username = loginParam.username
            password = loginParam.password
        }

        return user
    }
}