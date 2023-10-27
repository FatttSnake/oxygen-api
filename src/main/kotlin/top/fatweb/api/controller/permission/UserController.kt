package top.fatweb.api.controller.permission

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.fatweb.api.converter.UserConverter
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.service.permission.IUserService
import top.fatweb.api.vo.authentication.UserInfoVo

/**
 * <p>
 * 用户 前端控制器
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
    fun getInfo(): ResponseResult<UserInfoVo> {
        userService.getInfo()?.let {
            return ResponseResult.databaseSuccess(data = UserConverter.userToUserInfoVo(it))
        } ?: let { return ResponseResult.databaseFail() }
    }
}

