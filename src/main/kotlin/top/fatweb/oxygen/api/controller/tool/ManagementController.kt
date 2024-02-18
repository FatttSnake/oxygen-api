package top.fatweb.oxygen.api.controller.tool

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.entity.common.ResponseCode
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.param.tool.ToolManagementGetParam
import top.fatweb.oxygen.api.param.tool.ToolManagementPassParam
import top.fatweb.oxygen.api.service.tool.IManagementService
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolVo

/**
 * Tool management controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
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
    @PreAuthorize("hasAnyAuthority('system:tool:query:tool')")
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
    @PreAuthorize("hasAnyAuthority('system:tool:query:tool')")
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
    @PreAuthorize("hasAnyAuthority('system:tool:modify:tool')")
    fun pass(
        @PathVariable id: Long,
        @RequestBody @Valid toolManagementPassParam: ToolManagementPassParam
    ): ResponseResult<ToolVo> =
        ResponseResult.databaseSuccess(
            ResponseCode.DATABASE_UPDATE_SUCCESS,
            data = managementService.pass(id, toolManagementPassParam)
        )

    /**
     * Reject tool review
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Operation(summary = "驳回审核")
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('system:tool:modify:tool')")
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
    @PreAuthorize("hasAnyAuthority('system:tool:modify:tool')")
    fun offShelve(@PathVariable id: Long): ResponseResult<ToolVo> =
        ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS, data = managementService.offShelve(id))

    /**
     * Delete tool
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Operation(summary = "删除工具")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('system:tool:delete:tool')")
    fun delete(@PathVariable id: Long): ResponseResult<Nothing> =
        if (managementService.delete(id)) ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
        else ResponseResult.databaseFail(ResponseCode.DATABASE_DELETE_FAILED)
}