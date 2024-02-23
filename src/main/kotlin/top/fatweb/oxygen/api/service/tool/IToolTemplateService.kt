package top.fatweb.oxygen.api.service.tool

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.tool.ToolTemplate
import top.fatweb.oxygen.api.param.tool.ToolTemplateAddParam
import top.fatweb.oxygen.api.param.tool.ToolTemplateUpdateParam
import top.fatweb.oxygen.api.vo.tool.ToolTemplateVo

/**
 * Tool template service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IService
 * @see ToolTemplate
 */
interface IToolTemplateService : IService<ToolTemplate> {
    /**
     * Get tool template by ID
     *
     * @param id ID
     * @return ToolTemplateVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolTemplateVo
     */
    fun getOne(id: Long): ToolTemplateVo

    /**
     * Get tool template in list
     *
     * @return List of ToolTemplateVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolTemplateVo
     */
    fun get(): List<ToolTemplateVo>

    /**
     * Add tool template
     *
     * @param toolTemplateAddParam Add tool template parameters
     * @return ToolTemplateVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolTemplateAddParam
     * @see ToolTemplateVo
     */
    fun add(toolTemplateAddParam: ToolTemplateAddParam): ToolTemplateVo

    /**
     * Update tool template
     *
     * @param toolTemplateUpdateParam Update tool template parameters
     * @return ToolTemplateVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolTemplateUpdateParam
     * @see ToolTemplateVo
     */
    fun update(toolTemplateUpdateParam: ToolTemplateUpdateParam): ToolTemplateVo

    /**
     * Delete tool template
     *
     * @param id ID
     * @return Result
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun delete(id: Long): Boolean
}