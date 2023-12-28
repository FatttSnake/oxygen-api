package top.fatweb.oxygen.api.service.permission

import jakarta.servlet.http.HttpServletRequest
import top.fatweb.oxygen.api.entity.permission.User
import top.fatweb.oxygen.api.param.permission.*
import top.fatweb.oxygen.api.vo.permission.LoginVo
import top.fatweb.oxygen.api.vo.permission.RegisterVo
import top.fatweb.oxygen.api.vo.permission.TokenVo

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
     * @param registerParam Register parameters
     * @return RegisterVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see RegisterParam
     * @see RegisterVo
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
     * @param verifyParam Verify parameters
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see VerifyParam
     */
    fun verify(verifyParam: VerifyParam)

    /**
     * Forget password
     *
     * @param request
     * @param forgetParam Forget parameters
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see HttpServletRequest
     * @see ForgetParam
     */
    fun forget(request: HttpServletRequest, forgetParam: ForgetParam)

    /**
     * Retrieve password
     *
     * @param request
     * @param retrieveParam Retrieve parameters
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see HttpServletRequest
     * @see RetrieveParam
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