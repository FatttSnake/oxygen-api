package top.fatweb.oxygen.api.util

import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import top.fatweb.oxygen.api.exception.NoEmailConfigException
import top.fatweb.oxygen.api.settings.MailSecurityType
import top.fatweb.oxygen.api.settings.MailSettings
import top.fatweb.oxygen.api.settings.SettingsOperator
import java.util.*

/**
 * Mail util
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
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

    fun sendSimpleMail(subject: String, text: String, html: Boolean = false, vararg to: String) {
        val fromName = SettingsOperator.getMailValue(MailSettings::fromName) ?: throw NoEmailConfigException("fromName")
        val from = SettingsOperator.getMailValue(MailSettings::from) ?: throw NoEmailConfigException("from")

        val mimeMessage = mailSender.createMimeMessage()
        val messageHelper = MimeMessageHelper(mimeMessage, true)
        messageHelper.apply {
            setSubject(subject)
            setFrom(from, fromName)
            setSentDate(Date())
            setTo(to)
            setText(text, html)
        }
        mailSender.send(mimeMessage)
    }
}