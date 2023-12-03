package top.fatweb.api.converter.system

import top.fatweb.api.param.system.SettingParam
import top.fatweb.api.setting.MailSetting
import top.fatweb.api.setting.SystemSetting
import top.fatweb.api.vo.system.SettingVo

object SettingConverter {
    fun mailSettingToMailSettingVo(mailSetting: MailSetting) = SettingVo.MailSettingVo(
        host = mailSetting.host,
        port = mailSetting.port,
        username = mailSetting.username,
        password = mailSetting.password,
        from = mailSetting.from
    )

    fun systemSettingToSettingVo(sysSetting: SystemSetting) = SettingVo(
        mail = sysSetting.mail?.let { mailSettingToMailSettingVo(it) }
    )

    fun mailSettingParamToMailSetting(mailSettingParam: SettingParam.MailSettingParam) = MailSetting(
        host = mailSettingParam.host,
        port = mailSettingParam.port,
        username = mailSettingParam.username,
        password = mailSettingParam.password,
        from = mailSettingParam.from
    )

    fun settingParamToSystemSetting(settingParam: SettingParam) = SystemSetting(
        mail = settingParam.mail?.let { mailSettingParamToMailSetting(it) }
    )
}