package top.fatweb.api.controller.permission

import io.swagger.v3.oas.annotations.Operation
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import top.fatweb.api.annotation.BaseController
import top.fatweb.api.entity.common.ResponseCode
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.param.permission.*
import top.fatweb.api.service.permission.IAuthenticationService
import top.fatweb.api.util.WebUtil
import top.fatweb.api.vo.permission.LoginVo
import top.fatweb.api.vo.permission.RegisterVo
import top.fatweb.api.vo.permission.TokenVo

/**
 * Authentication controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IAuthenticationService
 */
@BaseController(name = "身份认证", description = "身份认证相关接口")
class AuthenticationController(
    private val authenticationService: IAuthenticationService
) {
    /**
     * Register
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Operation(summary = "注册")
    @PostMapping("/register")
    fun register(@Valid @RequestBody registerParam: RegisterParam): ResponseResult<RegisterVo> = ResponseResult.success(
        ResponseCode.PERMISSION_REGISTER_SUCCESS,
        data = authenticationService.register(registerParam)
    )


    /**
     * Send verify email
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Operation(summary = "发送验证邮件")
    @PostMapping("/resend")
    fun resend(): ResponseResult<Nothing> {
        authenticationService.resend()

        return ResponseResult.success(ResponseCode.PERMISSION_RESEND_SUCCESS)
    }

    /**
     * Verify email
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Operation(summary = "验证邮箱")
    @PostMapping("/verify")
    fun verify(@Valid @RequestBody verifyParam: VerifyParam): ResponseResult<Nothing> {
        authenticationService.verify(verifyParam)

        return ResponseResult.success(ResponseCode.PERMISSION_VERIFY_SUCCESS)
    }

    /**
     * Forget password
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Operation(summary = "忘记密码")
    @PostMapping("/forget")
    fun forget(request: HttpServletRequest, @Valid @RequestBody forgetParam: ForgetParam): ResponseResult<Nothing> {
        authenticationService.forget(request, forgetParam)

        return ResponseResult.success(ResponseCode.PERMISSION_FORGET_SUCCESS)
    }

    /**
     * Retrieve password
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Operation(summary = "找回密码")
    @PostMapping("/retrieve")
    fun retrieve(request: HttpServletRequest, @Valid @RequestBody retrieveParam: RetrieveParam): ResponseResult<Nothing> {
        authenticationService.retrieve(request, retrieveParam)

        return ResponseResult.success(ResponseCode.PERMISSION_RETRIEVE_SUCCESS)
    }

    /**
     * Login
     *
     * @param request
     * @param loginParam Login parameters
     * @return Response object includes login result
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see HttpServletRequest
     * @see LoginParam
     * @see ResponseResult
     * @see LoginVo
     */
    @Operation(summary = "登录")
    @PostMapping("/login")
    fun login(request: HttpServletRequest, @Valid @RequestBody loginParam: LoginParam): ResponseResult<LoginVo> =
        ResponseResult.success(
            ResponseCode.PERMISSION_LOGIN_SUCCESS,
            "Login success",
            authenticationService.login(request, loginParam)
        )

    /**
     * Logout
     *
     * @param request
     * @return Response object includes logout result
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see HttpServletRequest
     * @see ResponseResult
     */
    @Operation(summary = "登出")
    @PostMapping("/logout")
    fun logout(request: HttpServletRequest): ResponseResult<Nothing> =
        when (authenticationService.logout(WebUtil.getToken(request))) {
            true -> ResponseResult.success(ResponseCode.PERMISSION_LOGOUT_SUCCESS, "Logout success", null)
            false -> ResponseResult.fail(ResponseCode.PERMISSION_LOGOUT_FAILED, "Logout failed", null)
        }

    /**
     * Renew token
     *
     * @param request
     * @return Response object includes new token
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see HttpServletRequest
     * @see ResponseResult
     * @see TokenVo
     */
    @Operation(summary = "更新 Token")
    @GetMapping("/token")
    fun renewToken(request: HttpServletRequest): ResponseResult<TokenVo> = ResponseResult.success(
        ResponseCode.PERMISSION_TOKEN_RENEW_SUCCESS,
        "Token renew success",
        authenticationService.renewToken(WebUtil.getToken(request))
    )
}