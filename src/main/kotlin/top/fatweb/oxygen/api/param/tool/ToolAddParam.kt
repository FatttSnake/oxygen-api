package top.fatweb.oxygen.api.param.tool

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class ToolAddParam(
    @field: NotBlank(message = "Name can not be blank")
    val name: String?,

    @field: NotBlank(message = "ToolId can not be blank")
    @field: Pattern(
        regexp = "^[a-zA-Z-_][0-9a-zA-Z-_]{2,19}\$",
        message = "Ver can only match '^[a-zA-Z-_][0-9a-zA-Z-_]{2,19}\$'"
    )
    val toolId: String?,

    val description: String?,

    @field: NotNull(message = "BaseId can not be null")
    val baseId: Long?,

    @field: NotNull(message = "AuthorId can not be null")
    val authorId: Long?,

    @field: NotBlank(message = "Ver can not be blank")
    @field: Pattern(regexp = "^\\d+\\.\\d+\\.\\d+\$", message = "Ver can only match '<number>.<number>.<number>'")
    val ver: String?,

    val privately: Boolean = false,

    @field: NotEmpty(message = "Keywords can not be empty")
    val keywords: List<String>,

    @field: NotEmpty(message = "Categories can not be empty")
    val categories: List<Long>,

    @field: NotNull(message = "Source can not be null")
    val source: String?,

    @field:NotNull(message = "Dist can not be null")
    val dist: String?
)
