package top.fatweb.api.controller.permission

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import top.fatweb.api.annotation.ApiVersion
import top.fatweb.api.entity.common.ResponseCode
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.entity.permission.User
import top.fatweb.api.service.permission.IAuthenticationService

@Tag(name = "身份认证", description = "身份认证相关接口")
@RestController
@RequestMapping("/api/{apiVersion}")
@ApiVersion(2)
class AuthenticationController(val loginService: IAuthenticationService) {
    @Operation(summary = "登录")
    @PostMapping("/login")
    fun login(@PathVariable apiVersion: String, @RequestBody user: User) =
        ResponseResult.success(ResponseCode.SYSTEM_LOGIN_SUCCESS, "Login success", loginService.login(user))
}