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
 * @see BaseMapper
 * @see Group
 */
@Mapper
interface GroupMapper : BaseMapper<Group> {
    /**
     * Select group in page
     *
     * @param page Pagination
     * @param searchName Name to search for
     * @param searchRegex Use regex
     * @return Group in page
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see IPage
     */
    fun selectPage(page: IPage<Long>, searchName: String?, searchRegex: Boolean): IPage<Long>

    /**
     * Select group with role list by list of group IDs
     *
     * @param groupIds List of group IDs
     * @return Group with role list
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Group
     */
    fun selectListWithRoleByIds(groupIds: List<Long>): List<Group>?

    /**
     * Select one group by ID
     *
     * @param id Group ID
     * @return Group object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Group
     */
    fun selectOneById(id: Long): Group?
}
