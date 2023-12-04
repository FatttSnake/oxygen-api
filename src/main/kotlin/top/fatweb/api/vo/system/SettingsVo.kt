package top.fatweb.api.vo.system

/**
 * System settings value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
data class SettingsVo(
    /**
     * MailSettingVo object
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see MailSettingsVo
     */
    val mail: MailSettingsVo?
) {
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
        val from: String?
    )
}