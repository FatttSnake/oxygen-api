package top.fatweb.oxygen.api.controller.tool

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.entity.common.ResponseCode
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.param.tool.ToolCreateParam
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
}