package top.fatweb.oxygen.api.service.permission

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import top.fatweb.oxygen.api.entity.permission.User
import top.fatweb.oxygen.api.param.permission.*
import top.fatweb.oxygen.api.vo.permission.LoginVo
import top.fatweb.oxygen.api.vo.permission.RegisterVo
import top.fatweb.oxygen.api.vo.permission.TokenVo
import top.fatweb.oxygen.api.vo.permission.TwoFactorVo

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
     * @param request
     * @param response
     * @param registerParam Register parameters
     * @return RegisterVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see HttpServletRequest
     * @see HttpServletResponse
     * @see RegisterParam
     * @see RegisterVo
     */
    fun register(request: HttpServletRequest, response: HttpServletResponse, registerParam: RegisterParam): RegisterVo

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
     * @param response
     * @param loginParam Login parameters
     * @return LoginVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see HttpServletRequest
     * @see HttpServletResponse
     * @see User
     * @see LoginVo
     */
    fun login(request: HttpServletRequest, response: HttpServletResponse, loginParam: LoginParam): LoginVo

    /**
     * Create two-factor
     *
     * @return Two-factor QR code
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see TwoFactorVo
     */
    fun createTwoFactor(): TwoFactorVo

    /**
     * Validate two-factor
     *
     * @param twoFactorValidateParam Validate two-factor parameters
     * @return Result
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see TwoFactorValidateParam
     */
    fun validateTwoFactor(twoFactorValidateParam: TwoFactorValidateParam): Boolean

    /**
     * Remove two-factor
     *
     * @param twoFactorRemoveParam Remove two-factor parameters
     * @return Result
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see TwoFactorRemoveParam
     */
    fun removeTwoFactor(twoFactorRemoveParam: TwoFactorRemoveParam): Boolean

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
     * Refresh token
     *
     * @param response
     * @param refreshToken  Refresh token
     * @return TokenVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see HttpServletResponse
     * @see TokenVo
     */
    fun refreshToken(response: HttpServletResponse, refreshToken: String?): TokenVo
}