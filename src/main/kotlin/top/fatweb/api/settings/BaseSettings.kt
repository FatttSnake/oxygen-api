package top.fatweb.api.settings

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Base settings entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class BaseSettings(
    /**
     * Application name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var appName: String? = null,

    /**
     * Application URL
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var appUrl: String? = null,

    /**
     * Verify URL
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var verifyUrl: String? = null,

    /**
     * Retrieve URL
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var retrieveUrl: String? = null
)
