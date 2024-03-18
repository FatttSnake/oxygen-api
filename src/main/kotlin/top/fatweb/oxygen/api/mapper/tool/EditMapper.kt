package top.fatweb.oxygen.api.mapper.tool

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.entity.tool.ToolBase
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
     * Select tool in list by User ID
     *
     * @param userId User ID
     * @return List of tool object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Tool
     */
    fun selectPersonal(@Param("userId") userId: Long): List<Tool>

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
     * @see Tool
     */
    fun selectDetail(
        @Param("username") username: String,
        @Param("toolId") toolId: String,
        @Param("ver") ver: String,
        @Param("platform") platform: ToolBase.Platform,
        @Param("operator") operator: String?
    ): List<Tool>?
}