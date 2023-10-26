package top.fatweb.api.entity.common

import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable

class ResponseResult<T> private constructor(
    @Schema(description = "响应码", defaultValue = "200") val code: Int,

    @Schema(description = "是否调用成功") val success: Boolean,

    @Schema(description = "信息") val msg: String,

    @Schema(description = "数据") val data: T?
) : Serializable {
    companion object {
        fun <T> build(code: ResponseCode, success: Boolean, msg: String, data: T?) =
            ResponseResult(code.code, success, msg, data)

        fun <T> success(code: ResponseCode = ResponseCode.SYSTEM_OK, msg: String = "success", data: T? = null) =
            build(code, true, msg, data)

        fun <T> fail(code: ResponseCode = ResponseCode.SYSTEM_ERROR, msg: String = "fail", data: T? = null) =
            build(code, false, msg, data)

        fun <T> databaseSuccess(
            code: ResponseCode = ResponseCode.DATABASE_SELECT_SUCCESS, msg: String = "success", data: T? = null
        ) = build(code, true, msg, data)

        fun <T> databaseFail(
            code: ResponseCode = ResponseCode.DATABASE_SELECT_FAILED, msg: String = "fail", data: T? = null
        ) = build(code, false, msg, data)
    }
}