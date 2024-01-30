package top.fatweb.oxygen.api.mapper.tool

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.entity.tool.ToolTemplate

/**
 * Tool edit mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see BaseMapper
 * @see Tool
 */
@Mapper
interface EditMapper : BaseMapper<Tool> {
    fun getTemplate(@Param("id") id: Long): ToolTemplate?

    fun selectOne(@Param("id") id: Long, @Param("userId") userId: Long): Tool?

    fun selectPersonal(@Param("userId") userId: Long): List<Tool>

    fun detail(
        @Param("username") username: String,
        @Param("toolId") toolId: String,
        @Param("ver") ver: String,
        @Param("operator") operator: String?
    ): Tool?
}