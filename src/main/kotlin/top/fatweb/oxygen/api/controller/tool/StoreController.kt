package top.fatweb.oxygen.api.controller.tool

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.param.tool.ToolStoreGetParam
import top.fatweb.oxygen.api.service.tool.IStoreService
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolVo

/**
 * Tool store controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@BaseController(path = ["/tool/store"], name = "工具商店", description = "工具商店相关接口")
class StoreController(
    private val storeService: IStoreService
) {
    /**
     * Get store tool in page
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @GetMapping
    fun get(@Valid toolStoreGetParam: ToolStoreGetParam): ResponseResult<PageVo<ToolVo>> =
        ResponseResult.databaseSuccess(data = storeService.getPage(toolStoreGetParam))
}