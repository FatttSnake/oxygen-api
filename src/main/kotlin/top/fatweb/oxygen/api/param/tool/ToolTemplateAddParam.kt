package top.fatweb.oxygen.api.param.tool

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class ToolTemplateAddParam(
    @field: NotBlank(message = "Name can not be blank")
    val name: String?,

    @field: NotBlank(message = "Ver can not be blank")
    @field: Pattern(regexp = "^\\d+\\.\\d+\\.\\d+\$", message = "Ver can only match '<number>.<number>.<number>'")
    val ver: String?,

    @field: NotNull(message = "BaseId can not be null")
    val baseId: Long? = null,

    @field: NotNull(message = "Source can not be null")
    val source: String?,

    @field:NotNull(message = "Dist can not be null")
    val dist: String?
)
