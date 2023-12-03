package top.fatweb.api.service.system.impl

import org.springframework.stereotype.Service
import top.fatweb.api.param.system.SettingParam
import top.fatweb.api.service.system.ISysSettingService
import top.fatweb.api.vo.system.SettingVo

@Service
class SysSettingServiceImpl : ISysSettingService {
    override fun get(): SettingVo {
        TODO("Not yet implemented")
    }

    override fun update(settingParam: SettingParam) {
        TODO("Not yet implemented")
    }
}