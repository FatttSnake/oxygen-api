package top.fatweb.oxygen.api.param.tool

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ToolCategoryUpdateParam(
    @field: NotNull(message = "ID can not be null")
    val id: Long?,

    @field: NotBlank(message = "Name can not be blank")
    val name: String?,

    val enable: Boolean?
)
