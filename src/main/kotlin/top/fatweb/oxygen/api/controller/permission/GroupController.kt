package top.fatweb.oxygen.api.controller.permission

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.annotation.Trim
import top.fatweb.oxygen.api.entity.common.ResponseCode
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.param.permission.group.*
import top.fatweb.oxygen.api.service.permission.IGroupService
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.permission.GroupWithRoleVo
import top.fatweb.oxygen.api.vo.permission.base.GroupVo

/**
 * Group management controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IGroupService
 */
@BaseController(path = ["/system/group"], name = "用户组管理", description = "用户组管理相关接口")
class GroupController(
    val groupService: IGroupService
) {
    /**
     * Get group by ID
     *
     * @param id Group ID
     * @return Response object includes group information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see GroupWithRoleVo
     */
    @Operation(summary = "获取单个用户组")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('system:group:query:one')")
    fun getOne(@PathVariable id: Long): ResponseResult<GroupWithRoleVo> =
        ResponseResult.databaseSuccess(data = groupService.getOne(id))

    /**
     * Get group paging information
     *
     * @param groupGetParam Get group parameters
     * @return Response object includes group paging information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see GroupGetParam
     * @see ResponseResult
     * @see PageVo
     * @see GroupWithRoleVo
     */
    @Trim
    @Operation(summary = "获取用户组")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('system:group:query:all')")
    fun get(@Valid groupGetParam: GroupGetParam?): ResponseResult<PageVo<GroupWithRoleVo>> =
        ResponseResult.databaseSuccess(
            data = groupService.getPage(groupGetParam)
        )

    /**
     * Get group list
     *
     * @return Response object includes group list
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see GroupVo
     */
    @Operation(summary = "获取用户组列表")
    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('system:group:query:list', 'system:user:add:one', 'system:user:modify:one')")
    fun list(): ResponseResult<List<GroupVo>> =
        ResponseResult.databaseSuccess(
            data = groupService.getList()
        )

    /**
     * Add group
     *
     * @param groupAddParam Add group parameters
     * @return Response object includes group information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see GroupAddParam
     * @see ResponseResult
     * @see GroupVo
     */
    @Trim
    @Operation(summary = "添加用户组")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('system:group:add:one')")
    fun add(@Valid @RequestBody groupAddParam: GroupAddParam): ResponseResult<GroupVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_INSERT_SUCCESS, data = groupService.add(groupAddParam)
        )

    /**
     * Update group
     *
     * @param groupUpdateParam Update group parameters
     * @return Response object includes group information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see GroupUpdateParam
     * @see ResponseResult
     * @see GroupVo
     */
    @Trim
    @Operation(summary = "修改用户组")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('system:group:modify:one')")
    fun update(@Valid @RequestBody groupUpdateParam: GroupUpdateParam): ResponseResult<GroupVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_UPDATE_SUCCESS, data = groupService.update(groupUpdateParam)
        )

    /**
     * Update status of group
     *
     * @param groupUpdateStatusParam Update status of group parameters
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see GroupUpdateStatusParam
     * @see ResponseResult
     */
    @Operation(summary = "修改用户组状态")
    @PatchMapping
    @PreAuthorize("hasAnyAuthority('system:group:modify:status')")
    fun updateStatus(@Valid @RequestBody groupUpdateStatusParam: GroupUpdateStatusParam): ResponseResult<Nothing> {
        groupService.status(groupUpdateStatusParam)
        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

    /**
     * Delete group by ID
     *
     * @param id Group ID
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     */
    @Operation(summary = "删除用户组")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('system:group:delete:one')")
    fun delete(@PathVariable id: Long): ResponseResult<Nothing> {
        groupService.deleteOne(id)
        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
    }

    /**
     * Delete group by list
     *
     * @param groupDeleteParam Delete group parameters
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see GroupDeleteParam
     * @see ResponseResult
     */
    @Operation(summary = "批量删除用户组")
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('system:group:delete:multiple')")
    fun deleteList(@Valid @RequestBody groupDeleteParam: GroupDeleteParam): ResponseResult<Nothing> {
        groupService.delete(groupDeleteParam)
        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
    }
}