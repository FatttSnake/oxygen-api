package top.fatweb.oxygen.api.service.tool

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.param.tool.ToolManagementGetParam
import top.fatweb.oxygen.api.param.tool.ToolManagementPassParam
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolVo

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
     * @return ToolVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolVo
     */
    fun getOne(id: Long): ToolVo

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
     * @return ToolVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolManagementPassParam
     * @see ToolVo
     */
    fun pass(id: Long, toolManagementPassParam: ToolManagementPassParam): ToolVo

    /**
     * Reject tool review
     *
     * @param id Tool ID
     * @return ToolVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolVo
     */
    fun reject(id: Long): ToolVo

    /**
     * Off shelve tool
     *
     * @param id Tool ID
     * @return ToolVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolVo
     */
    fun offShelve(id: Long): ToolVo

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