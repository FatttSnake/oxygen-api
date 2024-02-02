package top.fatweb.oxygen.api.controller.tool

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.entity.common.ResponseCode
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.param.tool.ToolManagementGetParam
import top.fatweb.oxygen.api.service.tool.IManagementService
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolVo

@BaseController(path = ["/system/tool"], name = "工具管理", description = "工具管理相关接口")
class ManagementController(
    private val managementService: IManagementService
) {
    /**
     * Get tool by ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Operation(summary = "获取单个工具")
    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): ResponseResult<ToolVo> =
        ResponseResult.databaseSuccess(data = managementService.getOne(id))

    /**
     * Get tool paging information
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Operation(summary = "获取工具")
    @GetMapping
    fun get(toolManagementGetParam: ToolManagementGetParam): ResponseResult<PageVo<ToolVo>> =
        ResponseResult.databaseSuccess(data = managementService.getPage(toolManagementGetParam))
    
    /**
     * Pass tool review
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Operation(summary = "通过审核")
    @PostMapping("/{id}")
    fun pass(@PathVariable id: Long): ResponseResult<ToolVo> =
        ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS, data = managementService.pass(id))

    /**
     * Reject tool review
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Operation(summary = "驳回审核")
    @PutMapping("/{id}")
    fun reject(@PathVariable id: Long): ResponseResult<ToolVo> =
        ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS, data = managementService.reject(id))

    /**
     * Put off shelve
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Operation(summary = "下架")
    @PatchMapping("/{id}")
    fun offShelve(@PathVariable id: Long):ResponseResult<ToolVo> =
        ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS, data = managementService.offShelve(id))

    /**
     * Delete tool
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Operation(summary = "删除工具")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseResult<Nothing> =
        if (managementService.delete(id)) ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
        else ResponseResult.databaseFail(ResponseCode.DATABASE_DELETE_FAILED)
}