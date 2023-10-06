package top.fatweb.api.converter

import org.springframework.stereotype.Component
import top.fatweb.api.entity.permission.User
import top.fatweb.api.param.LoginParam

@Component
object UserConverter {
    fun loginParamToUser(loginParam: LoginParam): User {
        val user = User().apply {
            username = loginParam.username
            password = loginParam.password
        }

        return user
    }
}