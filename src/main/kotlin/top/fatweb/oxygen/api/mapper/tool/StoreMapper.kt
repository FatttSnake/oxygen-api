package top.fatweb.oxygen.api.mapper.tool

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import top.fatweb.oxygen.api.entity.tool.Platform
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
     * Select author and tool ID in page
     *
     * @param page Pagination
     * @param searchValue Value to search for
     * @return Author:Tool_ID in page
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see IPage
     */
    fun selectAuthorToolIdPage(page: IPage<Long>, @Param("searchValue") searchValue: String?): IPage<String>

    /**
     * Select author and tool ID by username in page
     *
     * @param page Pagination
     * @param username Username
     * @return Author:Tool_ID in page
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see IPage
     */
    fun selectAuthorToolIdPageByUsername(page: IPage<Long>, @Param("username") username: String): IPage<String>

    /**
     * Select tool in list by Author:Tool_ID
     *
     * @param ids List of Author:Tool_ID
     * @return List of tool object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Tool
     */
    fun selectListByAuthorToolIds(
        @Param("ids") ids: List<String>,
        @Param("operator") operator: Long?,
        @Param("platform") platform: Platform? = null
    ): List<Tool>

    /**
     * Count published tool by username and toolId
     *
     * @param authorId Author ID
     * @param toolId Tool ID
     * @return Number
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun countPublishedToolByAuthorAndToolId(@Param("authorId") authorId: Long, @Param("toolId") toolId: String): Long
}