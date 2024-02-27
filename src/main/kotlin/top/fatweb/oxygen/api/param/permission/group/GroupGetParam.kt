package top.fatweb.oxygen.api.param.permission.group

import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.oxygen.api.annotation.Trim
import top.fatweb.oxygen.api.param.PageSortParam

/**
 * Get group parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see PageSortParam
 */
@Trim
@Schema(description = "用户组查询请求参数")
data class GroupGetParam(
    /**
     * Name to search for
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Trim
    @Schema(description = "查询用户组名称", example = "Group_1")
    var searchName: String?,

    /**
     * Use regex
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(
        description = "查询使用正则表达式",
        allowableValues = ["true", "false"],
        defaultValue = "false",
        example = "false"
    )
    val searchRegex: Boolean = false,
) : PageSortParam()
