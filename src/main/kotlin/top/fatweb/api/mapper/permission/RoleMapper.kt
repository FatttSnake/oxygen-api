package top.fatweb.api.mapper.permission

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import org.apache.ibatis.annotations.Mapper
import top.fatweb.api.entity.permission.Role

/**
 * Role mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Mapper
interface RoleMapper : BaseMapper<Role> {
    fun selectPage(page: IPage<Long>, searchName: String?, searchRegex: Boolean): IPage<Long>

    fun selectListWithPowerByIds(roleIds: List<Long>): List<Role>?

    fun selectOneById(id: Long): Role?
}
