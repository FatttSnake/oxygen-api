package top.fatweb.oxygen.api.vo.tool

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.time.LocalDateTime

data class ToolDataVo(
    @JsonSerialize(using = ToStringSerializer::class)
    val id: Long?,

    val data: String?,

    val createTime: LocalDateTime?,

    val updateTime: LocalDateTime?
)