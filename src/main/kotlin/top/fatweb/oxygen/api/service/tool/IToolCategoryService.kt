package top.fatweb.oxygen.api.service.tool

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.tool.ToolCategory
import top.fatweb.oxygen.api.param.tool.ToolCategoryAddParam
import top.fatweb.oxygen.api.param.tool.ToolCategoryUpdateParam
import top.fatweb.oxygen.api.vo.tool.ToolCategoryVo

/**
 * Tool category service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IService
 * @see ToolCategory
 */
interface IToolCategoryService : IService<ToolCategory> {
    /**
     * Get tool category by ID
     *
     * @param id ID
     * @return ToolCategoryVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolCategoryVo
     */
    fun getOne(id: Long): ToolCategoryVo

    /**
     * Get tool category in list
     *
     * @return List of ToolCategoryVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolCategoryVo
     */
    fun get(): List<ToolCategoryVo>

    /**
     * Add tool category
     *
     * @param toolCategoryAddParam Add tool category parameters
     * @return ToolCategoryVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolCategoryAddParam
     * @see ToolCategoryVo
     */
    fun add(toolCategoryAddParam: ToolCategoryAddParam): ToolCategoryVo

    /**
     * Update tool category
     *
     * @param toolCategoryUpdateParam Update tool category parameters
     * @return ToolCategoryVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolCategoryUpdateParam
     * @see ToolCategoryVo
     */
    fun update(toolCategoryUpdateParam: ToolCategoryUpdateParam): ToolCategoryVo

    /**
     * Delete tool category
     *
     * @param id ID
     * @return Result
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun delete(id: Long): Boolean
}