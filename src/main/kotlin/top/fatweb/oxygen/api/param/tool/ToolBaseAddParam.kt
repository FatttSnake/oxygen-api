package top.fatweb.oxygen.api.param.tool

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ToolBaseAddParam(
    @field: NotBlank(message = "Name can not be blank")
    val name: String?,

    @field: NotNull(message = "Source can not be null")
    val source: String?,

    @field:NotNull(message = "Dist can not be null")
    val dist: String?
)
