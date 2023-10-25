package top.fatweb.api.mapper.permission

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
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
interface UserMapper : BaseMapper<User> {
    fun getOneWithPowerByUsername(@Param("username")username: String): User?
}
