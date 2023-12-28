package top.fatweb.oxygen.api.param.permission.role

import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.oxygen.api.param.PageSortParam

/**
 * Get role parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see PageSortParam
 */
@Schema(description = "角色查询请求参数")
data class RoleGetParam(
    /**
     * Name to search for
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "查询角色名称", example = "Role_1")
    val searchName: String?,

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
