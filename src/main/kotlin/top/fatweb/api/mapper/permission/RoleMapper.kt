package top.fatweb.api.mapper.permission

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import org.apache.ibatis.annotations.Mapper
import top.fatweb.api.entity.permission.Role

/**
 * Role mapper
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@Mapper
interface RoleMapper : BaseMapper<Role> {
    fun selectPage(page: IPage<Long>, searchName: String?, searchRegex: Boolean): IPage<Long>

    fun getWithPowerByList(roleIds: List<Long>): List<Role>?

    fun selectOne(id: Long): Role?
}
