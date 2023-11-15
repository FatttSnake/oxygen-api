package top.fatweb.api.controller.permission

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.service.permission.IPowerService

/**
 * 权限 前端控制器
 */
@Tag(name = "权限管理", description = "权限管理相关接口")
@RestController
@RequestMapping("/system/power")
class PowerController(
    private val powerService: IPowerService
) {
    @Operation(summary = "获取权限列表")
    @GetMapping("/list")
    fun getList() = ResponseResult.databaseSuccess(data = powerService.getList())
}