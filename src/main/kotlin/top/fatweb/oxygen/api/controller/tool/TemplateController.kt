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
import top.fatweb.oxygen.api.param.tool.*
import top.fatweb.oxygen.api.service.tool.IToolTemplateService
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolTemplateVo
import top.fatweb.oxygen.api.vo.tool.ToolTemplateWithSourceVo

/**
 * Tool template management controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IToolTemplateService
 */
@BaseController(path = ["/system/tool/template"], name = "工具模板管理", description = "工具模板管理相关接口")
class TemplateController(
    private val toolTemplateService: IToolTemplateService
) {
    /**
     * Get tool template by ID
     *
     * @param id Tool template ID
     * @return Response object includes tool template information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see ToolTemplateWithSourceVo
     */
    @Operation(summary = "获取单个模板")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('system:tool:query:template')")
    fun getOne(@PathVariable id: Long): ResponseResult<ToolTemplateWithSourceVo> =
        ResponseResult.databaseSuccess(data = toolTemplateService.getOne(id))

    /**
     * Get tool template paging information
     *
     * @param toolTemplateGetParam Get tool template parameters
     * @return Response object includes tool template paging information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolTemplateGetParam
     * @see ResponseResult
     * @see PageVo
     * @see ToolTemplateVo
     */
    @Operation(summary = "获取模板")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('system:tool:query:template')")
    fun get(toolTemplateGetParam: ToolTemplateGetParam?): ResponseResult<PageVo<ToolTemplateVo>> =
        ResponseResult.databaseSuccess(data = toolTemplateService.get(toolTemplateGetParam))

    /**
     * Add tool template
     *
     * @param toolTemplateAddParam Add tool template parameters
     * @return Response object includes tool template information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolTemplateAddParam
     * @see ResponseResult
     * @see ToolTemplateWithSourceVo
     */
    @Operation(summary = "添加模板")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('system:tool:add:template')")
    fun add(@ProcessParam @RequestBody @Valid toolTemplateAddParam: ToolTemplateAddParam): ResponseResult<ToolTemplateWithSourceVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_INSERT_SUCCESS,
            data = toolTemplateService.add(toolTemplateAddParam)
        )

    /**
     * Update tool template
     *
     * @param toolTemplateUpdateParam Update tool template parameters
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolTemplateUpdateParam
     * @see ResponseResult
     */
    @Operation(summary = "更新模板")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('system:tool:modify:template')")
    fun update(@ProcessParam @RequestBody @Valid toolTemplateUpdateParam: ToolTemplateUpdateParam): ResponseResult<Unit> {
        toolTemplateService.update(toolTemplateUpdateParam)

        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

    /**
     * Update tool template source - add file/directory
     *
     * @param id Tool template ID
     * @param toolCommonUpdateSourceAddParam Update source - add file/directory parameters
     * @return Response object includes new node ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     * @see ToolCommonUpdateSourceAddParam
     * @see ResponseResult
     */
    @Operation(summary = "更新模板源码-新增文件(目录)")
    @PatchMapping("/source/{id}/add")
    @PreAuthorize("hasAnyAuthority('system:tool:modify:template')")
    fun updateSourceAdd(
        @PathVariable id: Long,
        @ProcessParam @RequestBody @Valid toolCommonUpdateSourceAddParam: ToolCommonUpdateSourceAddParam
    ): ResponseResult<String> =
        ResponseResult.databaseSuccess(
            code = ResponseCode.DATABASE_UPDATE_SUCCESS,
            data = toolTemplateService.updateSourceAdd(
                id = id,
                toolCommonUpdateSourceAddParam = toolCommonUpdateSourceAddParam
            )
        )

    /**
     * Update tool template source - rename file/directory
     *
     * @param id Tool template ID
     * @param nodeId Source node ID
     * @param fileName New file name
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     * @see ResponseResult
     */
    @Operation(summary = "更新模板源码-重命名文件(目录)")
    @PatchMapping("/source/{id}/rename/{nodeId}")
    @PreAuthorize("hasAnyAuthority('system:tool:modify:template')")
    fun updateSourceRename(
        @PathVariable id: Long,
        @PathVariable nodeId: Long,
        @ProcessParam @ParamProcessor @RequestBody fileName: String
    ): ResponseResult<Unit> {
        toolTemplateService.updateSourceRename(id = id, nodeId = nodeId, fileName = fileName)

        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

    /**
     * Update tool template source - move file/directory
     *
     * @param id Tool template ID
     * @param nodeId Source node ID
     * @param newParentId New parent node ID
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     * @see ResponseResult
     */
    @Operation(summary = "更新模板源码-移动文件(目录)")
    @PatchMapping("/source/{id}/move/{nodeId}")
    @PreAuthorize("hasAnyAuthority('system:tool:modify:template')")
    fun updateSourceMove(
        @PathVariable id: Long,
        @PathVariable nodeId: Long,
        @RequestBody newParentId: Long
    ): ResponseResult<Unit> {
        toolTemplateService.updateSourceMove(id = id, nodeId = nodeId, newParentId = newParentId)

        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

    /**
     * Update tool template source - update content
     *
     * @param id Tool template ID
     * @param nodeId Source node ID
     * @param content New content
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     * @see ResponseResult
     */
    @Operation(summary = "更新模板源码-更新文件")
    @PatchMapping("/source/{id}/content/{nodeId}")
    @PreAuthorize("hasAnyAuthority('system:tool:modify:template')")
    fun updateSourceContent(
        @PathVariable id: Long,
        @PathVariable nodeId: Long,
        @RequestBody content: String = ""
    ): ResponseResult<Unit> {
        toolTemplateService.updateSourceContent(id = id, nodeId = nodeId, content = content)

        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

    /**
     * Update tool template source - remove file/directory
     *
     * @param id Tool template ID
     * @param nodeId Source node ID
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     * @see ResponseResult
     */
    @Operation(summary = "更新模板源码-删除文件(目录)")
    @PatchMapping("/source/{id}/remove/{nodeId}")
    @PreAuthorize("hasAnyAuthority('system:tool:modify:template')")
    fun updateSourceRemove(
        @PathVariable id: Long,
        @PathVariable nodeId: Long
    ): ResponseResult<Unit> {
        toolTemplateService.updateSourceRemove(id = id, nodeId = nodeId)

        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

    /**
     * Upgrade tool template base version
     *
     * @param toolOrTemplateUpgradeBaseParam Upgrade tool template base version parameters
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ToolOrTemplateUpgradeBaseParam
     * @see ResponseResult
     */
    @Operation(summary = "更新模板基板版本")
    @PatchMapping("/upgradeBase")
    @PreAuthorize("hasAnyAuthority('system:tool:modify:template')")
    fun upgradeBase(@RequestBody @Valid toolOrTemplateUpgradeBaseParam: ToolOrTemplateUpgradeBaseParam): ResponseResult<Unit> {
        toolTemplateService.upgradeBase(toolOrTemplateUpgradeBaseParam)

        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

    /**
     * Delete tool template
     *
     * @param id Tool template ID
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     */
    @Operation(summary = "删除模板")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('system:tool:delete:template')")
    fun delete(@PathVariable id: Long): ResponseResult<Unit> =
        if (toolTemplateService.delete(id)) ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
        else ResponseResult.databaseFail(ResponseCode.DATABASE_DELETE_FAILED)
}
