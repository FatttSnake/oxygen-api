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
@RegisterReflectionForBinding(
    ToolTemplateGetParam::class
)
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
     * Update tool template source
     *
     * @param toolTemplateUpdateSourceParam Update tool template source parameters
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ToolTemplateUpdateSourceParam
     * @see ResponseResult
     */
    @Operation(summary = "更新模板源码")
    @PatchMapping("/source")
    @PreAuthorize("hasAnyAuthority('system:tool:modify:template')")
    fun updateSource(@RequestBody @Valid toolTemplateUpdateSourceParam: ToolTemplateUpdateSourceParam): ResponseResult<Unit> {
        toolTemplateService.updateSource(toolTemplateUpdateSourceParam)

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
