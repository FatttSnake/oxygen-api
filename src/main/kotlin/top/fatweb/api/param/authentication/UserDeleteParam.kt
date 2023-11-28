package top.fatweb.api.param.authentication

import io.swagger.v3.oas.annotations.media.Schema

/**
 * User delete param
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "用户删除请求参数")
data class UserDeleteParam(
    @Schema(description = "用户 ID 列表")
    val ids: List<Long>
)
