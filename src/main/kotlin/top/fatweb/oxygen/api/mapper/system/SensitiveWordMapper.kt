package top.fatweb.oxygen.api.mapper.system

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import top.fatweb.oxygen.api.entity.system.SensitiveWord

/**
 * Sensitive word mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see BaseMapper
 * @see SensitiveWord
 */
@Mapper
interface SensitiveWordMapper : BaseMapper<SensitiveWord>