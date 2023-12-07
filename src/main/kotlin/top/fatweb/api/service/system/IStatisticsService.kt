package top.fatweb.api.service.system

import top.fatweb.api.vo.system.*

interface IStatisticsService {
    fun software(): SoftwareInfoVo

    fun hardware(): HardwareInfoVo

    fun cpu(): CpuInfoVo

    fun memory(): MemoryInfoVo

    fun jvm(): JvmInfoVo
}