package top.fatweb.api.util

import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSenderImpl
import top.fatweb.api.settings.MailSecurityType
import top.fatweb.api.settings.MailSettings
import top.fatweb.api.settings.SettingsOperator
import java.util.*

object MailUtil {
    private val mailSender: JavaMailSenderImpl = JavaMailSenderImpl()

    init {
        init()
    }

    fun init() {
        mailSender.defaultEncoding = Charsets.UTF_8.name()
        mailSender.protocol = "smtp"
        mailSender.host = SettingsOperator.getMailValue(MailSettings::host)
        SettingsOperator.getMailValue(MailSettings::port)?.let { mailSender.port = it }
        mailSender.username = SettingsOperator.getMailValue(MailSettings::username)
        mailSender.password = SettingsOperator.getMailValue(MailSettings::password)

        val properties = Properties()
        when (SettingsOperator.getMailValue(MailSettings::securityType)) {
            MailSecurityType.SSL_TLS -> properties.setProperty("mail.smtp.ssl.enable", "true")
            MailSecurityType.START_TLS -> properties.setProperty("mail.smtp.starttls.enable", "true")
            else -> {}
        }
        properties["mail.smtp.timeout"] = "10000"
        mailSender.javaMailProperties = properties
    }

    fun getSender(): MailSender = mailSender

    fun sendSimpleMail(subject: String, text: String, vararg to: String) {
        mailSender.send(SimpleMailMessage().apply {
            setSubject(subject)
            from = "${SettingsOperator.getMailValue(MailSettings::fromName)}<${SettingsOperator.getMailValue(MailSettings::from)}>"
            sentDate = Date()
            setTo(*to)
            setText(text)
        })
    }
}