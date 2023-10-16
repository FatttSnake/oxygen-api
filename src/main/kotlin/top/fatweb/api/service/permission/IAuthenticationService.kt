package top.fatweb.api.service.permission

import jakarta.servlet.http.HttpServletRequest
import top.fatweb.api.entity.permission.User
import top.fatweb.api.vo.LoginVo
import top.fatweb.api.vo.TokenVo

interface IAuthenticationService {
    fun login(request: HttpServletRequest, user: User): LoginVo

    fun logout(token: String): Boolean

    fun renewToken(token: String): TokenVo
}