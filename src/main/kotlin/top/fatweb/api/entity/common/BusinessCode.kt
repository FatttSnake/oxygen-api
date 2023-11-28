package top.fatweb.api.entity.common

/**
 * Business code entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
enum class BusinessCode(val code: Int) {
    SYSTEM(100),
    PERMISSION(200),
    DATABASE(300),
    API_AVATAR(501)
}