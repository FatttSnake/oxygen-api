package top.fatweb.api.service.system

import top.fatweb.api.param.system.SettingParam
import top.fatweb.api.vo.system.SettingVo

interface ISysSettingService {
    fun get(): SettingVo

    fun update(settingParam: SettingParam)
}