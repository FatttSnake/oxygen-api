package top.fatweb.oxygen.api.service.tool

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.param.tool.ToolCreateParam
import top.fatweb.oxygen.api.param.tool.ToolUpdateParam
import top.fatweb.oxygen.api.param.tool.ToolUpgradeParam
import top.fatweb.oxygen.api.vo.tool.ToolCategoryVo
import top.fatweb.oxygen.api.vo.tool.ToolTemplateVo
import top.fatweb.oxygen.api.vo.tool.ToolVo

/**
 * Tool edit service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IService
 * @see Tool
 */
interface IEditService : IService<Tool> {
    /**
     * Get tool template as list
     *
     * @return List of ToolTemplateVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolTemplateVo
     */
    fun getTemplate(): List<ToolTemplateVo>

    /**
     * Get tool template by ID
     *
     * @param id ID
     * @return ToolTemplateVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolTemplateVo
     */
    fun getTemplate(id: Long): ToolTemplateVo

    /**
     * Get tool category as list
     *
     * @return List of ToolCategoryVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolCategoryVo
     */
    fun getCategory(): List<ToolCategoryVo>

    /**
     * Get tool by ID
     *
     * @param
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun getOne(id: Long): ToolVo

    /**
     * Create tool
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun create(toolCreateParam: ToolCreateParam): ToolVo

    /**
     * Upgrade tool
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolUpgradeParam
     * @see ToolVo
     */
    fun upgrade(toolUpgradeParam: ToolUpgradeParam): ToolVo

    /**
     * Update tool
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun update(toolUpdateParam: ToolUpdateParam): ToolVo

    /**
     * Get personal tools
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun get(): List<ToolVo>

    /**
     * Get tool detail
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun detail(username: String, toolId: String, ver: String): ToolVo

    /**
     * Delete tool
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun delete(id: Long): Boolean
}