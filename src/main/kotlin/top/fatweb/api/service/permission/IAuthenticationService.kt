package top.fatweb.api.service.permission

import jakarta.servlet.http.HttpServletRequest
import top.fatweb.api.entity.permission.User
import top.fatweb.api.vo.permission.LoginVo
import top.fatweb.api.vo.permission.TokenVo

/**
 * Authentication service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
interface IAuthenticationService {
    /**
     * Login
     *
     * @param request
     * @param user User object
     * @return LoginVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see HttpServletRequest
     * @see User
     * @see LoginVo
     */
    fun login(request: HttpServletRequest, user: User): LoginVo

    /**
     * Logout
     *
     * @param token Token
     * @return Logout result
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun logout(token: String): Boolean

    /**
     * Renew token
     *
     * @param token Token
     * @return TokenVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see TokenVo
     */
    fun renewToken(token: String): TokenVo
}