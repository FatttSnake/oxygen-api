package top.fatweb.api.param.authentication

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Role delete param
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "角色删除请求参数")
data class RoleDeleteParam(
    @Schema(description = "角色 ID 列表")
    val ids: List<Long>
)
