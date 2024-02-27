package top.fatweb.oxygen.api.controller.system

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import top.fatweb.oxygen.api.annotation.BaseController
import top.fatweb.oxygen.api.annotation.Trim
import top.fatweb.oxygen.api.entity.common.ResponseCode
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.param.system.SysLogGetParam
import top.fatweb.oxygen.api.service.system.ISysLogService
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.system.SysLogVo

/**
 * System log viewer controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ISysLogService
 */
@BaseController(path = ["/system/log"], name = "系统日志", description = "系统日志相关接口")
class SysLogController(
    private val sysLogService: ISysLogService
) {
    /**
     * Get system log in page
     *
     * @param sysLogGetParam Get system log parameters
     * @return Response object includes system log in page
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see SysLogGetParam
     * @see ResponseResult
     * @see SysLogVo
     */
    @Trim
    @Operation(summary = "获取")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('system:log:query:all')")
    fun get(@Valid sysLogGetParam: SysLogGetParam?): ResponseResult<PageVo<SysLogVo>> {
        return ResponseResult.success(
            ResponseCode.DATABASE_SELECT_SUCCESS, data = sysLogService.getPage(sysLogGetParam)
        )
    }
}
