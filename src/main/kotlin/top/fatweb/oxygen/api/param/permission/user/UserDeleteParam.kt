package top.fatweb.oxygen.api.param.permission.user

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Delete user parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "用户删除请求参数")
data class UserDeleteParam(
    /**
     * List of user IDs
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "用户 ID 列表", required = true)
    val ids: List<Long>
)
