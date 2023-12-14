package top.fatweb.api.controller.system

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.service.system.IStatisticService
import top.fatweb.api.vo.system.*

/**
 * Statistic controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IStatisticService
 */
@Tag(name = "统计接口", description = "系统信息统计相关接口")
@RequestMapping("/system/statistic")
@RestController
class StatisticController(
    private val statisticService: IStatisticService
) {
    /**
     * Get software information
     *
     * @return Response object includes software information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see SoftwareInfoVo
     */
    @Operation(summary = "获取软件信息")
    @GetMapping("/software")
    fun software(): ResponseResult<SoftwareInfoVo> = ResponseResult.success(data = statisticService.software())

    /**
     * Get hardware information
     *
     * @return Response object includes hardware information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see HardwareInfoVo
     */
    @Operation(summary = "获取硬件信息")
    @GetMapping("/hardware")
    fun hardware(): ResponseResult<HardwareInfoVo> = ResponseResult.success(data = statisticService.hardware())

    /**
     * Get CPU information
     *
     * @return Response object includes CPU information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see CpuInfoVo
     */
    @Operation(summary = "获取 CPU 信息")
    @GetMapping("/cpu")
    fun cpu(): ResponseResult<CpuInfoVo> = ResponseResult.success(data = statisticService.cpu())

    /**
     * Get storage information
     *
     * @return Response object includes storage information
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ResponseResult
     * @see StorageInfoVo
     */
    @Operation(summary = "获取存储信息")
    @GetMapping("/storage")
    fun storage(): ResponseResult<StorageInfoVo> = ResponseResult.success(data = statisticService.storage())
}