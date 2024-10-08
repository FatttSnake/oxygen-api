package top.fatweb.oxygen.api.controller.system

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.annotation.Trim
import top.fatweb.oxygen.api.entity.common.ResponseCode
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.param.system.*
import top.fatweb.oxygen.api.service.system.ISensitiveWordService
import top.fatweb.oxygen.api.service.system.ISettingsService
import top.fatweb.oxygen.api.vo.system.BaseSettingsVo
import top.fatweb.oxygen.api.vo.system.MailSettingsVo
import top.fatweb.oxygen.api.vo.system.SensitiveWordVo
import top.fatweb.oxygen.api.vo.system.TwoFactorSettingsVo

/**
 * System settings management controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ISettingsService
 * @see ISensitiveWordService
 */
@BaseController(path = ["/system/settings"], name = "系统设置", description = "系统设置相关接口")
class SettingsController(
    private val settingsService: ISettingsService,
    private val sensitiveWordService: ISensitiveWordService
) {
    /**
     * Get base settings
     *
     * @return Response object includes base settings information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see BaseSettingsVo
     */
    @Operation(summary = "获取基础设置")
    @GetMapping("/base")
    @PreAuthorize("hasAnyAuthority('system:settings:query:base')")
    fun getApp(): ResponseResult<BaseSettingsVo> =
        ResponseResult.success(data = settingsService.getBase())

    /**
     * Update base settings
     *
     * @param baseSettingsParam Base settings parameters
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see BaseSettingsParam
     * @see ResponseResult
     */
    @Trim
    @Operation(summary = "更新基础设置")
    @PutMapping("/base")
    @PreAuthorize("hasAnyAuthority('system:settings:modify:base')")
    fun updateApp(@RequestBody baseSettingsParam: BaseSettingsParam): ResponseResult<Nothing> {
        settingsService.updateBase(baseSettingsParam)
        return ResponseResult.success()
    }

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
    fun getMail(): ResponseResult<MailSettingsVo> =
        ResponseResult.success(data = settingsService.getMail())

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
    @Trim
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
     * @param mailSendParam Mail send parameters
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see MailSendParam
     * @see ResponseResult
     */
    @Trim
    @Operation(summary = "邮件发送测试")
    @PostMapping("/mail")
    @PreAuthorize("hasAnyAuthority('system:settings:modify:mail')")
    fun sendMail(@RequestBody @Valid mailSendParam: MailSendParam): ResponseResult<Nothing> {
        settingsService.sendMail(mailSendParam)
        return ResponseResult.success()
    }

    /**
     * Get sensitive word settings
     *
     * @return Response object includes sensitive word settings information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see SensitiveWordVo
     */
    @Operation(summary = "获取敏感词配置")
    @GetMapping("/sensitive")
    @PreAuthorize("hasAnyAuthority('system:settings:query:sensitive')")
    fun getSensitive(): ResponseResult<List<SensitiveWordVo>> =
        ResponseResult.databaseSuccess(ResponseCode.DATABASE_SELECT_SUCCESS, data = sensitiveWordService.get())

    /**
     * Add sensitive word
     *
     * @param sensitiveWordAddParam Add sensitive word settings parameters
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see SensitiveWordAddParam
     * @see ResponseResult
     */
    @Trim
    @Operation(summary = "添加敏感词")
    @PostMapping("/sensitive")
    @PreAuthorize("hasAnyAuthority('system:settings:modify:sensitive')")
    fun addSensitive(@RequestBody @Valid sensitiveWordAddParam: SensitiveWordAddParam): ResponseResult<Nothing> {
        sensitiveWordService.add(sensitiveWordAddParam)
        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_INSERT_SUCCESS)
    }

    /**
     * Update sensitive word
     *
     * @param sensitiveWordUpdateParam Update sensitive word settings parameters
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see SensitiveWordUpdateParam
     * @see ResponseResult
     */
    @Operation(summary = "修改敏感词")
    @PutMapping("/sensitive")
    @PreAuthorize("hasAnyAuthority('system:settings:modify:sensitive')")
    fun updateSensitive(@RequestBody sensitiveWordUpdateParam: SensitiveWordUpdateParam): ResponseResult<Nothing> {
        sensitiveWordService.update(sensitiveWordUpdateParam)
        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
    }

    /**
     * Delete sensitive word
     *
     * @see id Sensitive word ID
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     */
    @Operation(summary = "删除敏感词")
    @DeleteMapping("/sensitive/{id}")
    @PreAuthorize("hasAnyAuthority('system:settings:modify:sensitive')")
    fun deleteSensitive(@PathVariable id: Long): ResponseResult<Nothing> {
        sensitiveWordService.delete(id)
        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
    }

    /**
     * Get two-factor settings
     *
     * @return Response object includes two-factor settings information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see TwoFactorSettingsVo
     */
    @Operation(summary = "获取双因素设置")
    @GetMapping("/two-factor")
    @PreAuthorize("hasAnyAuthority('system:settings:query:two-factor')")
    fun getTwoFactor(): ResponseResult<TwoFactorSettingsVo> =
        ResponseResult.success(data = settingsService.getTwoFactor())

    /**
     * Update two-factor settings
     *
     * @param twoFactorSettingsParam Two-factor settings parameters
     * @return Response object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see TwoFactorSettingsParam
     * @see ResponseResult
     */
    @Trim
    @Operation(summary = "更新双因素设置")
    @PutMapping("/two-factor")
    @PreAuthorize("hasAnyAuthority('system:settings:modify:two-factor')")
    fun updateTwoFactor(@RequestBody twoFactorSettingsParam: TwoFactorSettingsParam): ResponseResult<Nothing> {
        settingsService.updateTwoFactor(twoFactorSettingsParam)
        return ResponseResult.success()
    }
}