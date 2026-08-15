package top.fatweb.oxygen.api.service.system.impl

import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.param.system.BaseSettingsParam
import top.fatweb.oxygen.api.param.system.MailSendParam
import top.fatweb.oxygen.api.param.system.MailSettingsParam
import top.fatweb.oxygen.api.param.system.TwoFactorSettingsParam
import top.fatweb.oxygen.api.properties.ServerProperties
import top.fatweb.oxygen.api.service.system.ISettingsService
import top.fatweb.oxygen.api.settings.*
import top.fatweb.oxygen.api.util.MailUtil
import top.fatweb.oxygen.api.util.md5
import top.fatweb.oxygen.api.vo.system.BaseSettingsVo
import top.fatweb.oxygen.api.vo.system.MailSettingsVo
import top.fatweb.oxygen.api.vo.system.TwoFactorSettingsVo

/**
 * Settings service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServerProperties
 * @see ISettingsService
 */
@Service
class SettingsServiceImpl : ISettingsService {
    override fun getBase() = BaseSettingsVo(
        systemName = SettingsOperator.getValue(BaseSettings::systemName, "Oxygen"),
        desktopProtocol = SettingsOperator.getValue(BaseSettings::desktopProtocol, "oxygen-desktop"),
        applicationProtocol = SettingsOperator.getValue(BaseSettings::applicationProtocol, "oxygen-app"),
        tokenExpiryBufferMs = SettingsOperator.getValue(BaseSettings::tokenExpiryBufferMs, 1800000),
        tokenExpiryCheckIntervalMs = SettingsOperator.getValue(BaseSettings::tokenExpiryCheckIntervalMs, 600000),
        turnstileSiteKey = SettingsOperator.getValue(BaseSettings::turnstileSiteKey),
        turnstileSecretKey = SettingsOperator.getValue(BaseSettings::turnstileSecretKey)?.takeIf { it.isNotEmpty() }
            ?.let(::md5),
        homeUrl = SettingsOperator.getValue(BaseSettings::homeUrl, "http://localhost"),
        getAndroidAppUrl = SettingsOperator.getValue(BaseSettings::getAndroidAppUrl),
    )

    override fun updateBase(baseSettingsParam: BaseSettingsParam) {
        baseSettingsParam.run {
            SettingsOperator.setValue(BaseSettings::systemName, systemName)
            SettingsOperator.setValue(BaseSettings::desktopProtocol, desktopProtocol)
            SettingsOperator.setValue(BaseSettings::applicationProtocol, applicationProtocol)
            SettingsOperator.setValue(BaseSettings::tokenExpiryBufferMs, tokenExpiryBufferMs)
            SettingsOperator.setValue(BaseSettings::tokenExpiryCheckIntervalMs, tokenExpiryCheckIntervalMs)
            SettingsOperator.setValue(BaseSettings::turnstileSiteKey, turnstileSiteKey)
            SettingsOperator.setValue(BaseSettings::turnstileSecretKey, turnstileSecretKey)
            SettingsOperator.setValue(BaseSettings::homeUrl, homeUrl)
            SettingsOperator.setValue(BaseSettings::getAndroidAppUrl, getAndroidAppUrl)
        }
    }

    override fun getMail() = MailSettingsVo(
        host = SettingsOperator.getValue(MailSettings::host, "smtp.example.com"),
        port = SettingsOperator.getValue(MailSettings::port, 25),
        securityType = SettingsOperator.getValue(MailSettings::securityType, MailSecurityType.NONE),
        username = SettingsOperator.getValue(MailSettings::username),
        password = SettingsOperator.getValue(MailSettings::password)?.takeIf { it.isNotEmpty() }?.let(::md5),
        from = SettingsOperator.getValue(MailSettings::from),
        fromName = SettingsOperator.getValue(MailSettings::fromName)
    )

    override fun updateMail(mailSettingsParam: MailSettingsParam) {
        mailSettingsParam.run {
            SettingsOperator.setValue(MailSettings::host, host)
            SettingsOperator.setValue(MailSettings::port, port)
            SettingsOperator.setValue(MailSettings::securityType, securityType)
            SettingsOperator.setValue(MailSettings::username, username)
            SettingsOperator.setValue(MailSettings::password, password)
            SettingsOperator.setValue(MailSettings::from, from)
            SettingsOperator.setValue(MailSettings::fromName, fromName)
        }

        MailUtil.init()
    }

    override fun sendMail(mailSendParam: MailSendParam) {
        mailSendParam.to?.let {
            MailUtil.sendSimpleMail(
                "${SettingsOperator.getValue(BaseSettings::systemName)} Test Message",
                "This is a test email sent when testing the system email sending service.",
                false,
                it
            )
        }
    }

    override fun getTwoFactor() = TwoFactorSettingsVo(
        issuer = SettingsOperator.getValue(TwoFactorSettings::issuer, "Oxygen"),
        secretKeyLength = SettingsOperator.getValue(TwoFactorSettings::secretKeyLength, 16)
    )

    override fun updateTwoFactor(twoFactorSettingsParam: TwoFactorSettingsParam) {
        twoFactorSettingsParam.run {
            SettingsOperator.setValue(TwoFactorSettings::issuer, issuer)
            SettingsOperator.setValue(TwoFactorSettings::secretKeyLength, secretKeyLength)
        }
    }
}
