package top.fatweb.api.controller.system

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.param.system.SettingParam
import top.fatweb.api.service.system.ISysSettingService
import top.fatweb.api.vo.system.SettingVo

/**
 * System setting controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Tag(name = "系统设置", description = "系统设置相关接口")
@RequestMapping("/system/setting")
@RestController
class SettingController(
    private val sysSettingService: ISysSettingService
) {
    @Operation(summary = "获取")
    fun get(): ResponseResult<SettingVo> = ResponseResult.success(data = sysSettingService.get())

    @Operation(summary = "更新")
    fun update(settingParam: SettingParam): ResponseResult<Nothing> {
        sysSettingService.update(settingParam)
        return ResponseResult.success()
    }
}