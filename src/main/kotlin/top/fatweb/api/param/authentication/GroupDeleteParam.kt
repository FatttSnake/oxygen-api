package top.fatweb.api.param.authentication

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "用户组删除请求参数")
data class GroupDeleteParam(
    @Schema(description = "用户组 ID 列表")
    val ids: List<Long>
)
