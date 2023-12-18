package top.fatweb.api.controller.system

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import top.fatweb.api.annotation.BaseController
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.param.system.MailSendParam
import top.fatweb.api.param.system.MailSettingsParam
import top.fatweb.api.service.system.ISettingsService
import top.fatweb.api.vo.system.MailSettingsVo

/**
 * System settings controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ISettingsService
 */
@BaseController(path = ["/system/settings"], name = "系统设置", description = "系统设置相关接口")
class SettingsController(
    private val settingsService: ISettingsService
) {
    /**
     * Get mail settings
     *
     * @return Response object includes mail settings
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see MailSettingsVo
     */
    @Operation(summary = "获取邮件设置")
    @GetMapping("/mail")
    @PreAuthorize("hasAnyAuthority('system:settings:query:mail')")
    fun getMail(): ResponseResult<MailSettingsVo> = ResponseResult.success(data = settingsService.getMail())

    /**
     * Update mail settings
     *
     * @param mailSettingsParam Mail settings parameters
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see MailSettingsParam
     * @see ResponseResult
     */
    @Operation(summary = "更新邮件设置")
    @PutMapping("/mail")
    @PreAuthorize("hasAnyAuthority('system:settings:modify:mail')")
    fun updateMail(@RequestBody mailSettingsParam: MailSettingsParam): ResponseResult<Nothing> {
        settingsService.updateMail(mailSettingsParam)
        return ResponseResult.success()
    }

    /**
     * Send mail test
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Operation(summary = "邮件发送测试")
    @PostMapping("/mail")
    @PreAuthorize("hasAnyAuthority('system:settings:modify:mail')")
    fun sendMail(@RequestBody @Valid mailSendParam: MailSendParam): ResponseResult<Nothing> {
        settingsService.sendMail(mailSendParam)
        return ResponseResult.success()
    }
}