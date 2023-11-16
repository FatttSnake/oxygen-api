package top.fatweb.api.controller.permission

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.fatweb.api.converter.permission.UserConverter
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.service.permission.IUserService
import top.fatweb.api.vo.permission.UserWithPowerInfoVo
import top.fatweb.api.vo.permission.UserWithRoleInfoVo

/**
 * User controller
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@Tag(name = "用户管理", description = "用户管理相关接口")
@RestController
@RequestMapping("/system/user")
class UserController(
    private val userService: IUserService
) {
    @Operation(summary = "获取当前用户信息")
    @GetMapping("info")
    fun getInfo(): ResponseResult<UserWithPowerInfoVo> {
        userService.getInfo()?.let {
            return ResponseResult.databaseSuccess(data = UserConverter.userToUserWithPowerInfoVo(it))
        } ?: let { return ResponseResult.databaseFail() }
    }

    @Operation(summary = "获取用户列表")
    @GetMapping
    fun get(): ResponseResult<List<UserWithRoleInfoVo>> {
        return ResponseResult.databaseSuccess(
            data = userService.getList().map { UserConverter.userToUserWithRoleInfoVo(it) })
    }
}

