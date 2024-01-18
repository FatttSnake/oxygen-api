package top.fatweb.oxygen.api.mapper.tool

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import top.fatweb.oxygen.api.entity.tool.ToolTemplate

/**
 * Tool template mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see BaseMapper
 * @see ToolTemplate
 */
@Mapper
interface ToolTemplateMapper : BaseMapper<ToolTemplate> {
    fun selectOne(@Param("id") id: Long): ToolTemplate?

    fun selectList(): List<ToolTemplate>
}