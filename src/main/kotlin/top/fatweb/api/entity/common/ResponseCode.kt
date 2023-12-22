package top.fatweb.api.entity.common

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

    PERMISSION_LOGIN_SUCCESS(BusinessCode.PERMISSION, 0),
    PERMISSION_PASSWORD_CHANGE_SUCCESS(BusinessCode.PERMISSION, 1),
    PERMISSION_LOGOUT_SUCCESS(BusinessCode.PERMISSION, 2),
    PERMISSION_TOKEN_RENEW_SUCCESS(BusinessCode.PERMISSION, 3),
    PERMISSION_REGISTER_SUCCESS(BusinessCode.PERMISSION, 4),
    PERMISSION_RESEND_SUCCESS(BusinessCode.PERMISSION, 5),
    PERMISSION_VERIFY_SUCCESS(BusinessCode.PERMISSION, 6),

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

    DATABASE_SELECT_SUCCESS(BusinessCode.DATABASE, 0),
    DATABASE_SELECT_FAILED(BusinessCode.DATABASE, 5),
    DATABASE_INSERT_SUCCESS(BusinessCode.DATABASE, 10),
    DATABASE_INSERT_FAILED(BusinessCode.DATABASE, 15),
    DATABASE_UPDATE_SUCCESS(BusinessCode.DATABASE, 20),
    DATABASE_UPDATE_FILED(BusinessCode.DATABASE, 25),
    DATABASE_DELETE_SUCCESS(BusinessCode.DATABASE, 30),
    DATABASE_DELETE_FILED(BusinessCode.DATABASE, 35),
    DATABASE_EXECUTE_ERROR(BusinessCode.DATABASE, 50),
    DATABASE_DUPLICATE_KEY(BusinessCode.DATABASE, 51),
    DATABASE_NO_RECORD_FOUND(BusinessCode.DATABASE, 52),

    API_AVATAR_SUCCESS(BusinessCode.API_AVATAR, 0),
    API_AVATAR_ERROR(BusinessCode.API_AVATAR, 50);

    constructor(businessCode: BusinessCode, code: Int) : this(businessCode.code * 100 + code)
}