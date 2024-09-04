package top.fatweb.oxygen.api.controller.permission

import io.swagger.v3.oas.annotations.Operation
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.service.permission.IPowerService
import top.fatweb.oxygen.api.vo.permission.PowerSetVo

/**
 * Power management controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IPowerService
 */
@BaseController(path = ["/system/power"], name = "权限管理", description = "权限管理相关接口")
class PowerController(
    private val powerService: IPowerService
) {
    /**
     * Get power list
     *
     * @return Response object includes power list
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see PowerSetVo
     */
    @Operation(summary = "获取权限列表")
    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('system:power:query:list', 'system:role:add:one', 'system:role:modify:one')")
    fun getList(): ResponseResult<PowerSetVo> = ResponseResult.databaseSuccess(data = powerService.getList())
}