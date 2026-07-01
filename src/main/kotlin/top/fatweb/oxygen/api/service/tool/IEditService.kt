package top.fatweb.oxygen.api.service.tool

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.tool.Platform
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.param.PageSortParam
import top.fatweb.oxygen.api.param.tool.*
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.*

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
     * Get tool template as list by platform
     *
     * @param platform Platform
     * @return List of ToolTemplateVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Platform
     * @see ToolTemplateVo
     */
    fun getTemplate(platform: Platform): List<ToolTemplateVo>

    /**
     * Get tool template by ID
     *
     * @param id ID
     * @return ToolTemplateWithSourceVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolTemplateWithSourceVo
     */
    fun getTemplate(id: Long): ToolTemplateWithSourceVo

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
     * Get tool base dist
     *
     * @param id Tool base ID
     * @param version Tool base version
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ToolBaseWithDistVo
     */
    fun getBaseDist(id: Long, version: Long): ToolBaseWithDistVo

    /**
     * Get tool base latest version
     *
     * @param id Tool base ID
     * @return Version
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    fun getBaseLatestVersion(id: Long): Long

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
     * Get tool source original object
     *
     * @param username Username
     * @param toolId Tool ID
     * @param ver Version
     * @param platform Platform
     * @return ToolWithSourceVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     * @see Platform
     * @see Tool
     */
    fun originalSource(username: String, toolId: String, ver: String, platform: Platform): Tool

    /**
     * Get tool source
     *
     * @param username Username
     * @param toolId Tool ID
     * @param ver Version
     * @param platform Platform
     * @return ToolWithSourceVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see Platform
     * @see ToolWithSourceVo
     */
    fun source(username: String, toolId: String, ver: String, platform: Platform): ToolWithSourceVo

    /**
     * Get tool dist
     *
     * @param username Username
     * @param toolId Tool ID
     * @param ver Version
     * @param platform Platform
     * @return ToolWithDistVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see Platform
     * @see ToolWithDistVo
     */
    fun dist(username: String, toolId: String, ver: String, platform: Platform): ToolWithDistVo

    /**
     * Get personal tools
     *
     * @param pageSortParam Page sort parameters
     * @return PageVo<ToolVo> object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see PageSortParam
     * @see PageVo
     * @see ToolVo
     */
    fun getPage(pageSortParam: PageSortParam): PageVo<ToolVo>

    /**
     * Create tool
     *
     * @param toolCreateParam Create tool parameters
     * @return ToolWithSourceVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolCreateParam
     * @see ToolWithSourceVo
     */
    fun create(toolCreateParam: ToolCreateParam): ToolWithSourceVo

    /**
     * Update tool
     *
     * @param toolUpdateParam Update tool parameters
     * @return ToolWithSourceVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolUpdateParam
     */
    fun update(toolUpdateParam: ToolUpdateParam)

    /**
     * Update tool source - add file/directory
     *
     * @param id Tool ID
     * @param toolCommonUpdateSourceAddParam Update source - add file/directory parameters
     * @return New node ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     * @see ToolCommonUpdateSourceAddParam
     */
    fun updateSourceAdd(id: Long, toolCommonUpdateSourceAddParam: ToolCommonUpdateSourceAddParam): String

    /**
     * Update tool source - rename file/directory
     *
     * @param id Tool ID
     * @param nodeId Source node ID
     * @param fileName New file name
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    fun updateSourceRename(id: Long, nodeId: Long, fileName: String)

    /**
     * Update tool source - move file/directory
     *
     * @param id Tool ID
     * @param nodeId Source node ID
     * @param newParentId New parent node ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    fun updateSourceMove(id: Long, nodeId: Long, newParentId: Long)

    /**
     * Update tool source - update content
     *
     * @param id Tool ID
     * @param nodeId Source node ID
     * @param content New content
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    fun updateSourceContent(id: Long, nodeId: Long, content: String)

    /**
     * Update tool source - remove file/directory
     *
     * @param id Tool ID
     * @param nodeId Source node ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    fun updateSourceRemove(id: Long, nodeId: Long)

    /**
     * Upgrade tool
     *
     * @param toolUpgradeParam Upgrade tool parameters
     * @return ToolWithSourceVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolUpgradeParam
     * @see ToolWithSourceVo
     */
    fun upgrade(toolUpgradeParam: ToolUpgradeParam): ToolWithSourceVo

    /**
     * Upgrade tool base version
     *
     * @param toolOrTemplateUpgradeBaseParam Upgrade tool base version parameters
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ToolOrTemplateUpgradeBaseParam
     */
    fun upgradeBase(toolOrTemplateUpgradeBaseParam: ToolOrTemplateUpgradeBaseParam)

    /**
     * Submit tool review
     *
     * @param id Tool ID
     * @return Result
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun submit(id: Long): Boolean

    /**
     * Cancel tool review
     *
     * @param id Tool ID
     * @return Result
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun cancel(id: Long): Boolean

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
