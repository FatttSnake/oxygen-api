package top.fatweb.oxygen.api.exception

/**
 * Database delete exception
 *
 * @param message Exception message
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see RuntimeException
 */
class DatabaseDeleteException(message: String = "Database delete failed"): RuntimeException(message)