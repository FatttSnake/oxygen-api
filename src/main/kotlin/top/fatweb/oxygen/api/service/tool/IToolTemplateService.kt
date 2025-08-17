package top.fatweb.oxygen.api.service.tool

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.tool.ToolTemplate
import top.fatweb.oxygen.api.param.tool.ToolTemplateAddParam
import top.fatweb.oxygen.api.param.tool.ToolTemplateGetParam
import top.fatweb.oxygen.api.param.tool.ToolTemplateUpdateParam
import top.fatweb.oxygen.api.param.tool.ToolTemplateUpdateSourceParam
import top.fatweb.oxygen.api.param.tool.ToolOrTemplateUpgradeBaseParam
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolTemplateVo
import top.fatweb.oxygen.api.vo.tool.ToolTemplateWithSourceVo

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
     * @return ToolTemplateWithSourceVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolTemplateWithSourceVo
     */
    fun getOne(id: Long): ToolTemplateWithSourceVo

    /**
     * Get tool template in page
     *
     * @return Page of ToolTemplateVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolTemplateGetParam
     * @see PageVo
     * @see ToolTemplateVo
     */
    fun get(toolTemplateGetParam: ToolTemplateGetParam?): PageVo<ToolTemplateVo>

    /**
     * Add tool template
     *
     * @param toolTemplateAddParam Add tool template parameters
     * @return ToolTemplateWithSourceVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolTemplateAddParam
     * @see ToolTemplateWithSourceVo
     */
    fun add(toolTemplateAddParam: ToolTemplateAddParam): ToolTemplateWithSourceVo

    /**
     * Update tool template
     *
     * @param toolTemplateUpdateParam Update tool template parameters
     * @return ToolTemplateVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolTemplateUpdateParam
     */
    fun update(toolTemplateUpdateParam: ToolTemplateUpdateParam)

    /**
     * Update tool template source
     *
     * @param toolTemplateUpdateSourceParam Update tool template source parameters
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ToolTemplateUpdateSourceParam
     */
    fun updateSource(toolTemplateUpdateSourceParam: ToolTemplateUpdateSourceParam)

    /**
     * Upgrade tool template base version
     *
     * @param toolOrTemplateUpgradeBaseParam Upgrade tool template base version parameters
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ToolOrTemplateUpgradeBaseParam
     */
    fun upgradeBase(toolOrTemplateUpgradeBaseParam: ToolOrTemplateUpgradeBaseParam)

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
