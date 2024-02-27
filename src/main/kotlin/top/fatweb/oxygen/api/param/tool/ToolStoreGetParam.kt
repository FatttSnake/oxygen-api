package top.fatweb.oxygen.api.param.tool

import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.oxygen.api.annotation.Trim
import top.fatweb.oxygen.api.param.PageSortParam

/**
 * Get tool parameters in tool store
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see PageSortParam
 */
@Trim
data class ToolStoreGetParam(
    /**
     * Value to search for
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Trim
    @Schema(description = "查询内容", example = "ToolName")
    var searchValue: String?
) : PageSortParam()
