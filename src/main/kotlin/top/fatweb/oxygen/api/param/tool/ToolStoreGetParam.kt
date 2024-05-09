package top.fatweb.oxygen.api.param.tool

import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.oxygen.api.annotation.Trim
import top.fatweb.oxygen.api.entity.tool.Platform
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
    var searchValue: String?,

    /**
     * Platform to search for
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Platform
     */
    @Schema(description = "指定平台", example = "DESKTOP")
    val platform: Platform
) : PageSortParam()
