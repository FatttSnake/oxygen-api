package top.fatweb.oxygen.api.service.tool

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.tool.ToolBase
import top.fatweb.oxygen.api.param.tool.*
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseWithDistVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseWithSourceVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseWithVersionsVo

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
     * Get tool base by ID and version
     *
     * @param id ID
     * @param version Version
     * @return ToolBaseWithSourceVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBaseWithSourceVo
     */
    fun getOne(id: Long, version: Long): ToolBaseWithSourceVo

    /**
     * Get tool base dist
     *
     * @param id Tool base ID
     * @param version Version
     * @return ToolBaseWithDistVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ToolBaseWithDistVo
     */
    fun getDist(id: Long, version: Long): ToolBaseWithDistVo

    /**
     * Get tool base latest version
     *
     * @param id Tool base ID
     * @return Version
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    fun getLatestVersion(id: Long): Long

    /**
     * Get tool base in page
     *
     * @param toolBaseGetParam Get tool base parameters
     * @return PageVo<ToolBaseWithVersionsVo> object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBaseGetParam
     * @see PageVo
     * @see ToolBaseWithVersionsVo
     */
    fun get(toolBaseGetParam: ToolBaseGetParam?): PageVo<ToolBaseWithVersionsVo>

    /**
     * Get all tool base in list
     *
     * @return List of ToolBaseVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBaseVo
     */
    fun getList(): List<ToolBaseVo>

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
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBaseUpdateParam
     */
    fun update(toolBaseUpdateParam: ToolBaseUpdateParam)

    /**
     * Update tool base source - add file/directory
     *
     * @param id Tool base ID
     * @param toolCommonUpdateSourceAddParam Update source - add file/directory parameters
     * @return New node ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     * @see ToolCommonUpdateSourceAddParam
     */
    fun updateSourceAdd(id: Long, toolCommonUpdateSourceAddParam: ToolCommonUpdateSourceAddParam): String

    /**
     * Update tool base source - rename file/directory
     *
     * @param id Tool base ID
     * @param nodeId Source node ID
     * @param fileName New file name
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    fun updateSourceRename(id: Long, nodeId: Long, fileName: String)

    /**
     * Update tool base source - move file/directory
     *
     * @param id Tool base ID
     * @param nodeId Source node ID
     * @param newParentId New parent node ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    fun updateSourceMove(id: Long, nodeId: Long, newParentId: Long)

    /**
     * Update tool base source - update content
     *
     * @param id Tool base ID
     * @param nodeId Source node ID
     * @param content New content
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    fun updateSourceContent(id: Long, nodeId: Long, content: String)

    /**
     * Update tool base source - remove file/directory
     *
     * @param id Tool base ID
     * @param nodeId Source node ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    fun updateSourceRemove(id: Long, nodeId: Long)

    /**
     * Update tool base dist
     *
     * @param id Tool base ID
     * @param dist Dist
     * @return Version
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    fun updateDist(id: Long, dist: String): Long

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
