package top.fatweb.api.service.permission

import top.fatweb.api.entity.permission.User

interface IAuthenticationService {
    fun login(user: User): HashMap<String, String>

    fun logout(token: String): Boolean

    fun renewToken(token: String): HashMap<String, String>
}