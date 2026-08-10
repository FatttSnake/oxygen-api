package top.fatweb.oxygen.api.mapper.tool

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import top.fatweb.oxygen.api.entity.tool.ToolFileVersion

/**
 * Tool file version mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.3.0
 * @see BaseMapper
 * @see ToolFileVersion
 */
@Mapper
interface ToolFileVersionMapper : BaseMapper<ToolFileVersion>
