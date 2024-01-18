package top.fatweb.oxygen.api.controller.tool

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.entity.common.ResponseCode
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.param.tool.ToolTemplateAddParam
import top.fatweb.oxygen.api.param.tool.ToolTemplateUpdateParam
import top.fatweb.oxygen.api.service.tool.IToolTemplateService
import top.fatweb.oxygen.api.vo.tool.ToolTemplateVo

@BaseController(path = ["/system/tool/template"], name = "工具模板管理", description = "工具模板管理相关接口")
class TemplateController(
    private val toolTemplateService: IToolTemplateService
) {
    @Operation(summary = "获取单个模板")
    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): ResponseResult<ToolTemplateVo> =
        ResponseResult.databaseSuccess(data = toolTemplateService.getOne(id))

    @Operation(summary = "获取模板")
    @GetMapping
    fun get(): ResponseResult<List<ToolTemplateVo>> =
        ResponseResult.databaseSuccess(data = toolTemplateService.get())

    @Operation(summary = "添加模板")
    @PostMapping
    fun add(@RequestBody @Valid toolTemplateAddParam: ToolTemplateAddParam): ResponseResult<ToolTemplateVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_INSERT_SUCCESS,
            data = toolTemplateService.add(toolTemplateAddParam)
        )

    @Operation(summary = "更新模板")
    @PutMapping
    fun update(@RequestBody @Valid toolTemplateUpdateParam: ToolTemplateUpdateParam): ResponseResult<ToolTemplateVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_UPDATE_SUCCESS,
            data = toolTemplateService.update(toolTemplateUpdateParam)
        )

    @Operation(summary = "删除模板")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseResult<Nothing> =
        if (toolTemplateService.delete(id)) ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
        else ResponseResult.databaseFail(ResponseCode.DATABASE_DELETE_FAILED)
}