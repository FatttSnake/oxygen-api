package top.fatweb.oxygen.api.controller.tool

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.entity.common.ResponseCode
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.param.tool.ToolBaseAddParam
import top.fatweb.oxygen.api.param.tool.ToolBaseUpdateParam
import top.fatweb.oxygen.api.service.tool.IToolBaseService
import top.fatweb.oxygen.api.vo.tool.ToolBaseVo

@BaseController(path = ["/system/tool/base"], name = "工具基板管理", description = "工具基板管理相关接口")
class BaseController(
    private val toolBaseService: IToolBaseService
) {
    @Operation(summary = "获取单个基板")
    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): ResponseResult<ToolBaseVo> =
        ResponseResult.databaseSuccess(data = toolBaseService.getOne(id))

    @Operation(summary = "获取基板")
    @GetMapping
    fun get(): ResponseResult<List<ToolBaseVo>> =
        ResponseResult.databaseSuccess(data = toolBaseService.get())

    @Operation(summary = "新增基板")
    @PostMapping
    fun add(@RequestBody @Valid toolBaseAddParam: ToolBaseAddParam): ResponseResult<ToolBaseVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_INSERT_SUCCESS,
            data = toolBaseService.add(toolBaseAddParam)
        )

    @Operation(summary = "更新基板")
    @PutMapping
    fun update(@RequestBody @Valid toolBaseUpdateParam: ToolBaseUpdateParam): ResponseResult<ToolBaseVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_UPDATE_SUCCESS,
            data = toolBaseService.update(toolBaseUpdateParam)
        )

    @Operation(summary = "删除基板")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseResult<Nothing> =
        if (toolBaseService.delete(id)) ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
        else ResponseResult.databaseFail(ResponseCode.DATABASE_DELETE_FAILED)
}