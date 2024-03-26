package top.fatweb.oxygen.api.controller.tool

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.annotation.Trim
import top.fatweb.oxygen.api.entity.common.ResponseCode
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.entity.tool.ToolBase
import top.fatweb.oxygen.api.param.tool.ToolCreateParam
import top.fatweb.oxygen.api.param.tool.ToolUpdateParam
import top.fatweb.oxygen.api.param.tool.ToolUpgradeParam
import top.fatweb.oxygen.api.service.tool.IEditService
import top.fatweb.oxygen.api.vo.tool.ToolCategoryVo
import top.fatweb.oxygen.api.vo.tool.ToolTemplateVo
import top.fatweb.oxygen.api.vo.tool.ToolVo

/**
 * Tool edit controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IEditService
 */
@BaseController(path = ["/tool"], name = "工具编辑", description = "工具编辑相关接口")
class EditController(
    private val editService: IEditService
) {
    /**
     * Get tool template list
     *
     * @return Response object includes tool template list
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see ToolTemplateVo
     */
    @Operation(summary = "获取模板")
    @GetMapping("/template")
    fun getTemplate(platform: ToolBase.Platform): ResponseResult<List<ToolTemplateVo>> =
        ResponseResult.databaseSuccess(data = editService.getTemplate(platform))

    /**
     * Get tool template by ID
     *
     * @param id ID
     * @return Response object includes tool template information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see ToolTemplateVo
     */
    @Operation(summary = "获取单个模板")
    @GetMapping("/template/{id}")
    fun getTemplate(@PathVariable id: Long): ResponseResult<ToolTemplateVo> =
        ResponseResult.databaseSuccess(data = editService.getTemplate(id))

    /**
     * Get tool category list
     *
     * @return Response object includes tool category list
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see ToolCategoryVo
     */
    @Operation(summary = "获取类别")
    @GetMapping("/category")
    fun getCategory(): ResponseResult<List<ToolCategoryVo>> =
        ResponseResult.databaseSuccess(data = editService.getCategory())

    /**
     * Create tool
     *
     * @param toolCreateParam Create tool parameters
     * @return Response object includes tool information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolCreateParam
     * @see ResponseResult
     * @see ToolVo
     */
    @Trim
    @Operation(summary = "创建工具")
    @PostMapping
    fun create(@RequestBody @Valid toolCreateParam: ToolCreateParam): ResponseResult<ToolVo> =
        ResponseResult.databaseSuccess(ResponseCode.DATABASE_INSERT_SUCCESS, data = editService.create(toolCreateParam))

    /**
     * Upgrade tool
     *
     * @param toolUpgradeParam Upgrade tool parameters
     * @return Response object includes tool information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolUpgradeParam
     * @see ResponseResult
     * @see ToolVo
     */
    @Trim
    @Operation(summary = "升级工具")
    @PatchMapping
    fun upgrade(@RequestBody @Valid toolUpgradeParam: ToolUpgradeParam): ResponseResult<ToolVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_UPDATE_SUCCESS,
            data = editService.upgrade(toolUpgradeParam)
        )

    /**
     * Get personal tool
     *
     * @return Response object includes tool list
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see ToolVo
     */
    @Operation(summary = "获取个人工具")
    @GetMapping
    fun get(): ResponseResult<List<ToolVo>> =
        ResponseResult.databaseSuccess(ResponseCode.DATABASE_SELECT_SUCCESS, data = editService.get())

    /**
     * Get tool detail
     *
     * @param username Username
     * @param toolId Tool ID
     * @param ver Version
     * @return Response object includes tool information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see ToolVo
     */
    @Operation(summary = "获取工具内容")
    @GetMapping("/detail/{username}/{toolId}/{ver}")
    fun detail(
        @PathVariable username: String,
        @PathVariable toolId: String,
        @PathVariable ver: String,
        platform: ToolBase.Platform
    ): ResponseResult<ToolVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_SELECT_SUCCESS,
            data = editService.detail(username.trim(), toolId.trim(), ver.trim(), platform)
        )

    /**
     * Update tool
     *
     * @param toolUpdateParam Update tool parameters
     * @return Response object includes tool information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolUpdateParam
     * @see ResponseResult
     * @see ToolVo
     */
    @Trim
    @Operation(summary = "更新工具")
    @PutMapping
    fun update(@RequestBody @Valid toolUpdateParam: ToolUpdateParam): ResponseResult<ToolVo> =
        ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS, data = editService.update(toolUpdateParam))

    /**
     * Submit tool review
     *
     * @param id Tool ID
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     */
    @Operation(summary = "提交工具审核")
    @PostMapping("/{id}")
    fun submit(@PathVariable id: Long): ResponseResult<Nothing> =
        if (editService.submit(id)) ResponseResult.success(ResponseCode.TOOL_SUBMIT_SUCCESS)
        else ResponseResult.fail(ResponseCode.TOOL_SUBMIT_ERROR)

    /**
     * Cancel tool review
     *
     * @param id Tool ID
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     */
    @Operation(summary = "取消工具审核")
    @PutMapping("/{id}")
    fun cancel(@PathVariable id: Long): ResponseResult<Nothing> =
        if (editService.cancel(id)) ResponseResult.success(ResponseCode.TOOL_CANCEL_SUCCESS)
        else ResponseResult.fail(ResponseCode.TOOL_CANCEL_ERROR)

    /**
     * Delete tool
     *
     * @param id Tool ID
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     */
    @Operation(summary = "删除工具")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseResult<Nothing> =
        if (editService.delete(id)) ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
        else ResponseResult.databaseFail(ResponseCode.DATABASE_DELETE_FAILED)
}