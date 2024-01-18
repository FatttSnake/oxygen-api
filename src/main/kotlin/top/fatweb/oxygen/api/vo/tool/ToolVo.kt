package top.fatweb.oxygen.api.vo.tool

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import top.fatweb.oxygen.api.vo.permission.base.UserInfoVo
import java.time.LocalDateTime

data class ToolVo (
    @JsonSerialize(using = ToStringSerializer::class)
    val id: Long?,

    val name: String?,

    val toolId: String?,

    val description: String?,

    @JsonSerialize(using = ToStringSerializer::class)
    val baseId: Long?,

    val author: UserInfoVo?,

    val ver: String?,

    val privately: Boolean?,

    val keywords: List<String>?,

    val categories: List<ToolCategoryVo>?,

    val source: ToolDataVo?,

    val dist: ToolDataVo?,

    val publish: Boolean?,

    val review: Int?,

    val createTime: LocalDateTime?,

    val updateTime: LocalDateTime?
)