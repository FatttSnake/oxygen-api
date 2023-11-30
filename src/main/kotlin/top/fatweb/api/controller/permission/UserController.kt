package top.fatweb.api.controller.permission

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import top.fatweb.api.entity.common.ResponseCode
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.exception.NoRecordFoundException
import top.fatweb.api.param.permission.user.*
import top.fatweb.api.service.permission.IUserService
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.UserWithPasswordRoleInfoVo
import top.fatweb.api.vo.permission.UserWithPowerInfoVo
import top.fatweb.api.vo.permission.UserWithRoleInfoVo

/**
 * User controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
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
            return ResponseResult.databaseSuccess(data = it)
        } ?: let { return ResponseResult.databaseFail() }
    }

    @Operation(summary = "获取单个用户")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('system:user:query:one')")
    fun getOne(@PathVariable id: Long): ResponseResult<UserWithRoleInfoVo> {
        return userService.getOne(id)?.let {
            ResponseResult.databaseSuccess(data = it)
        } ?: let {
            throw NoRecordFoundException()
        }
    }

    @Operation(summary = "获取用户")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('system:user:query:all')")
    fun get(@Valid userGetParam: UserGetParam?): ResponseResult<PageVo<UserWithRoleInfoVo>> {
        return ResponseResult.databaseSuccess(
            data = userService.getPage(userGetParam)
        )
    }

    @Operation(summary = "添加用户")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('system:user:add:one')")
    fun add(@Valid @RequestBody userAddParam: UserAddParam): ResponseResult<UserWithPasswordRoleInfoVo> {
        return userService.add(userAddParam)?.let {
            ResponseResult.databaseSuccess(
                ResponseCode.DATABASE_INSERT_SUCCESS, data = it
            )
        } ?: let { ResponseResult.databaseFail(ResponseCode.DATABASE_INSERT_FAILED) }
    }

    @Operation(summary = "修改用户")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('system:user:modify:one')")
    fun update(@Valid @RequestBody userUpdateParam: UserUpdateParam): ResponseResult<UserWithRoleInfoVo> {
        return userService.update(userUpdateParam)?.let {
            ResponseResult.databaseSuccess(
                ResponseCode.DATABASE_UPDATE_SUCCESS, data = it
            )
        } ?: let { ResponseResult.databaseFail(ResponseCode.DATABASE_UPDATE_FILED) }
    }

    @Operation(summary = "修改密码")
    @PatchMapping
    @PreAuthorize("hasAnyAuthority('system:user:modify:password')")
    fun changePassword(@Valid @RequestBody userChangePasswordParam: UserChangePasswordParam): ResponseResult<Nothing> {
        userService.changePassword(userChangePasswordParam)
        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('system:user:delete:one')")
    fun delete(@PathVariable id: Long): ResponseResult<Nothing> {
        userService.deleteOne(id)
        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
    }

    @Operation(summary = "批量删除用户")
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('system:user:delete:multiple')")
    fun deleteList(@Valid @RequestBody userDeleteParam: UserDeleteParam): ResponseResult<Nothing> {
        userService.delete(userDeleteParam)
        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
    }
}

