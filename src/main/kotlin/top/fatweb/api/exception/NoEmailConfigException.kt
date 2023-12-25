package top.fatweb.api.exception

/**
 * Email settings not configured exception
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
class NoEmailConfigException(
    vararg configs: String
) : RuntimeException("Email settings not configured: ${configs.joinToString(", ")}")