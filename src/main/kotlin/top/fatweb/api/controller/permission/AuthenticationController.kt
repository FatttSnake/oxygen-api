package top.fatweb.api.controller.permission

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import top.fatweb.api.annotation.ApiVersion
import top.fatweb.api.converter.UserConverter
import top.fatweb.api.entity.common.ResponseCode
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.param.LoginParam
import top.fatweb.api.service.permission.IAuthenticationService
import top.fatweb.api.util.WebUtil

@Tag(name = "身份认证", description = "身份认证相关接口")
@Suppress("MVCPathVariableInspection")
@RequestMapping("/api/{apiVersion}")
@ApiVersion(2)
@RestController
class AuthenticationController(val authenticationService: IAuthenticationService, val userConverter: UserConverter) {
    @Operation(summary = "登录")
    @PostMapping("/login")
    fun login(@Valid @RequestBody loginParam: LoginParam) =
        ResponseResult.success(
            ResponseCode.SYSTEM_LOGIN_SUCCESS,
            "Login success",
            authenticationService.login(userConverter.loginParamToUser(loginParam))
        )

    @Operation(summary = "登出")
    @PostMapping("/logout")
    fun logout(request: HttpServletRequest) =
        when (authenticationService.logout(WebUtil.getToken(request))) {
            true -> ResponseResult.success(ResponseCode.SYSTEM_LOGOUT_SUCCESS, "Logout success", null)
            false -> ResponseResult.fail(ResponseCode.SYSTEM_LOGOUT_FAILED, "Logout failed", null)
        }

    @Operation(summary = "更新 Token")
    @GetMapping("/token")
    fun renewToken(request: HttpServletRequest) =
        ResponseResult.success(
            ResponseCode.SYSTEM_TOKEN_RENEW_SUCCESS,
            "Token renew success",
            authenticationService.renewToken(WebUtil.getToken(request))
        )
}