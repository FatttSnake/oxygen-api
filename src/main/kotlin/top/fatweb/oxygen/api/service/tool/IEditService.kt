package top.fatweb.oxygen.api.service.tool

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.entity.tool.ToolBase
import top.fatweb.oxygen.api.param.PageSortParam
import top.fatweb.oxygen.api.param.tool.*
import top.fatweb.oxygen.api.vo.PageVo
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
     * Get tool template as list by platform
     *
     * @return List of ToolTemplateVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBase.Platform
     * @see ToolTemplateVo
     */
    fun getTemplate(platform: ToolBase.Platform): List<ToolTemplateVo>

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
     * @param id Tool ID
     * @return ToolVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun getOne(id: Long): ToolVo

    /**
     * Create tool
     *
     * @param toolCreateParam Create tool parameters
     * @return ToolVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolCreateParam
     * @see ToolVo
     */
    fun create(toolCreateParam: ToolCreateParam): ToolVo

    /**
     * Upgrade tool
     *
     * @param toolUpgradeParam Upgrade tool parameters
     * @return ToolVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolUpgradeParam
     * @see ToolVo
     */
    fun upgrade(toolUpgradeParam: ToolUpgradeParam): ToolVo

    /**
     * Update tool
     *
     * @param toolUpdateParam Update tool parameters
     * @return ToolVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolUpdateParam
     * @see ToolVo
     */
    fun update(toolUpdateParam: ToolUpdateParam): ToolVo

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
     * Get tool detail
     *
     * @param username Username
     * @param toolId Tool ID
     * @param ver Version
     * @param platform Platform
     * @return ToolVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBase.Platform
     * @see ToolVo
     */
    fun detail(username: String, toolId: String, ver: String, platform: ToolBase.Platform): ToolVo

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

    /***
     * Add favorite
     *
     * @param toolFavoriteAddParam Add favorite tool parameters
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolFavoriteAddParam
     */
    fun addFavorite(toolFavoriteAddParam: ToolFavoriteAddParam)

    /***
     * Remove favorite tool
     *
     * @param toolFavoriteRemoveParam Remove favorite tool parameters
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolFavoriteRemoveParam
     */
    fun removeFavorite(toolFavoriteRemoveParam: ToolFavoriteRemoveParam)
}