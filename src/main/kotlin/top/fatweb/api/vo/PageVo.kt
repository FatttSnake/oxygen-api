package top.fatweb.api.vo

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "分页返回参数")
data class PageVo<T>(
    @Schema(description = "总数量", example = "100")
    val total: Long,

    @Schema(description = "总页码", example = "10")
    val pages: Long,

    @Schema(description = "分页大小", example = "10")
    val size: Long,

    @Schema(description = "当前页码", example = "2")
    val current: Long,

    @Schema(description = "数据")
    val records: List<T>
)
