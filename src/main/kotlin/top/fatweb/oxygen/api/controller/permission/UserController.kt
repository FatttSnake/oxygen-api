package top.fatweb.oxygen.api.controller.permission

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.annotation.Trim
import top.fatweb.oxygen.api.entity.common.ResponseCode
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.param.permission.user.*
import top.fatweb.oxygen.api.service.permission.IUserService
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.permission.UserWithInfoVo
import top.fatweb.oxygen.api.vo.permission.UserWithPasswordRoleInfoVo
import top.fatweb.oxygen.api.vo.permission.UserWithPowerInfoVo
import top.fatweb.oxygen.api.vo.permission.UserWithRoleInfoVo

/**
 * User management controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IUserService
 */
@BaseController(path = ["/system/user"], name = "用户管理", description = "用户管理相关接口")
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
    @GetMapping("/info")
    fun getInfo(): ResponseResult<UserWithPowerInfoVo> =
        ResponseResult.databaseSuccess(data = userService.getInfo())
    
    /**
     * Get basic user information
     *
     * @param username Username
     * @return Response object includes user basic information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see UserWithPowerInfoVo
     */
    @Trim
    @Operation(summary = "获取指定用户基本信息")
    @GetMapping("/info/{username}")
    fun getBasicInfo(@PathVariable username: String): ResponseResult<UserWithInfoVo> =
        ResponseResult.databaseSuccess(data = userService.getBasicInfo(username.trim()))

    /**
     * Update current user information
     *
     * @param userInfoUpdateParam Update user information parameters
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserInfoUpdateParam
     * @see ResponseResult
     */
    @Trim
    @Operation(summary = "更新当前用户信息")
    @PatchMapping("info")
    fun updateInfo(@RequestBody @Valid userInfoUpdateParam: UserInfoUpdateParam): ResponseResult<Nothing> =
        if (userService.updateInfo(userInfoUpdateParam)) ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
        else ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_FAILED)

    /**
     * Change password
     *
     * @param userChangePasswordParam User change password parameters
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserChangePasswordParam
     * @see ResponseResult
     */
    @Operation(summary = "更改密码")
    @PostMapping("info")
    fun password(@RequestBody @Valid userChangePasswordParam: UserChangePasswordParam): ResponseResult<Nothing> {
        userService.password(userChangePasswordParam)

        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

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
        ResponseResult.databaseSuccess(data = userService.getOne(id))

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
    @Trim
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
    @Trim
    @Operation(summary = "添加用户")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('system:user:add:one')")
    fun add(@Valid @RequestBody userAddParam: UserAddParam): ResponseResult<UserWithPasswordRoleInfoVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_INSERT_SUCCESS, data = userService.add(userAddParam)
        )

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
    @Trim
    @Operation(summary = "修改用户")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('system:user:modify:one')")
    fun update(@Valid @RequestBody userUpdateParam: UserUpdateParam): ResponseResult<UserWithRoleInfoVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_UPDATE_SUCCESS, data = userService.update(userUpdateParam)
        )

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