package top.fatweb.api.service.permission

import jakarta.servlet.http.HttpServletRequest
import top.fatweb.api.entity.permission.User
import top.fatweb.api.param.permission.*
import top.fatweb.api.vo.permission.LoginVo
import top.fatweb.api.vo.permission.RegisterVo
import top.fatweb.api.vo.permission.TokenVo

/**
 * Authentication service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
interface IAuthenticationService {
    /**
     * Register
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun register(registerParam: RegisterParam): RegisterVo

    /**
     * Send verify email
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun resend()

    /**
     * Verify email
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun verify(verifyParam: VerifyParam)

    /**
     * Forget password
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun forget(request: HttpServletRequest, forgetParam: ForgetParam)

    /**
     * Retrieve password
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun retrieve(request: HttpServletRequest, retrieveParam: RetrieveParam)

    /**
     * Login
     *
     * @param request
     * @param loginParam Login parameters
     * @return LoginVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see HttpServletRequest
     * @see User
     * @see LoginVo
     */
    fun login(request: HttpServletRequest, loginParam: LoginParam): LoginVo

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