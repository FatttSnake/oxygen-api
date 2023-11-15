package top.fatweb.api.param.authentication

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

@Schema(description = "用户组更新请求参数")
data class GroupUpdateParam(
    @Schema(description = "用户组 ID")
    @field:Min(0)
    val id: Long,

    @Schema(description = "用户组名称")
    @field:NotBlank(message = "Name can not be blank")
    val name: String?,

    @Schema(description = "启用", allowableValues = ["true", "false"])
    val enable: Boolean? = true,

    @Schema(description = "角色 ID 列表")
    val roleIds: List<Long>? = null
)
