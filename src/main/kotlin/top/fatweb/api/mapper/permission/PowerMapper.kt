package top.fatweb.api.mapper.permission

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import top.fatweb.api.entity.permission.Power

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
@Mapper
interface PowerMapper : BaseMapper<Power>
