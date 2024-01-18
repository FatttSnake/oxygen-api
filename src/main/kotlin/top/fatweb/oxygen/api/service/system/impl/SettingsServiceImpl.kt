package top.fatweb.oxygen.api.service.system.impl

import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.param.system.BaseSettingsParam
import top.fatweb.oxygen.api.param.system.MailSendParam
import top.fatweb.oxygen.api.param.system.MailSettingsParam
import top.fatweb.oxygen.api.properties.ServerProperties
import top.fatweb.oxygen.api.service.system.ISettingsService
import top.fatweb.oxygen.api.settings.BaseSettings
import top.fatweb.oxygen.api.settings.MailSettings
import top.fatweb.oxygen.api.settings.SettingsOperator
import top.fatweb.oxygen.api.util.MailUtil
import top.fatweb.oxygen.api.util.StrUtil
import top.fatweb.oxygen.api.vo.system.BaseSettingsVo
import top.fatweb.oxygen.api.vo.system.MailSettingsVo

/**
 * Settings service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ISettingsService
 */
@Service
class SettingsServiceImpl : ISettingsService {
    override fun getBase() = BaseSettingsVo(
        appName = SettingsOperator.getAppValue(BaseSettings::appName, "氧工具"),
        appUrl = SettingsOperator.getAppValue(BaseSettings::appUrl, "http://localhost"),
        verifyUrl = SettingsOperator.getAppValue(
            BaseSettings::verifyUrl,
            "http://localhost/verify?code=\${verifyCode}"
        ),
        retrieveUrl = SettingsOperator.getAppValue(
            BaseSettings::retrieveUrl,
            "http://localhost/retrieve?code=\${retrieveCode}"
        )
    )

    override fun updateBase(baseSettingsParam: BaseSettingsParam) {
        baseSettingsParam.run {
            SettingsOperator.setAppValue(BaseSettings::appName, appName)
            SettingsOperator.setAppValue(BaseSettings::appUrl, appUrl)
            SettingsOperator.setAppValue(BaseSettings::verifyUrl, verifyUrl)
            SettingsOperator.setAppValue(BaseSettings::retrieveUrl, retrieveUrl)
        }
    }

    override fun getMail() = MailSettingsVo(
        host = SettingsOperator.getMailValue(MailSettings::host),
        port = SettingsOperator.getMailValue(MailSettings::port),
        securityType = SettingsOperator.getMailValue(MailSettings::securityType),
        username = SettingsOperator.getMailValue(MailSettings::username),
        password = SettingsOperator.getMailValue(MailSettings::password)?.let(StrUtil::md5),
        from = SettingsOperator.getMailValue(MailSettings::from),
        fromName = SettingsOperator.getMailValue(MailSettings::fromName)
    )

    override fun updateMail(mailSettingsParam: MailSettingsParam) {
        mailSettingsParam.run {
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