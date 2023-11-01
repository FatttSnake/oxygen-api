package top.fatweb.api.controller.permission

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.fatweb.api.converter.permission.UserConverter
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.service.permission.IUserService
import top.fatweb.api.vo.permission.UserWithPowerInfoVo
import top.fatweb.api.vo.permission.UserWithRoleInfoVo

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-04
 */
@RestController
@RequestMapping("/system/user")
class UserController(
    private val userService: IUserService
) {
    @GetMapping("info")
    fun getInfo(): ResponseResult<UserWithPowerInfoVo> {
        userService.getInfo()?.let {
            return ResponseResult.databaseSuccess(data = UserConverter.userToUserWithPowerInfoVo(it))
        } ?: let { return ResponseResult.databaseFail() }
    }

    @GetMapping
    fun get(): ResponseResult<List<UserWithRoleInfoVo>> {
        return ResponseResult.databaseSuccess(
            data = userService.getList().map { UserConverter.userToUserWithRoleInfoVo(it) })
    }
}

