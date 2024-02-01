package top.fatweb.oxygen.api.exception

/**
 * Request too frequent exception
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see RuntimeException
 */
class RequestTooFrequentException: RuntimeException("Request too frequent")