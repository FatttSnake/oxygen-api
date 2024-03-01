package top.fatweb.oxygen.api.settings

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Two-factor settings entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class TwoFactorSettings(
    /**
     * Issuer
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var issuer: String? = null,

    /**
     * Length of secret key
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var secretKeyLength: Int? = 16
)
