package top.fatweb.api.controller.permission

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.fatweb.api.annotation.ApiVersion
import top.fatweb.api.entity.common.ResponseCode
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.entity.converter.UserConverter
import top.fatweb.api.entity.param.LoginParam
import top.fatweb.api.service.permission.IAuthenticationService

@Tag(name = "身份认证", description = "身份认证相关接口")
@Suppress("MVCPathVariableInspection")
@RequestMapping("/api/{apiVersion}")
@ApiVersion(2)
@RestController
class AuthenticationController(val loginService: IAuthenticationService, val userConverter: UserConverter) {
    @Operation(summary = "登录")
    @PostMapping("/login")
    fun login(@Valid @RequestBody loginParam: LoginParam) =
        ResponseResult.success(
            ResponseCode.SYSTEM_LOGIN_SUCCESS,
            "Login success",
            loginService.login(userConverter.loginParamToUser(loginParam))
        )
}