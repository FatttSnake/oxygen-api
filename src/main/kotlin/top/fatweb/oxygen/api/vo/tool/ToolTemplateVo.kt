package top.fatweb.oxygen.api.vo.tool

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.time.LocalDateTime

data class ToolTemplateVo(
    @JsonSerialize(using = ToStringSerializer::class)
    val id: Long?,

    val name: String?,

    val ver: String?,

    @JsonSerialize(using = ToStringSerializer::class)
    val baseId: Long?,

    val source: ToolDataVo?,

    val dist: ToolDataVo?,

    val createTime: LocalDateTime?,

    val updateTime: LocalDateTime?
)