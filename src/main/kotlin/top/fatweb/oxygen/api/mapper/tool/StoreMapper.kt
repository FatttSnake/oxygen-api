package top.fatweb.oxygen.api.mapper.tool

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import top.fatweb.oxygen.api.entity.tool.Tool

/**
 * Tool store mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see BaseMapper
 * @see Tool
 */
@Mapper
interface StoreMapper : BaseMapper<Tool> {
    /**
     * Select tool ID in page
     *
     * @param page Pagination
     * @param searchValue Value to search for
     * @return Tool ID in page
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see IPage
     */
    fun selectPage(page: IPage<Long>, @Param("searchValue") searchValue: String?): IPage<Long>

    /**
     * Select tool in list by tool IDs
     *
     * @param ids List of tool IDs
     * @return List of tool object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Tool
     */
    fun selectListByIds(@Param("ids") ids: List<Long>): List<Tool>
}