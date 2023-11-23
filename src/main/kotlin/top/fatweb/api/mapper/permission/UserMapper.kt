package top.fatweb.api.mapper.permission

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import top.fatweb.api.entity.permission.User

/**
 * User mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Mapper
interface UserMapper : BaseMapper<User> {
    fun getOneWithPowerInfoByUsername(@Param("username")username: String): User?

    fun getListWithRoleInfo(): List<User>
}
