package top.fatweb.oxygen.api.properties

import jakarta.validation.constraints.NotBlank
import org.springframework.validation.annotation.Validated

/**
 * Admin properties
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.2.0
 */
@Validated
data class AdminProperties(
    /**
     * Username
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    @field:NotBlank val username: String = "admin",

    /**
     * Password
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    val password: String? = null,

    /**
     * Nickname
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    @field:NotBlank val nickname: String = "Administrator",

    /**
     * Email
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    @field:NotBlank val email: String = "admin@mail.com"
)
