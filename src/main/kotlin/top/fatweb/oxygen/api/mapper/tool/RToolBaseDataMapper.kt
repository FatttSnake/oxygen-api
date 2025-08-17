package top.fatweb.oxygen.api.mapper.tool

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import top.fatweb.oxygen.api.entity.tool.RToolBaseData

/**
 * Tool base data intermediate mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see BaseMapper
 * @see RToolBaseData
 */
@Mapper
interface RToolBaseDataMapper : BaseMapper<RToolBaseData>
