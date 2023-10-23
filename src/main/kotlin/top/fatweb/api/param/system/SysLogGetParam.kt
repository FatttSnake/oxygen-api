package top.fatweb.api.param.system

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import java.io.Serializable

@Schema(description = "获取系统日志请求参数")
class SysLogGetParam : Serializable {

    @Schema(description = "分页页码", example = "1", defaultValue = "1")
    @Min(1, message = "Pagination page number must be a positive integer")
    val page: Long = 1

    @Schema(description = "分页大小", example = "20", defaultValue = "20")
    @Min(1, message = "The number of data per page must be a positive integer")
    val pageSize: Long = 20
}