package top.fatweb.api.entity.common

/**
 * Response code entity
 *
 * @author FatttSnake
 * @since 1.0.0
 */
enum class ResponseCode(val code: Int) {
    SYSTEM_OK(BusinessCode.SYSTEM, 0),
    SYSTEM_LOGIN_SUCCESS(BusinessCode.SYSTEM, 20),
    SYSTEM_PASSWORD_CHANGE_SUCCESS(BusinessCode.SYSTEM, 21),
    SYSTEM_LOGOUT_SUCCESS(BusinessCode.SYSTEM, 22),
    SYSTEM_TOKEN_RENEW_SUCCESS(BusinessCode.SYSTEM, 23),
    SYSTEM_UNAUTHORIZED(BusinessCode.SYSTEM, 30),
    SYSTEM_USERNAME_NOT_FOUND(BusinessCode.SYSTEM, 31),
    SYSTEM_ACCESS_DENIED(BusinessCode.SYSTEM, 32),
    SYSTEM_USER_DISABLE(BusinessCode.SYSTEM, 33),
    SYSTEM_LOGIN_USERNAME_PASSWORD_ERROR(BusinessCode.SYSTEM, 34),
    SYSTEM_OLD_PASSWORD_NOT_MATCH(BusinessCode.SYSTEM, 35),
    SYSTEM_LOGOUT_FAILED(BusinessCode.SYSTEM, 36),
    SYSTEM_TOKEN_ILLEGAL(BusinessCode.SYSTEM, 37),
    SYSTEM_TOKEN_HAS_EXPIRED(BusinessCode.SYSTEM, 38),
    SYSTEM_REQUEST_ILLEGAL(BusinessCode.SYSTEM, 40),
    SYSTEM_ARGUMENT_NOT_VALID(BusinessCode.SYSTEM, 41),
    SYSTEM_ERROR(BusinessCode.SYSTEM, 50),
    SYSTEM_TIMEOUT(BusinessCode.SYSTEM, 51),

    DATABASE_SELECT_SUCCESS(BusinessCode.DATABASE, 0),
    DATABASE_SELECT_FAILED(BusinessCode.DATABASE, 5),
    DATABASE_INSERT_SUCCESS(BusinessCode.DATABASE, 10),
    DATABASE_INSERT_FAILED(BusinessCode.DATABASE, 15),
    DATABASE_UPDATE_SUCCESS(BusinessCode.DATABASE, 20),
    DATABASE_UPDATE_FILED(BusinessCode.DATABASE, 25),
    DATABASE_DELETE_SUCCESS(BusinessCode.DATABASE, 30),
    DATABASE_DELETE_FILED(BusinessCode.DATABASE, 35),
    DATABASE_EXECUTE_ERROR(BusinessCode.DATABASE, 40),
    DATABASE_DUPLICATE_KEY(BusinessCode.DATABASE, 45);

    constructor(businessCode: BusinessCode, code: Int) : this(businessCode.code * 100 + code)
}