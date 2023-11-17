package top.fatweb.api.param

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min

/**
 * Page sort param
 *
 * @author FatttSnake
 * @since 1.0.0
 */
open class PageSortParam {
    @Schema(description = "分页页码", example = "1", defaultValue = "1")
    @field:Min(1, message = "Pagination page number must be a positive integer")
    var currentPage: Long = 1

    @Schema(description = "分页大小", example = "20", defaultValue = "20")
    @field:Min(1, message = "The number of data per page must be a positive integer")
    var pageSize: Long = 20

    @Schema(description = "排序字段", example = "id")
    var sortField: String? = null

    @Schema(description = "排序方式", example = "desc", allowableValues = ["desc", "asc"])
    var sortOrder: String? = null
}