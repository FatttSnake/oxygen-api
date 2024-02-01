package top.fatweb.oxygen.api.controller.tool

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.entity.common.ResponseCode
import top.fatweb.oxygen.api.entity.common.ResponseResult
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
    fun getTemplate(): ResponseResult<List<ToolTemplateVo>> =
        ResponseResult.databaseSuccess(data = editService.getTemplate())

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
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Operation(summary = "创建工具")
    @PostMapping
    fun create(@RequestBody @Valid toolCreateParam: ToolCreateParam): ResponseResult<ToolVo> =
        ResponseResult.databaseSuccess(ResponseCode.DATABASE_INSERT_SUCCESS, data = editService.create(toolCreateParam))

    /**
     * Upgrade tool
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
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
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Operation(summary = "获取个人工具")
    @GetMapping
    fun get(): ResponseResult<List<ToolVo>> =
        ResponseResult.databaseSuccess(ResponseCode.DATABASE_SELECT_SUCCESS, data = editService.get())

    /**
     * Get tool detail
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Operation(summary = "获取工具内容")
    @GetMapping("/detail/{username}/{toolId}/{ver}")
    fun detail(@PathVariable username: String, @PathVariable toolId: String, @PathVariable ver: String) =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_SELECT_SUCCESS,
            data = editService.detail(username, toolId, ver)
        )

    /**
     * Update tool
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Operation(summary = "更新工具")
    @PutMapping
    fun update(@RequestBody @Valid toolUpdateParam: ToolUpdateParam) =
        ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS, data = editService.update(toolUpdateParam))

    /**
     * Delete tool
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Operation(summary = "删除工具")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseResult<Nothing> =
        if (editService.delete(id)) ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
        else ResponseResult.databaseFail(ResponseCode.DATABASE_DELETE_FAILED)
}