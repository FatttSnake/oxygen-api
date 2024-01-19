package top.fatweb.oxygen.api.param.tool

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class ToolTemplateUpdateParam(
    @field: NotNull(message = "ID can not be null")
    val id: Long?,

    val name: String?,

    @field: Pattern(regexp = "^\\d+\\.\\d+\\.\\d+\$", message = "Ver can only match '<number>.<number>.<number>'")
    val ver: String?,

    val baseId: Long?,

    val source: String?,

    val dist: String?,

    val enable: Boolean?
)
