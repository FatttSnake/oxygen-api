package top.fatweb.api.mapper.permission

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import org.apache.ibatis.annotations.Mapper
import top.fatweb.api.entity.permission.Group

/**
 * <p>
 * 用户组表 Mapper 接口
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
@Mapper
interface GroupMapper : BaseMapper<Group> {
    fun selectPage(page: IPage<Group>): IPage<Group>
}
