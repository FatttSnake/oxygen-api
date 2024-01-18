package top.fatweb.oxygen.api.param.tool

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class ToolUpdateParam(
    @field: NotNull(message = "ID can not be null")
    val id: Long?,

    val name: String?,

    @field: Pattern(
        regexp = "^[a-zA-Z-_][0-9a-zA-Z-_]{2,19}\$",
        message = "Ver can only match '^[a-zA-Z-_][0-9a-zA-Z-_]{2,19}\$'"
    )
    val toolId: String?,

    val description: String?,

    val authorId: Long?,

    @field: Pattern(regexp = "^\\d+\\.\\d+\\.\\d+\$", message = "Ver can only match '<number>.<number>.<number>'")
    val ver: String?,

    val privately: Boolean?,

    val keywords: List<String>,

    val categories: List<Long>,

    val source: String?,

    val dist: String?
)
