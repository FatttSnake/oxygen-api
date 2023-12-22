package top.fatweb.api.service.system.impl

import org.springframework.stereotype.Service
import top.fatweb.api.converter.system.SettingsConverter
import top.fatweb.api.param.system.MailSendParam
import top.fatweb.api.param.system.MailSettingsParam
import top.fatweb.api.properties.ServerProperties
import top.fatweb.api.service.system.ISettingsService
import top.fatweb.api.settings.MailSettings
import top.fatweb.api.settings.SettingsOperator
import top.fatweb.api.util.MailUtil
import top.fatweb.api.vo.system.MailSettingsVo

/**
 * Settings service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ISettingsService
 */
@Service
class SettingsServiceImpl : ISettingsService {
    override fun getMail(): MailSettingsVo? = SettingsOperator.settings().mail?.let {
        SettingsConverter.mailSettingsToMailSettingsVo(
            it
        )
    }

    override fun updateMail(mailSettingsParam: MailSettingsParam) {
        mailSettingsParam.apply {
            SettingsOperator.setMailValue(MailSettings::host, host)
            SettingsOperator.setMailValue(MailSettings::port, port)
            SettingsOperator.setMailValue(MailSettings::securityType, securityType)
            SettingsOperator.setMailValue(MailSettings::username, username)
            SettingsOperator.setMailValue(MailSettings::password, password)
            SettingsOperator.setMailValue(MailSettings::from, from)
            SettingsOperator.setMailValue(MailSettings::fromName, fromName)
        }

        MailUtil.init()
    }

    override fun sendMail(mailSendParam: MailSendParam) {
        mailSendParam.to?.let {
            MailUtil.sendSimpleMail(
                "${ServerProperties.appName} Test Message",
                "This is a test email sent when testing the system email sending service.",
                false,
                it
            )
        }
    }
}