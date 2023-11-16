package top.fatweb.api.service.permission

import jakarta.servlet.http.HttpServletRequest
import top.fatweb.api.entity.permission.User
import top.fatweb.api.vo.permission.LoginVo
import top.fatweb.api.vo.permission.TokenVo

/**
 * Authentication service interface
 *
 * @author FatttSnake
 * @since 1.0.0
 */
interface IAuthenticationService {
    fun login(request: HttpServletRequest, user: User): LoginVo

    fun logout(token: String): Boolean

    fun renewToken(token: String): TokenVo
}