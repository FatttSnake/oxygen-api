package top.fatweb.oxygen.api.entity.common

/**
 * Business code entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
enum class BusinessCode(val code: Int) {
    /**
     * System
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    SYSTEM(100),

    /**
     * Permission
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    PERMISSION(200),

    /**
     * Database
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    DATABASE(300),

    /**
     * Avatar API
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    API_AVATAR(501)
}