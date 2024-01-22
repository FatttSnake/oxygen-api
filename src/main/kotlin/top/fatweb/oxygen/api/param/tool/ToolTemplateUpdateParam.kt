package top.fatweb.oxygen.api.param.tool

import jakarta.validation.constraints.NotNull

data class ToolTemplateUpdateParam(
    @field: NotNull(message = "ID can not be null")
    val id: Long?,

    val name: String?,

    val baseId: Long?,

    val source: String?,

    val enable: Boolean?
)
