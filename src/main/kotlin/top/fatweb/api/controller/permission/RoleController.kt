package top.fatweb.api.controller.permission

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import top.fatweb.api.entity.common.ResponseCode
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.param.permission.role.*
import top.fatweb.api.service.permission.IRoleService
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.RoleWithPowerVo
import top.fatweb.api.vo.permission.base.RoleVo

/**
 * Role controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Tag(name = "角色管理", description = "角色管理相关接口")
@RestController
@RequestMapping("/system/role")
class RoleController(
    private val roleService: IRoleService
) {
    /**
     * Get role by ID
     *
     * @param id Role ID
     * @return Response object includes role information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see RoleWithPowerVo
     */
    @Operation(summary = "获取单个角色")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('system:role:query:one')")
    fun getOne(@PathVariable id: Long): ResponseResult<RoleWithPowerVo> {
        return ResponseResult.databaseSuccess(
            data = roleService.getOne(id)
        )
    }

    /**
     * Get role paging information
     *
     * @param roleGetParam Get role parameters
     * @return Response object includes role paging information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see RoleGetParam
     * @see ResponseResult
     * @see RoleWithPowerVo
     */
    @Operation(summary = "获取角色")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('system:role:query:all')")
    fun get(roleGetParam: RoleGetParam?): ResponseResult<PageVo<RoleWithPowerVo>> {
        return ResponseResult.databaseSuccess(
            data = roleService.getPage(roleGetParam)
        )
    }

    /**
     * Get role list
     *
     * @return Response object includes role list
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see RoleVo
     */
    @Operation(summary = "获取角色列表")
    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('system:role:query:list', 'system:group:add:one', 'system:group:modify:one', 'system:user:add:one', 'system:user:modify:one')")
    fun list(): ResponseResult<List<RoleVo>> {
        return ResponseResult.databaseSuccess(
            data = roleService.listAll()
        )
    }

    /**
     * Add role
     *
     * @param roleAddParam Add role parameters
     * @return Response object includes role information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see RoleAddParam
     * @see ResponseResult
     * @see RoleVo
     */
    @Operation(summary = "添加角色")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('system:role:add:one')")
    fun add(@Valid @RequestBody roleAddParam: RoleAddParam): ResponseResult<RoleVo> {
        return roleService.add(roleAddParam)?.let {
            ResponseResult.databaseSuccess(
                ResponseCode.DATABASE_INSERT_SUCCESS, data = it
            )
        } ?: let { ResponseResult.databaseFail(ResponseCode.DATABASE_INSERT_FAILED) }
    }

    /**
     * Update role
     *
     * @param roleUpdateParam Update role parameters
     * @return Response object includes role information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see RoleUpdateParam
     * @see ResponseResult
     * @see RoleVo
     */
    @Operation(summary = "修改角色")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('system:role:modify:one')")
    fun update(@Valid @RequestBody roleUpdateParam: RoleUpdateParam): ResponseResult<RoleVo> {
        return roleService.update(roleUpdateParam)?.let {
            ResponseResult.databaseSuccess(
                ResponseCode.DATABASE_UPDATE_SUCCESS, data = it
            )
        } ?: let { ResponseResult.databaseFail(ResponseCode.DATABASE_UPDATE_FILED) }
    }

    /**
     * Update status of role
     *
     * @param roleUpdateStatusParam Update status of role parameters
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see RoleUpdateStatusParam
     * @see ResponseResult
     */
    @Operation(summary = "修改角色状态")
    @PatchMapping
    @PreAuthorize("hasAnyAuthority('system:role:modify:status')")
    fun status(@Valid @RequestBody roleUpdateStatusParam: RoleUpdateStatusParam): ResponseResult<Nothing> {
        return if (roleService.status(roleUpdateStatusParam)) {
            ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
        } else {
            ResponseResult.databaseFail(ResponseCode.DATABASE_UPDATE_FILED)
        }
    }

    /**
     * Delete role by ID
     *
     * @param id
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     */
    @Operation(summary = "删除角色")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('system:role:delete:one')")
    fun delete(@PathVariable id: Long): ResponseResult<Nothing> {
        roleService.deleteOne(id)
        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
    }

    /**
     * Delete role by list
     *
     * @param roleDeleteParam Delete role parameters
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see RoleDeleteParam
     * @see ResponseResult
     */
    @Operation(summary = "批量删除角色")
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('system:role:delete:multiple')")
    fun deleteList(@Valid @RequestBody roleDeleteParam: RoleDeleteParam): ResponseResult<Nothing> {
        roleService.delete(roleDeleteParam)
        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
    }
}