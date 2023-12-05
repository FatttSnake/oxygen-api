package top.fatweb.api.vo.system

import top.fatweb.api.settings.MailSecurityType

/**
 * Mail settings value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
data class MailSettingsVo(
    /**
     * Host
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val host: String?,

    /**
     * Port
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val port: Int?,

    /**
     * Security type
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val securityType: MailSecurityType?,

    /**
     * Username
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val username: String?,

    /**
     * Password
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val password: String?,

    /**
     * Sender
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val from: String?,

    /**
     * Sender name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val fromName: String?
)