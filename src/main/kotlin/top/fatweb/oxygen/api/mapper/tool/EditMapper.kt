package top.fatweb.oxygen.api.mapper.tool

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import top.fatweb.oxygen.api.entity.tool.Platform
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.entity.tool.ToolTemplate

/**
 * Tool edit mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see BaseMapper
 * @see Tool
 */
@Mapper
interface EditMapper : BaseMapper<Tool> {
    /**
     * Select tool template by ID
     *
     * @param id Template ID
     * @return ToolTemplate object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolTemplate
     */
    fun selectTemplate(@Param("id") id: Long): ToolTemplate?

    /**
     * Select tool by ID and user ID
     *
     * @param id Tool ID
     * @param userId User ID
     * @return Tool object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Tool
     */
    fun selectOne(@Param("id") id: Long, @Param("userId") userId: Long): Tool?

    /**
     * Select tool ID by user ID in page
     *
     * @param page Pagination
     * @param userId User ID
     * @return Tool ID in page
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see IPage
     */
    fun selectPersonalToolIdPage(page: IPage<String>, @Param("userId") userId: Long): IPage<String>

    /**
     * Select tool in list by tool IDs and user ID
     *
     * @param toolIds List of tool Ids
     * @param userId User ID
     * @return List of Tool object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Tool
     */
    fun selectListByToolIds(@Param("toolIds") toolIds: List<String>, @Param("userId") userId: Long): List<Tool>

    /**
     * Select tool detail
     *
     * @param username Username
     * @param toolId Tool ID
     * @param ver Tool version
     * @param operator Operator username
     * @return List of tool object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Platform
     * @see Tool
     */
    fun selectDetail(
        @Param("username") username: String,
        @Param("toolId") toolId: String,
        @Param("ver") ver: String,
        @Param("platform") platform: Platform,
        @Param("operator") operator: String?
    ): Tool?
}