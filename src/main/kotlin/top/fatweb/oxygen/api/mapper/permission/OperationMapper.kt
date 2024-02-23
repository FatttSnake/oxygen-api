package top.fatweb.oxygen.api.mapper.permission

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import top.fatweb.oxygen.api.entity.permission.Operation

/**
 * Operation mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see BaseMapper
 * @see Operation
 */
@Mapper
interface OperationMapper : BaseMapper<Operation>
