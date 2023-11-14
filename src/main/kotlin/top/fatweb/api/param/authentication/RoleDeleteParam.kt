package top.fatweb.api.param.authentication

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "角色删除请求参数")
data class RoleDeleteParam(
    @Schema(description = "角色 ID 列表")
    val ids: List<Long>
)
