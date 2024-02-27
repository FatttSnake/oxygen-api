package top.fatweb.oxygen.api.controller.tool

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.annotation.Trim
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.param.PageSortParam
import top.fatweb.oxygen.api.param.tool.ToolStoreGetParam
import top.fatweb.oxygen.api.service.tool.IStoreService
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolVo

/**
 * Tool store controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IStoreService
 */
@BaseController(path = ["/tool/store"], name = "工具商店", description = "工具商店相关接口")
class StoreController(
    private val storeService: IStoreService
) {
    /**
     * Get store tool paging information
     *
     * @param toolStoreGetParam Get tool parameters in tool store
     * @return Response object includes store tool paging information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolStoreGetParam
     * @see ResponseResult
     * @see PageVo
     * @see ToolVo
     */
    @Trim
    @Operation(description = "获取商店工具")
    @GetMapping
    fun get(@Valid toolStoreGetParam: ToolStoreGetParam): ResponseResult<PageVo<ToolVo>> =
        ResponseResult.databaseSuccess(data = storeService.getPage(toolStoreGetParam))

    /**
     * Get store tool paging information by username
     *
     * @param username Username
     * @param pageSortParam Page sort parameters
     * @return Response object includes store tool paging information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see PageSortParam
     * @see ResponseResult
     * @see PageVo
     * @see ToolVo
     */
    @Trim
    @Operation(description = "获取商店指定用户工具")
    @GetMapping("/{username}")
    fun get(@PathVariable username: String, @Valid pageSortParam: PageSortParam): ResponseResult<PageVo<ToolVo>> =
        ResponseResult.databaseSuccess(data = storeService.getPage(pageSortParam, username.trim()))
}