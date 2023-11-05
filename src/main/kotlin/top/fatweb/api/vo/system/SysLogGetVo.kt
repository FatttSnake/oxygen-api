package top.fatweb.api.vo.system

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "获取系统日志返回参数")
class SysLogGetVo(
    @JsonSerialize(using = ToStringSerializer::class)
    val id: Long?,

    @Schema(description = "日志类型")
    val logType: String?,

    @Schema(description = "操作用户 ID")
    @JsonSerialize(using = ToStringSerializer::class)
    val operateUserId: Long?,

    @Schema(description = "操作时间")
    val operateTime: LocalDateTime?,

    @Schema(description = "请求 Uri")
    val requestUri: String?,

    @Schema(description = "请求方式")
    val requestMethod: String?,

    @Schema(description = "请求参数")
    val requestParams: String?,

    @Schema(description = "请求 IP")
    val requestIp: String?,

    @Schema(description = "请求服务器地址")
    val requestServerAddress: String?,

    @Schema(description = "是否异常")
    val exception: Boolean?,

    @Schema(description = "异常信息")
    val exceptionInfo: String?,

    @Schema(description = "开始时间")
    val startTime: LocalDateTime?,

    @Schema(description = "结束时间")
    val endTime: LocalDateTime?,

    @Schema(description = "执行时间")
    val executeTime: Long?,

    @Schema(description = "用户代理")
    val userAgent: String?,

    @Schema(description = "操作用户名")
    val operateUsername: String?
)
