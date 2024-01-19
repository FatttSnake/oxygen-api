package top.fatweb.oxygen.api.param.tool

import jakarta.validation.constraints.NotBlank

data class ToolBaseAddParam(
    @field: NotBlank(message = "Name can not be blank")
    val name: String?,

    val source: String = "",

    val dist: String = "",

    val enable: Boolean = true
)
