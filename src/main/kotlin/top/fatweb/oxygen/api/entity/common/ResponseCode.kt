package top.fatweb.oxygen.api.entity.common

/**
 * Response code entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
enum class ResponseCode(val code: Int) {
    SYSTEM_OK(BusinessCode.SYSTEM, 0),

    SYSTEM_ERROR(BusinessCode.SYSTEM, 50),
    SYSTEM_TIMEOUT(BusinessCode.SYSTEM, 51),
    SYSTEM_REQUEST_ILLEGAL(BusinessCode.SYSTEM, 52),
    SYSTEM_ARGUMENT_NOT_VALID(BusinessCode.SYSTEM, 53),
    SYSTEM_INVALID_CAPTCHA_CODE(BusinessCode.SYSTEM, 54),
    SYSTEM_REQUEST_TOO_FREQUENT(BusinessCode.SYSTEM, 55),
    SYSTEM_MATCH_SENSITIVE_WORD(BusinessCode.SYSTEM, 56),

    PERMISSION_LOGIN_SUCCESS(BusinessCode.PERMISSION, 0),
    PERMISSION_PASSWORD_CHANGE_SUCCESS(BusinessCode.PERMISSION, 1),
    PERMISSION_LOGOUT_SUCCESS(BusinessCode.PERMISSION, 2),
    PERMISSION_TOKEN_RENEW_SUCCESS(BusinessCode.PERMISSION, 3),
    PERMISSION_REGISTER_SUCCESS(BusinessCode.PERMISSION, 4),
    PERMISSION_RESEND_SUCCESS(BusinessCode.PERMISSION, 5),
    PERMISSION_VERIFY_SUCCESS(BusinessCode.PERMISSION, 6),
    PERMISSION_FORGET_SUCCESS(BusinessCode.PERMISSION, 7),
    PERMISSION_RETRIEVE_SUCCESS(BusinessCode.PERMISSION, 8),

    PERMISSION_UNAUTHORIZED(BusinessCode.PERMISSION, 50),
    PERMISSION_USERNAME_NOT_FOUND(BusinessCode.PERMISSION, 51),
    PERMISSION_ACCESS_DENIED(BusinessCode.PERMISSION, 52),
    PERMISSION_USER_LOCKED(BusinessCode.PERMISSION, 53),
    PERMISSION_USER_EXPIRED(BusinessCode.PERMISSION, 54),
    PERMISSION_USER_CREDENTIALS_EXPIRED(BusinessCode.PERMISSION, 55),
    PERMISSION_USER_DISABLE(BusinessCode.PERMISSION, 56),
    PERMISSION_LOGIN_USERNAME_PASSWORD_ERROR(BusinessCode.PERMISSION, 57),
    PERMISSION_OLD_PASSWORD_NOT_MATCH(BusinessCode.PERMISSION, 58),
    PERMISSION_LOGOUT_FAILED(BusinessCode.PERMISSION, 59),
    PERMISSION_TOKEN_ILLEGAL(BusinessCode.PERMISSION, 60),
    PERMISSION_TOKEN_HAS_EXPIRED(BusinessCode.PERMISSION, 61),
    PERMISSION_NO_VERIFICATION_REQUIRED(BusinessCode.PERMISSION, 62),
    PERMISSION_VERIFY_CODE_ERROR_OR_EXPIRED(BusinessCode.PERMISSION, 63),
    PERMISSION_ACCOUNT_NEED_INIT(BusinessCode.PERMISSION, 64),
    PERMISSION_USER_NOT_FOUND(BusinessCode.PERMISSION, 65),
    PERMISSION_RETRIEVE_CODE_ERROR_OR_EXPIRED(BusinessCode.PERMISSION, 66),
    PERMISSION_ACCOUNT_NEED_RESET_PASSWORD(BusinessCode.PERMISSION, 67),
    PERMISSION_NEED_TWO_FACTOR(BusinessCode.PERMISSION, 68),
    PERMISSION_ALREADY_HAS_TWO_FACTOR(BusinessCode.PERMISSION, 69),
    PERMISSION_NO_TWO_FACTOR_FOUND(BusinessCode.PERMISSION, 70),
    PERMISSION_TWO_FACTOR_VERIFICATION_CODE_ERROR(BusinessCode.PERMISSION, 71),

    DATABASE_SELECT_SUCCESS(BusinessCode.DATABASE, 0),
    DATABASE_SELECT_FAILED(BusinessCode.DATABASE, 5),
    DATABASE_INSERT_SUCCESS(BusinessCode.DATABASE, 10),
    DATABASE_INSERT_FAILED(BusinessCode.DATABASE, 15),
    DATABASE_UPDATE_SUCCESS(BusinessCode.DATABASE, 20),
    DATABASE_UPDATE_FAILED(BusinessCode.DATABASE, 25),
    DATABASE_DELETE_SUCCESS(BusinessCode.DATABASE, 30),
    DATABASE_DELETE_FAILED(BusinessCode.DATABASE, 35),
    DATABASE_EXECUTE_ERROR(BusinessCode.DATABASE, 50),
    DATABASE_DUPLICATE_KEY(BusinessCode.DATABASE, 51),
    DATABASE_NO_RECORD_FOUND(BusinessCode.DATABASE, 52),
    DATABASE_RECORD_ALREADY_EXISTS(BusinessCode.DATABASE, 53),

    TOOL_SUBMIT_SUCCESS(BusinessCode.TOOL, 10),
    TOOL_CANCEL_SUCCESS(BusinessCode.TOOL, 11),
    TOOL_ILLEGAL_VERSION(BusinessCode.TOOL, 50),
    TOOL_UNDER_REVIEW(BusinessCode.TOOL, 51),
    TOOL_NOT_UNDER_REVIEW(BusinessCode.TOOL, 52),
    TOOL_HAS_UNPUBLISHED_VERSION(BusinessCode.TOOL, 53),
    TOOL_HAS_NOT_BEEN_PUBLISHED(BusinessCode.TOOL, 54),
    TOOL_HAS_BEEN_PUBLISHED(BusinessCode.TOOL, 55),
    TOOL_SUBMIT_ERROR(BusinessCode.TOOL, 60),
    TOOL_CANCEL_ERROR(BusinessCode.TOOL, 61),

    API_AVATAR_SUCCESS(BusinessCode.API_AVATAR, 0),
    API_AVATAR_ERROR(BusinessCode.API_AVATAR, 50);

    constructor(businessCode: BusinessCode, code: Int) : this(businessCode.code * 100 + code)
}