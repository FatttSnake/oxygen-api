package top.fatweb.oxygen.api.param.tool

import jakarta.validation.constraints.NotNull

data class ToolBaseUpdateParam(
    @field: NotNull(message = "ID can not be null")
    val id: Long?,

    val name: String?,

    val source: String?,

    val dist: String?,

    val enable: Boolean?
)
