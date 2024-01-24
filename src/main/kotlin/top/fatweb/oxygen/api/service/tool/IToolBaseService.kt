package top.fatweb.oxygen.api.service.tool

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.tool.ToolBase
import top.fatweb.oxygen.api.param.tool.ToolBaseAddParam
import top.fatweb.oxygen.api.param.tool.ToolBaseUpdateParam
import top.fatweb.oxygen.api.vo.tool.ToolBaseVo

/**
 * Tool base service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IService
 * @see ToolBase
 */
interface IToolBaseService : IService<ToolBase> {
    /**
     * Get tool base by ID
     *
     * @param id ID
     * @return ToolBaseVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBaseVo
     */
    fun getOne(id: Long): ToolBaseVo

    /**
     * Get tool base in list
     *
     * @return List of ToolBaseVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBaseVo
     */
    fun get(): List<ToolBaseVo>

    /**
     * Add tool base
     *
     * @param toolBaseAddParam Add tool base parameters
     * @return ToolBaseVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBaseAddParam
     * @see ToolBaseVo
     */
    fun add(toolBaseAddParam: ToolBaseAddParam): ToolBaseVo

    /**
     * Update tool base
     *
     * @param toolBaseUpdateParam Update tool base parameters
     * @return ToolBaseVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBaseUpdateParam
     * @see ToolBaseVo
     */
    fun update(toolBaseUpdateParam: ToolBaseUpdateParam): ToolBaseVo

    /**
     * Delete tool base
     *
     * @param id ID
     * @return Result
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun delete(id: Long): Boolean
}