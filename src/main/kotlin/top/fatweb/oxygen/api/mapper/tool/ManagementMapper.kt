package top.fatweb.oxygen.api.mapper.tool

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import top.fatweb.oxygen.api.entity.tool.Tool

/**
 * Tool management mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see BaseMapper
 * @see Tool
 */
@Mapper
interface ManagementMapper : BaseMapper<Tool> {
    /**
     * Select tool by ID
     *
     * @param id Tool ID
     * @return Tool object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Tool
     */
    fun selectOne(@Param("id") id: Long): Tool?

    /**
     * Select tool ID in page
     *
     * @param page Pagination
     * @param review Review
     * @param searchType Type of search
     * @param searchValue Value to search for
     * @param searchRegex Use regex
     * @return Tool ID in page
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see IPage
     */
    fun selectPage(
        page: IPage<Long>,
        @Param("review") review: List<String>?,
        @Param("platform") platform: List<String>?,
        @Param("searchType") searchType: String,
        @Param("searchValue") searchValue: String?,
        @Param("searchRegex") searchRegex: Boolean
    ): IPage<Long>

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