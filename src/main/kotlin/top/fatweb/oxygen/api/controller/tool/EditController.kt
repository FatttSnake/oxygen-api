package top.fatweb.oxygen.api.controller.tool

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.entity.common.ResponseCode
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.param.tool.ToolAddParam
import top.fatweb.oxygen.api.param.tool.ToolUpdateParam
import top.fatweb.oxygen.api.service.tool.IToolService
import top.fatweb.oxygen.api.vo.tool.ToolVo

/**
 * Tool management controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@BaseController(path = ["/tool"], name = "工具管理", description = "工具管理相关接口")
class EditController(
    private val toolService: IToolService
) {
    @Operation(summary = "获取单个工具")
    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): ResponseResult<ToolVo> =
        ResponseResult.databaseSuccess(data = toolService.getOne(id))

    @Operation(summary = "获取工具")
    @GetMapping
    fun get(): ResponseResult<List<ToolVo>> =
        ResponseResult.databaseSuccess(data = toolService.get())

    @Operation(summary = "新增工具")
    @PostMapping
    fun add(@RequestBody @Valid toolAddParam: ToolAddParam): ResponseResult<ToolVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_INSERT_SUCCESS,
            data = toolService.add(toolAddParam)
        )

    @Operation(summary = "更新工具")
    @PutMapping
    fun update(@RequestBody @Valid toolUpdateParam: ToolUpdateParam): ResponseResult<ToolVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_UPDATE_SUCCESS,
            data = toolService.update(toolUpdateParam)
        )

    @Operation(summary = "删除工具")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseResult<Nothing> =
        if (toolService.delete(id)) ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
        else ResponseResult.databaseFail(ResponseCode.DATABASE_DELETE_FAILED)
}