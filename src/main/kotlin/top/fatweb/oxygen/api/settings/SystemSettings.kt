package top.fatweb.oxygen.api.settings

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * System setting entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class SystemSettings(
    /**
     * Base setting
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var base: BaseSettings? = null,

    /**
     * Mail setting
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var mail: MailSettings? = null
)
