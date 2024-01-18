package top.fatweb.oxygen.api.controller.tool

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
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
 */
@BaseController(path = ["/system/tool/category"], name = "工具类别管理", description = "工具列别管理相关接口")
class CategoryController(
    private val toolCategoryService: IToolCategoryService
) {
    @Operation(summary = "获取单个类别")
    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): ResponseResult<ToolCategoryVo> =
        toolCategoryService.getOne(id)?.let { ResponseResult.databaseSuccess(data = it) }
            ?: let { ResponseResult.databaseFail(ResponseCode.DATABASE_NO_RECORD_FOUND) }

    @Operation(summary = "获取类别")
    @GetMapping
    fun get(): ResponseResult<List<ToolCategoryVo>> =
        ResponseResult.databaseSuccess(data = toolCategoryService.get())

    @Operation(summary = "新增类别")
    @PostMapping
    fun add(@RequestBody @Valid toolCategoryAddParam: ToolCategoryAddParam): ResponseResult<ToolCategoryVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_INSERT_SUCCESS,
            data = toolCategoryService.add(toolCategoryAddParam)
        )

    @Operation(summary = "更新类别")
    @PutMapping
    fun update(@RequestBody @Valid toolCategoryUpdateParam: ToolCategoryUpdateParam): ResponseResult<ToolCategoryVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_UPDATE_SUCCESS,
            data = toolCategoryService.update(toolCategoryUpdateParam)
        )

    @Operation(summary = "删除类别")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseResult<Nothing> =
        if (toolCategoryService.delete(id)) ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
        else ResponseResult.databaseFail(ResponseCode.DATABASE_DELETE_FILED)
}