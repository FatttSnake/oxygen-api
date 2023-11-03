package top.fatweb.api.param.system

import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.api.param.PageParam

@Schema(description = "获取系统日志请求参数")
data class SysLogGetParam(
    val logType: String? = null
) : PageParam()