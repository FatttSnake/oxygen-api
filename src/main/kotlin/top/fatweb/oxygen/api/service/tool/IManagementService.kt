package top.fatweb.oxygen.api.service.tool

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.param.tool.ToolManagementGetParam
import top.fatweb.oxygen.api.param.tool.ToolManagementPassParam
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolVo
import top.fatweb.oxygen.api.vo.tool.ToolWithSourceVo

/**
 * Tool management service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IService
 * @see Tool
 */
interface IManagementService : IService<Tool> {
    /**
     * Get tool by ID
     *
     * @param id Tool ID
     * @return ToolWithSourceVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolWithSourceVo
     */
    fun getOne(id: Long): ToolWithSourceVo

    /**
     * Get tool in page
     *
     * @param toolManagementGetParam Get tool parameters in tool management
     * @return PageVo<ToolVo> object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolManagementGetParam
     * @see PageVo
     * @see ToolVo
     */
    fun getPage(toolManagementGetParam: ToolManagementGetParam?): PageVo<ToolVo>

    /**
     * Pass tool review
     *
     * @param id Tool ID
     * @param toolManagementPassParam Pass tool review parameters in tool management
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolManagementPassParam
     */
    fun pass(id: Long, toolManagementPassParam: ToolManagementPassParam)

    /**
     * Reject tool review
     *
     * @param id Tool ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun reject(id: Long)

    /**
     * Delist tool
     *
     * @param id Tool ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun delist(id: Long)

    /**
     * Delist tool all versions
     *
     * @param id Tool ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    fun delistAllVersion(id: Long)

    /**
     * Relist tool
     *
     * @param id Tool ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    fun relist(id: Long)

    /**
     * Delete tool
     *
     * @param id Tool ID
     * @return Result
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun delete(id: Long): Boolean
}
