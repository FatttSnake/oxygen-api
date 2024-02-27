package top.fatweb.oxygen.api.controller.tool

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.annotation.Trim
import top.fatweb.oxygen.api.entity.common.ResponseCode
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.param.tool.ToolBaseAddParam
import top.fatweb.oxygen.api.param.tool.ToolBaseUpdateParam
import top.fatweb.oxygen.api.service.tool.IToolBaseService
import top.fatweb.oxygen.api.vo.tool.ToolBaseVo

/**
 * Tool base management controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IToolBaseService
 */
@BaseController(path = ["/system/tool/base"], name = "工具基板管理", description = "工具基板管理相关接口")
class BaseController(
    private val toolBaseService: IToolBaseService
) {
    /**
     * Get tool base by ID
     *
     * @param id Tool base ID
     * @return Response object includes tool base information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see ToolBaseVo
     */
    @Operation(summary = "获取单个基板")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('system:tool:query:base')")
    fun getOne(@PathVariable id: Long): ResponseResult<ToolBaseVo> =
        ResponseResult.databaseSuccess(data = toolBaseService.getOne(id))

    /**
     * Get tool base list
     *
     * @return Response object includes tool base list
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see ToolBaseVo
     */
    @Operation(summary = "获取基板")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('system:tool:query:base', 'system:tool:add:template', 'system:tool:modify:template')")
    fun get(): ResponseResult<List<ToolBaseVo>> =
        ResponseResult.databaseSuccess(data = toolBaseService.get())

    /**
     * Add tool base
     *
     * @param toolBaseAddParam Add tool base parameters
     * @return Response object includes tool base information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBaseAddParam
     * @see ResponseResult
     * @see ToolBaseVo
     */
    @Trim
    @Operation(summary = "新增基板")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('system:tool:add:base')")
    fun add(@RequestBody @Valid toolBaseAddParam: ToolBaseAddParam): ResponseResult<ToolBaseVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_INSERT_SUCCESS,
            data = toolBaseService.add(toolBaseAddParam)
        )

    /**
     * Update tool base
     *
     * @param toolBaseUpdateParam Update tool base parameters
     * @return Response object includes tool base information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBaseUpdateParam
     * @see ResponseResult
     * @see ToolBaseVo
     */
    @Trim
    @Operation(summary = "更新基板")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('system:tool:modify:base')")
    fun update(@RequestBody @Valid toolBaseUpdateParam: ToolBaseUpdateParam): ResponseResult<ToolBaseVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_UPDATE_SUCCESS,
            data = toolBaseService.update(toolBaseUpdateParam)
        )

    /**
     * Delete tool base by ID
     *
     * @param id Tool base ID
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     */
    @Operation(summary = "删除基板")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('system:tool:delete:base')")
    fun delete(@PathVariable id: Long): ResponseResult<Nothing> =
        if (toolBaseService.delete(id)) ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
        else ResponseResult.databaseFail(ResponseCode.DATABASE_DELETE_FAILED)
}