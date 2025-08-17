package top.fatweb.oxygen.api.controller.tool

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.annotation.ProcessParam
import top.fatweb.oxygen.api.entity.common.ResponseCode
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.param.tool.*
import top.fatweb.oxygen.api.service.tool.IToolBaseService
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseWithSourceVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseWithVersionsVo

/**
 * Tool base management controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IToolBaseService
 */
@BaseController(path = ["/system/tool/base"], name = "工具基板管理", description = "工具基板管理相关接口")
@RegisterReflectionForBinding(
    ToolBaseGetParam::class
)
class BaseController(
    private val toolBaseService: IToolBaseService
) {
    /**
     * Get tool base by ID and version
     *
     * @param id Tool base ID
     * @param version Tool base version
     * @return Response object includes tool base information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see ToolBaseWithSourceVo
     */
    @Operation(summary = "获取单个基板")
    @GetMapping("/{id}/{version}")
    @PreAuthorize("hasAnyAuthority('system:tool:query:base')")
    fun getOne(@PathVariable id: Long, @PathVariable version: Long): ResponseResult<ToolBaseWithSourceVo> =
        ResponseResult.databaseSuccess(data = toolBaseService.getOne(id = id, version = version))

    /**
     * Get tool base paging information
     *
     * @param toolBaseGetParam Get tool base parameters
     * @return Response object includes tool base paging information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBaseGetParam
     * @see ResponseResult
     * @see PageVo
     * @see ToolBaseWithVersionsVo
     */
    @Operation(summary = "获取基板")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('system:tool:query:base')")
    fun get(toolBaseGetParam: ToolBaseGetParam?): ResponseResult<PageVo<ToolBaseWithVersionsVo>> =
        ResponseResult.databaseSuccess(data = toolBaseService.get(toolBaseGetParam))

    /**
     * Get tool base list
     *
     * @return Response object includes tool base list
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see ToolBaseVo
     */
    @Operation(summary = "获取基板列表")
    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('system:tool:add:template', 'system:tool:modify:template')")
    fun list(): ResponseResult<List<ToolBaseVo>> =
        ResponseResult.databaseSuccess(data = toolBaseService.getList())


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
    @Operation(summary = "新增基板")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('system:tool:add:base')")
    fun add(@ProcessParam @RequestBody @Valid toolBaseAddParam: ToolBaseAddParam): ResponseResult<ToolBaseVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_INSERT_SUCCESS,
            data = toolBaseService.add(toolBaseAddParam)
        )

    /**
     * Update tool base
     *
     * @param toolBaseUpdateParam Update tool base parameters
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBaseUpdateParam
     * @see ResponseResult
     */
    @Operation(summary = "更新基板")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('system:tool:modify:base')")
    fun update(@ProcessParam @RequestBody @Valid toolBaseUpdateParam: ToolBaseUpdateParam): ResponseResult<Unit> {
        toolBaseService.update(toolBaseUpdateParam)

        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

    /**
     * Update tool base source
     *
     * @param toolBaseUpdateSourceParam Update tool base source parameters
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ToolBaseUpdateSourceParam
     * @see ResponseResult
     */
    @Operation(summary = "更新基板源码")
    @PatchMapping("/source")
    @PreAuthorize("hasAnyAuthority('system:tool:modify:base')")
    fun updateSource(@RequestBody @Valid toolBaseUpdateSourceParam: ToolBaseUpdateSourceParam): ResponseResult<Unit> {
        toolBaseService.updateSource(toolBaseUpdateSourceParam)

        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

    /**
     * Update tool base dist
     *
     * @param toolBaseUpdateDistParam Update tool base dist parameters
     * @return Response object include version
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ToolBaseUpdateDistParam
     * @see ResponseResult
     */
    @Operation(summary = "更新基板产物")
    @PatchMapping("/dist")
    @PreAuthorize("hasAnyAuthority('system:tool:modify:base')")
    fun updateDist(@RequestBody @Valid toolBaseUpdateDistParam: ToolBaseUpdateDistParam): ResponseResult<Long> =
        ResponseResult.databaseSuccess(
            code = ResponseCode.DATABASE_UPDATE_SUCCESS,
            data = toolBaseService.updateDist(toolBaseUpdateDistParam)
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
    fun delete(@PathVariable id: Long): ResponseResult<Unit> =
        if (toolBaseService.delete(id)) ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
        else ResponseResult.databaseFail(ResponseCode.DATABASE_DELETE_FAILED)
}
