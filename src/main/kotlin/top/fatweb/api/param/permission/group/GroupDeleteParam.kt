package top.fatweb.api.param.permission.group

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Delete group parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "用户组删除请求参数")
data class GroupDeleteParam(
    /**
     * List of group IDs
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "用户组 ID 列表")
    val ids: List<Long>
)
