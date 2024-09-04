package top.fatweb.oxygen.api.exception

/**
 * Database select exception
 *
 * @param message Exception message
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see RuntimeException
 */
class DatabaseSelectException(message: String = "Database select failed"): RuntimeException(message)