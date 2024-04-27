package top.fatweb.oxygen.api.service.tool

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.param.PageSortParam
import top.fatweb.oxygen.api.param.tool.ToolFavoriteAddParam
import top.fatweb.oxygen.api.param.tool.ToolFavoriteRemoveParam
import top.fatweb.oxygen.api.param.tool.ToolStoreGetParam
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolVo

/**
 * Tool store service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IService
 * @see Tool
 */
interface IStoreService : IService<Tool> {
    /**
     * Get tool in page
     *
     * @param toolStoreGetParam Get tool parameters in tool store
     * @return PageVo<ToolVo> object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolStoreGetParam
     * @see PageVo
     * @see ToolVo
     */
    fun getPage(toolStoreGetParam: ToolStoreGetParam): PageVo<ToolVo>

    /**
     * Get tool by username in page
     *
     * @param pageSortParam Page sort parameters
     * @param username Username
     * @return PageVo<ToolVo< object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see PageSortParam
     * @see PageVo
     * @see ToolVo
     */
    fun getPage(pageSortParam: PageSortParam, username: String): PageVo<ToolVo>

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