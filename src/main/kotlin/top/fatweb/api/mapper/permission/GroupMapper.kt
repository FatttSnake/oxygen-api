package top.fatweb.api.mapper.permission

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import org.apache.ibatis.annotations.Mapper
import top.fatweb.api.entity.permission.Group

/**
 * Group mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Mapper
interface GroupMapper : BaseMapper<Group> {
    fun selectPage(page: IPage<Long>, searchName: String?, searchRegex: Boolean): IPage<Long>

    fun getWithRoleByList(groupIds: List<Long>): List<Group>?

    fun selectOne(id: Long): Group?
}
