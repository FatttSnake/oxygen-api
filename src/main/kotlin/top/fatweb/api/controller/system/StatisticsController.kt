package top.fatweb.api.controller.system

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.service.system.IStatisticsService
import top.fatweb.api.vo.system.*

@Tag(name = "统计接口", description = "系统信息统计相关接口")
@RequestMapping("/system/statistics")
@RestController
class StatisticsController(
    private val statisticsService: IStatisticsService
) {
    @Operation(summary = "获取软件信息")
    @GetMapping("/software")
    fun software(): ResponseResult<SoftwareInfoVo> = ResponseResult.success(data = statisticsService.software())

    @Operation(summary = "获取硬件信息")
    @GetMapping("/hardware")
    fun hardware(): ResponseResult<HardwareInfoVo> = ResponseResult.success(data = statisticsService.hardware())

    @Operation(summary = "获取 CPU 信息")
    @GetMapping("/cpu")
    fun cpu(): ResponseResult<CpuInfoVo> = ResponseResult.success(data = statisticsService.cpu())

    @Operation(summary = "获取内存信息")
    @GetMapping("/memory")
    fun memory(): ResponseResult<MemoryInfoVo> = ResponseResult.success(data = statisticsService.memory())
}