package top.fatweb.api.service.system.impl

import org.springframework.stereotype.Service
import top.fatweb.api.converter.system.SettingsConverter
import top.fatweb.api.param.system.MailSettingsParam
import top.fatweb.api.service.system.ISettingsService
import top.fatweb.api.settings.MailSettings
import top.fatweb.api.settings.SettingsOperator
import top.fatweb.api.vo.system.SettingsVo

/**
 * Settings service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ISettingsService
 */
@Service
class SettingsServiceImpl : ISettingsService {
    override fun get(): SettingsVo  = SettingsConverter.systemSettingsToSettingsVo(SettingsOperator.settings())

    override fun updateMail(mailSettingsParam: MailSettingsParam) {
        mailSettingsParam.apply {
            SettingsOperator.setMailValue(MailSettings::host, host)
            SettingsOperator.setMailValue(MailSettings::port, port)
            SettingsOperator.setMailValue(MailSettings::username, username)
            SettingsOperator.setMailValue(MailSettings::password, password)
            SettingsOperator.setMailValue(MailSettings::from, from)
        }
    }
}