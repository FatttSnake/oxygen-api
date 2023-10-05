package top.fatweb.api.entity.common

enum class ResponseCode(val code: Int) {
    SYSTEM_OK(BusinessCode.SYSTEM, 0),
    SYSTEM_LOGIN_SUCCESS(BusinessCode.SYSTEM, 20),
    SYSTEM_PASSWORD_CHANGE_SUCCESS(BusinessCode.SYSTEM, 21),
    SYSTEM_LOGOUT_SUCCESS(BusinessCode.SYSTEM, 22),
    SYSTEM_TOKEN_RENEW_SUCCESS(BusinessCode.SYSTEM, 23),
    SYSTEM_UNAUTHORIZED(BusinessCode.SYSTEM, 30),
    SYSTEM_ACCESS_DENIED(BusinessCode.SYSTEM, 31),
    SYSTEM_USER_DISABLE(BusinessCode.SYSTEM, 32),
    SYSTEM_LOGIN_USERNAME_PASSWORD_ERROR(BusinessCode.SYSTEM, 33),
    SYSTEM_OLD_PASSWORD_NOT_MATCH(BusinessCode.SYSTEM, 34),
    SYSTEM_LOGOUT_FAILED(BusinessCode.SYSTEM, 35),
    SYSTEM_TOKEN_ILLEGAL(BusinessCode.SYSTEM, 36),
    SYSTEM_TOKEN_HAS_EXPIRED(BusinessCode.SYSTEM, 37),
    SYSTEM_REQUEST_ILLEGAL(BusinessCode.SYSTEM, 40),
    SYSTEM_ERROR(BusinessCode.SYSTEM, 50),
    SYSTEM_TIMEOUT(BusinessCode.SYSTEM, 51);

    constructor(businessCode: BusinessCode, code: Int) : this(businessCode.code * 100 + code)
}