package top.fatweb.api.controller.permission

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.service.permission.IPowerService
import top.fatweb.api.vo.permission.PowerSetVo

/**
 * Power controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IPowerService
 */
@Tag(name = "权限管理", description = "权限管理相关接口")
@RequestMapping("/system/power")
@RestController
class PowerController(
    private val powerService: IPowerService
) {
    /**
     * Get power list
     *
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