package top.fatweb.oxygen.api.service.tool

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.tool.ToolTemplate
import top.fatweb.oxygen.api.param.tool.*
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
     * Get tool template original object by ID
     *
     * @param id ID
     * @return ToolTemplate object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     * @see ToolTemplate
     */
    fun getOriginalOne(id: Long): ToolTemplate

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
     * @param toolTemplateGetParam Get tool template parameters
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
     * Update tool template source - add file/directory
     *
     * @param id Tool template ID
     * @param toolCommonUpdateSourceAddParam Update source - add file/directory parameters
     * @return New node ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     * @see ToolCommonUpdateSourceAddParam
     */
    fun updateSourceAdd(id: Long, toolCommonUpdateSourceAddParam: ToolCommonUpdateSourceAddParam): String

    /**
     * Update tool template source - rename file/directory
     *
     * @param id Tool template ID
     * @param nodeId Source node ID
     * @param fileName New file name
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    fun updateSourceRename(id: Long, nodeId: Long, fileName: String)

    /**
     * Update tool template source - move file/directory
     *
     * @param id Tool template ID
     * @param nodeId Source node ID
     * @param newParentId New parent node ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    fun updateSourceMove(id: Long, nodeId: Long, newParentId: Long)

    /**
     * Update tool template source - update content
     *
     * @param id Tool template ID
     * @param nodeId Source node ID
     * @param content New content
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    fun updateSourceContent(id: Long, nodeId: Long, content: String)

    /**
     * Update tool template source - remove file/directory
     *
     * @param id Tool template ID
     * @param nodeId Source node ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    fun updateSourceRemove(id: Long, nodeId: Long)

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
