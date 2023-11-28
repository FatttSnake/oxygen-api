package top.fatweb.api.mapper.permission

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
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

    fun selectPage(page: IPage<Long>, searchValue: String?, searchRegex: Boolean): IPage<Long>

    fun getWithRoleInfoByList(userIds: List<Long>): List<User>?

    fun getOneWithRoleInfo(id: Long): User?

    fun getListWithRoleInfo(): List<User>
}
