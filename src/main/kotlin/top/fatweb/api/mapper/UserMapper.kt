package top.fatweb.api.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import top.fatweb.api.entity.permission.User

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-04
 */
@Mapper
interface UserMapper : BaseMapper<User>
