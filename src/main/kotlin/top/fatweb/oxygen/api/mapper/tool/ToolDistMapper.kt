package top.fatweb.oxygen.api.mapper.tool

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import top.fatweb.oxygen.api.entity.tool.ToolDist

/**
 * Tool dist mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.2.0
 * @see BaseMapper
 * @see ToolDist
 */
@Mapper
interface ToolDistMapper : BaseMapper<ToolDist>
