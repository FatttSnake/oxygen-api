package top.fatweb.api.service.system

import top.fatweb.api.vo.system.HardwareInfoVo
import top.fatweb.api.vo.system.SoftwareInfoVo

interface IStatisticsService {
    fun software(): SoftwareInfoVo

    fun hardware(): HardwareInfoVo
}