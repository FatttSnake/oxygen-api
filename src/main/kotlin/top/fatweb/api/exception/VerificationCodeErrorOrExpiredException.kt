package top.fatweb.api.exception

/**
 * Verification code error or expired exception
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
class VerificationCodeErrorOrExpiredException : RuntimeException("Verification code is error or has expired")