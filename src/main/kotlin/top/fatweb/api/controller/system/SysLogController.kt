package top.fatweb.api.controller.system

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.fatweb.api.converter.system.SysLogConverter
import top.fatweb.api.entity.common.ResponseCode
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.param.system.SysLogGetParam
import top.fatweb.api.service.system.ISysLogService
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.system.SysLogGetVo

/**
 * <p>
 * 系统日志表 前端控制器
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-18
 */
@RestController
@RequestMapping("/system/log")
@Tag(name = "系统日志", description = "系统日志相关接口")
class SysLogController(
    private val sysLogService: ISysLogService
) {
    @Operation(summary = "获取")
    @GetMapping
    fun get(@Valid sysLogGetParam: SysLogGetParam?): ResponseResult<PageVo<SysLogGetVo>> {
        return ResponseResult.success(
            ResponseCode.DATABASE_SELECT_SUCCESS, data = SysLogConverter.sysLogPageToSysLogPageVo(
                sysLogService.getPage(sysLogGetParam)
            )
        )
    }
}
