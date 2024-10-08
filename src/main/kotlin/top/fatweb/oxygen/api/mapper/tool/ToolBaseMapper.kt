package top.fatweb.oxygen.api.mapper.tool

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import top.fatweb.oxygen.api.entity.tool.ToolBase

/**
 * Tool base mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see BaseMapper
 * @see ToolBase
 */
@Mapper
interface ToolBaseMapper : BaseMapper<ToolBase> {
    /**
     * Select tool base by ID
     *
     * @param id Tool base ID
     * @return ToolBase object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBase
     */
    fun selectOne(@Param("id") id: Long): ToolBase?
}