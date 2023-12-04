package top.fatweb.api.controller.system

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.param.system.MailSettingsParam
import top.fatweb.api.service.system.ISettingsService
import top.fatweb.api.vo.system.SettingsVo

/**
 * System settings controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Tag(name = "系统设置", description = "系统设置相关接口")
@RequestMapping("/system/settings")
@RestController
class SettingsController(
    private val settingsService: ISettingsService
) {
    @Operation(summary = "获取全部设置")
    @GetMapping
    fun get(): ResponseResult<SettingsVo> = ResponseResult.success(data = settingsService.get())

    @Operation(summary = "更新邮件设置")
    @PutMapping("/mail")
    fun updateMail(@RequestBody mailSettingsParam: MailSettingsParam): ResponseResult<Nothing> {
        settingsService.updateMail(mailSettingsParam)
        return ResponseResult.success()
    }
}