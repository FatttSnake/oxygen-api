package top.fatweb.api.service.system

import top.fatweb.api.param.system.MailSettingsParam
import top.fatweb.api.vo.system.SettingsVo

interface ISettingsService {
    fun get(): SettingsVo

    fun updateMail(mailSettingsParam: MailSettingsParam)
}