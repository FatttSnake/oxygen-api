package top.fatweb.oxygen.api.param.tool

import jakarta.validation.constraints.NotBlank

data class ToolCategoryAddParam(
    @field: NotBlank(message = "Name can not be blank")
    val name: String?,

    val enable: Boolean = true
)
