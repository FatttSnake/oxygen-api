package top.fatweb.oxygen.api.controller.tool

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.annotation.ParamProcessor
import top.fatweb.oxygen.api.annotation.ProcessParam
import top.fatweb.oxygen.api.entity.common.ResponseCode
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.entity.tool.Platform
import top.fatweb.oxygen.api.param.PageSortParam
import top.fatweb.oxygen.api.param.tool.*
import top.fatweb.oxygen.api.service.tool.IEditService
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.*

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
    fun getTemplate(platform: Platform): ResponseResult<List<ToolTemplateVo>> =
        ResponseResult.databaseSuccess(data = editService.getTemplate(platform))

    /**
     * Get tool template by ID
     *
     * @param id ID
     * @return Response object includes tool template information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see ToolTemplateWithSourceVo
     */
    @Operation(summary = "获取单个模板")
    @GetMapping("/template/{id}")
    fun getTemplate(@PathVariable id: Long): ResponseResult<ToolTemplateWithSourceVo> =
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
     * Get tool base dist
     *
     * @param id Tool base ID
     * @param version Tool base version
     * @return Response object includes tool base with dist
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @Operation(summary = "获取基板产物")
    @GetMapping("/base/{id}/{version}")
    fun getBaseDist(@PathVariable id: Long, @PathVariable version: Long): ResponseResult<ToolBaseWithDistVo> =
        ResponseResult.databaseSuccess(data = editService.getBaseDist(id, version))

    /**
     * Get tool base latest version
     *
     * @param id Tool base ID
     * @return Version
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ResponseResult
     */
    @Operation(summary = "获取基板最新版本")
    @GetMapping("/base/{id}/version")
    fun getBaseLatestVersion(@PathVariable id: Long): ResponseResult<Long> =
        ResponseResult.databaseSuccess(data = editService.getBaseLatestVersion(id))

    /**
     * Get tool source
     *
     * @param username Username
     * @param toolId Tool ID
     * @param ver Version
     * @return Response object includes tool source
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see Platform
     * @see ResponseResult
     * @see ToolWithSourceVo
     */
    @Operation(summary = "获取工具源码")
    @GetMapping("/source/{username}/{toolId}/{ver}")
    fun source(
        @ProcessParam @ParamProcessor @PathVariable username: String,
        @ProcessParam @ParamProcessor @PathVariable toolId: String,
        @ProcessParam @ParamProcessor @PathVariable ver: String,
        platform: Platform
    ): ResponseResult<ToolWithSourceVo> =
        ResponseResult.databaseSuccess(
            code = ResponseCode.DATABASE_SELECT_SUCCESS,
            data = editService.source(username, toolId, ver, platform)
        )

    /**
     * Get tool dist
     *
     * @param username Username
     * @param toolId Tool ID
     * @param ver Version
     * @return Response object includes tool dist
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see Platform
     * @see ResponseResult
     * @see ToolWithDistVo
     */
    @Operation(summary = "获取工具产物")
    @GetMapping("/dist/{username}/{toolId}/{ver}")
    fun dist(
        @ProcessParam @ParamProcessor @PathVariable username: String,
        @ProcessParam @ParamProcessor @PathVariable toolId: String,
        @ProcessParam @ParamProcessor @PathVariable ver: String,
        platform: Platform
    ): ResponseResult<ToolWithDistVo> =
        ResponseResult.databaseSuccess(
            code = ResponseCode.DATABASE_SELECT_SUCCESS,
            data = editService.dist(username, toolId, ver, platform)
        )

    /**
     * Get personal tool
     *
     * @param pageSortParam Page sort parameters
     * @return Response object includes tool list
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see PageSortParam
     * @see ResponseResult
     * @see PageVo
     * @see ToolVo
     */
    @Operation(summary = "获取个人工具")
    @GetMapping
    fun get(@Valid pageSortParam: PageSortParam): ResponseResult<PageVo<ToolVo>> =
        ResponseResult.databaseSuccess(
            code = ResponseCode.DATABASE_SELECT_SUCCESS,
            data = editService.getPage(pageSortParam)
        )

    /**
     * Create tool
     *
     * @param toolCreateParam Create tool parameters
     * @return Response object includes tool information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolCreateParam
     * @see ResponseResult
     * @see ToolWithSourceVo
     */
    @Operation(summary = "创建工具")
    @PostMapping
    fun create(@ProcessParam @RequestBody @Valid toolCreateParam: ToolCreateParam): ResponseResult<ToolWithSourceVo> =
        ResponseResult.databaseSuccess(
            code = ResponseCode.DATABASE_INSERT_SUCCESS,
            data = editService.create(toolCreateParam)
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
     */
    @Operation(summary = "更新工具")
    @PutMapping
    fun update(@ProcessParam @RequestBody @Valid toolUpdateParam: ToolUpdateParam): ResponseResult<Unit> {
        editService.update(toolUpdateParam)

        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

    /**
     * Update tool source
     *
     * @param toolUpdateSourceParam Update tool source parameters
     * @return Response object includes tool information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ToolUpdateSourceParam
     * @see ResponseResult
     */
    @Operation(summary = "更新工具源码")
    @PatchMapping("/source")
    fun updateSource(@ProcessParam @RequestBody @Valid toolUpdateSourceParam: ToolUpdateSourceParam): ResponseResult<Unit> {
        editService.updateSource(toolUpdateSourceParam)

        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

    /**
     * Upgrade tool
     *
     * @param toolUpgradeParam Upgrade tool parameters
     * @return Response object includes tool information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolUpgradeParam
     * @see ResponseResult
     * @see ToolWithSourceVo
     */
    @Operation(summary = "升级工具")
    @PatchMapping("/upgrade")
    fun upgrade(@ProcessParam @RequestBody @Valid toolUpgradeParam: ToolUpgradeParam): ResponseResult<ToolWithSourceVo> =
        ResponseResult.databaseSuccess(
            code = ResponseCode.DATABASE_UPDATE_SUCCESS,
            data = editService.upgrade(toolUpgradeParam)
        )

    /**
     * Upgrade tool base version
     *
     * @param toolOrTemplateUpgradeBaseParam Upgrade tool base version
     * @return Response result
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ToolOrTemplateUpgradeBaseParam
     * @see ResponseResult
     */
    @Operation(summary = "更新工具基板版本")
    @PatchMapping("/upgradeBase")
    fun upgradeBase(@RequestBody @Valid toolOrTemplateUpgradeBaseParam: ToolOrTemplateUpgradeBaseParam): ResponseResult<Unit> {
        editService.upgradeBase(toolOrTemplateUpgradeBaseParam)

        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

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
    fun submit(@PathVariable id: Long): ResponseResult<Unit> =
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
    fun cancel(@PathVariable id: Long): ResponseResult<Unit> =
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
    fun delete(@PathVariable id: Long): ResponseResult<Unit> =
        if (editService.delete(id)) ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
        else ResponseResult.databaseFail(ResponseCode.DATABASE_DELETE_FAILED)
}
