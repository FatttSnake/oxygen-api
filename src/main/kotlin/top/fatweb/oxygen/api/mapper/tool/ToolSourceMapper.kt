package top.fatweb.oxygen.api.mapper.tool

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import top.fatweb.oxygen.api.entity.tool.ToolSource

/**
 * Tool source mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.3.0
 * @see BaseMapper
 * @see ToolSource
 */
@Mapper
interface ToolSourceMapper : BaseMapper<ToolSource> {
    fun getDescendantNodeIds(
        @Param("rootId") rootId: Long,
        @Param("nodeId") nodeId: Long
    ): List<Long>
}
