package top.fatweb.oxygen.api.param.tool

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ToolTemplateAddParam(
    @field: NotBlank(message = "Name can not be blank")
    val name: String?,

    @field: NotNull(message = "BaseId can not be null")
    val baseId: Long? = null,

    val source: String = "",

    val enable: Boolean = true
)
