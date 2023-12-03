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
    /**
     * Get current user information
     *
     * @return Response object includes user information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see UserWithPowerInfoVo
     */
    @Operation(summary = "获取当前用户信息")
    @GetMapping("info")
    fun getInfo(): ResponseResult<UserWithPowerInfoVo> =
        userService.getInfo()?.let {
            ResponseResult.databaseSuccess(data = it)
        } ?: let { ResponseResult.databaseFail() }

    /**
     * Get user by ID
     *
     * @param id User ID
     * @return Response object includes user information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see UserWithRoleInfoVo
     */
    @Operation(summary = "获取单个用户")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('system:user:query:one')")
    fun getOne(@PathVariable id: Long): ResponseResult<UserWithRoleInfoVo> =
        userService.getOne(id)?.let {
            ResponseResult.databaseSuccess(data = it)
        } ?: let {
            throw NoRecordFoundException()
        }

    /**
     * Get user paging information
     *
     * @param userGetParam Get user parameters
     * @return Response object includes user paging information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserGetParam
     * @see ResponseResult
     * @see UserWithRoleInfoVo
     */
    @Operation(summary = "获取用户")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('system:user:query:all')")
    fun get(@Valid userGetParam: UserGetParam?): ResponseResult<PageVo<UserWithRoleInfoVo>> =
        ResponseResult.databaseSuccess(
            data = userService.getPage(userGetParam)
        )

    /**
     * Add user
     *
     * @param userAddParam Add user parameters
     * @return Response object includes user information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserAddParam
     * @see ResponseResult
     * @see UserWithPasswordRoleInfoVo
     */
    @Operation(summary = "添加用户")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('system:user:add:one')")
    fun add(@Valid @RequestBody userAddParam: UserAddParam): ResponseResult<UserWithPasswordRoleInfoVo> =
        userService.add(userAddParam)?.let {
            ResponseResult.databaseSuccess(
                ResponseCode.DATABASE_INSERT_SUCCESS, data = it
            )
        } ?: let { ResponseResult.databaseFail(ResponseCode.DATABASE_INSERT_FAILED) }

    /**
     * Update user
     *
     * @param userUpdateParam Update user parameters
     * @return Response object includes user information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserUpdateParam
     * @see ResponseResult
     * @see UserWithRoleInfoVo
     */
    @Operation(summary = "修改用户")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('system:user:modify:one')")
    fun update(@Valid @RequestBody userUpdateParam: UserUpdateParam): ResponseResult<UserWithRoleInfoVo> =
        userService.update(userUpdateParam)?.let {
            ResponseResult.databaseSuccess(
                ResponseCode.DATABASE_UPDATE_SUCCESS, data = it
            )
        } ?: let { ResponseResult.databaseFail(ResponseCode.DATABASE_UPDATE_FILED) }

    /**
     * Update user password
     *
     * @param userUpdatePasswordParam Update user password parameters
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserUpdatePasswordParam
     * @see ResponseResult
     */
    @Operation(summary = "修改密码")
    @PatchMapping
    @PreAuthorize("hasAnyAuthority('system:user:modify:password')")
    fun password(@Valid @RequestBody userUpdatePasswordParam: UserUpdatePasswordParam): ResponseResult<Nothing> {
        userService.password(userUpdatePasswordParam)
        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

    /**
     * Delete user by ID
     *
     * @param id User ID
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     */
    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('system:user:delete:one')")
    fun delete(@PathVariable id: Long): ResponseResult<Nothing> {
        userService.deleteOne(id)
        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
    }

    /**
     * Delete user by list
     *
     * @param userDeleteParam Delete user parameters
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserDeleteParam
     * @see ResponseResult
     */
    @Operation(summary = "批量删除用户")
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('system:user:delete:multiple')")
    fun deleteList(@Valid @RequestBody userDeleteParam: UserDeleteParam): ResponseResult<Nothing> {
        userService.delete(userDeleteParam)
        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
    }
}