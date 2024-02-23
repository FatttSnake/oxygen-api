package top.fatweb.oxygen.api.controller.tool

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.entity.common.ResponseCode
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.param.tool.ToolCategoryAddParam
import top.fatweb.oxygen.api.param.tool.ToolCategoryUpdateParam
import top.fatweb.oxygen.api.service.tool.IToolCategoryService
import top.fatweb.oxygen.api.vo.tool.ToolCategoryVo

/**
 * Tool category management controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IToolCategoryService
 */
@BaseController(path = ["/system/tool/category"], name = "工具类别管理", description = "工具列别管理相关接口")
class CategoryController(
    private val toolCategoryService: IToolCategoryService
) {
    /**
     * Get tool category by ID
     *
     * @param id Tool category ID
     * @return Response object includes tool template information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see ToolCategoryVo
     */
    @Operation(summary = "获取单个类别")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('system:tool:query:category')")
    fun getOne(@PathVariable id: Long): ResponseResult<ToolCategoryVo> =
        ResponseResult.databaseSuccess(data = toolCategoryService.getOne(id))

    /**
     * Get tool category list
     *
     * @return Response object includes tool template list
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see ToolCategoryVo
     */
    @Operation(summary = "获取类别")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('system:tool:query:category')")
    fun get(): ResponseResult<List<ToolCategoryVo>> =
        ResponseResult.databaseSuccess(data = toolCategoryService.get())

    /**
     * Add tool category
     *
     * @param toolCategoryAddParam Add tool category parameters
     * @return Response object includes tool category information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolCategoryAddParam
     * @see ResponseResult
     * @see ToolCategoryVo
     */
    @Operation(summary = "新增类别")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('system:tool:add:category')")
    fun add(@RequestBody @Valid toolCategoryAddParam: ToolCategoryAddParam): ResponseResult<ToolCategoryVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_INSERT_SUCCESS,
            data = toolCategoryService.add(toolCategoryAddParam)
        )

    /**
     * Update tool category
     *
     * @param toolCategoryUpdateParam Update tool category parameters
     * @return Response object includes tool category information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolCategoryUpdateParam
     * @see ResponseResult
     * @see ToolCategoryVo
     */
    @Operation(summary = "更新类别")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('system:tool:modify:category')")
    fun update(@RequestBody @Valid toolCategoryUpdateParam: ToolCategoryUpdateParam): ResponseResult<ToolCategoryVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_UPDATE_SUCCESS,
            data = toolCategoryService.update(toolCategoryUpdateParam)
        )

    /**
     * Delete tool category
     *
     * @param id Tool category ID
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     */
    @Operation(summary = "删除类别")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('system:tool:delete:category')")
    fun delete(@PathVariable id: Long): ResponseResult<Nothing> =
        if (toolCategoryService.delete(id)) ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
        else ResponseResult.databaseFail(ResponseCode.DATABASE_DELETE_FAILED)
}