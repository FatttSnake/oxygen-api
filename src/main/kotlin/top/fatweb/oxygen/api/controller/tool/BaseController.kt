package top.fatweb.oxygen.api.controller.tool

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.annotation.ParamProcessor
import top.fatweb.oxygen.api.annotation.ProcessParam
import top.fatweb.oxygen.api.entity.common.ResponseCode
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.param.tool.ToolBaseAddParam
import top.fatweb.oxygen.api.param.tool.ToolBaseGetParam
import top.fatweb.oxygen.api.param.tool.ToolBaseUpdateParam
import top.fatweb.oxygen.api.param.tool.ToolCommonUpdateSourceAddParam
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
     * Update tool base source - add file/directory
     *
     * @param id Tool base ID
     * @param toolCommonUpdateSourceAddParam Update source - add file/directory parameters
     * @return Response object includes new node ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     * @see ToolCommonUpdateSourceAddParam
     * @see ResponseResult
     */
    @Operation(summary = "更新基板源码-新增文件(目录)")
    @PatchMapping("/source/{id}/add")
    @PreAuthorize("hasAnyAuthority('system:tool:modify:base')")
    fun updateSourceAdd(
        @PathVariable id: Long,
        @ProcessParam @RequestBody @Valid toolCommonUpdateSourceAddParam: ToolCommonUpdateSourceAddParam
    ): ResponseResult<String> =
        ResponseResult.databaseSuccess(
            code = ResponseCode.DATABASE_UPDATE_SUCCESS,
            data = toolBaseService.updateSourceAdd(
                id = id,
                toolCommonUpdateSourceAddParam = toolCommonUpdateSourceAddParam
            )
        )

    /**
     * Update tool base source - rename file/directory
     *
     * @param id Tool base ID
     * @param nodeId Source node ID
     * @param fileName New file name
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     * @see ResponseResult
     */
    @Operation(summary = "更新基板源码-重命名文件(目录)")
    @PatchMapping("/source/{id}/rename/{nodeId}")
    @PreAuthorize("hasAnyAuthority('system:tool:modify:base')")
    fun updateSourceRename(
        @PathVariable id: Long,
        @PathVariable nodeId: Long,
        @ProcessParam @ParamProcessor @RequestBody fileName: String
    ): ResponseResult<Unit> {
        toolBaseService.updateSourceRename(id = id, nodeId = nodeId, fileName = fileName)

        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

    /**
     * Update tool base source - move file/directory
     *
     * @param id Tool base ID
     * @param nodeId Source node ID
     * @param newParentId New parent node ID
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     * @see ResponseResult
     */
    @Operation(summary = "更新基板源码-移动文件(目录)")
    @PatchMapping("/source/{id}/move/{nodeId}")
    @PreAuthorize("hasAnyAuthority('system:tool:modify:base')")
    fun updateSourceMove(
        @PathVariable id: Long,
        @PathVariable nodeId: Long,
        @RequestBody newParentId: Long
    ): ResponseResult<Unit> {
        toolBaseService.updateSourceMove(id = id, nodeId = nodeId, newParentId = newParentId)

        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

    /**
     * Update tool base source - update content
     *
     * @param id Tool base ID
     * @param nodeId Source node ID
     * @param content New content
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     * @see ResponseResult
     */
    @Operation(summary = "更新基板源码-更新文件")
    @PatchMapping("/source/{id}/content/{nodeId}")
    @PreAuthorize("hasAnyAuthority('system:tool:modify:base')")
    fun updateSourceContent(
        @PathVariable id: Long,
        @PathVariable nodeId: Long,
        @RequestBody content: String = ""
    ): ResponseResult<Unit> {
        toolBaseService.updateSourceContent(id = id, nodeId = nodeId, content = content)

        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

    /**
     * Update tool base source - remove file/directory
     *
     * @param id Tool base ID
     * @param nodeId Source node ID
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     * @see ResponseResult
     */
    @Operation(summary = "更新基板源码-删除文件(目录)")
    @PatchMapping("/source/{id}/remove/{nodeId}")
    @PreAuthorize("hasAnyAuthority('system:tool:modify:base')")
    fun updateSourceRemove(
        @PathVariable id: Long,
        @PathVariable nodeId: Long
    ): ResponseResult<Unit> {
        toolBaseService.updateSourceRemove(id = id, nodeId = nodeId)

        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

    /**
     * Update tool base dist
     *
     * @param id Tool base ID
     * @param dist Dist
     * @return Response object include version
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ResponseResult
     */
    @Operation(summary = "更新基板产物")
    @PatchMapping("/dist/{id}")
    @PreAuthorize("hasAnyAuthority('system:tool:modify:base')")
    fun updateDist(@PathVariable id: Long, @RequestBody dist: String): ResponseResult<Long> =
        ResponseResult.databaseSuccess(
            code = ResponseCode.DATABASE_UPDATE_SUCCESS,
            data = toolBaseService.updateDist(id = id, dist = dist)
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
