package top.fatweb.api.settings

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Mail settings entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class MailSettings(
    /**
     * Host
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var host: String? = null,

    /**
     * Port
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var port: Int? = null,

    /**
     * Username
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var username: String? = null,

    /**
     * Password
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var password: String? = null,

    /**
     * Sender
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var from: String? = null,
)