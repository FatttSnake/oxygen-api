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
     * Update tool base source
     *
     * @param toolBaseUpdateSourceParam Update tool base source parameters
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ToolBaseUpdateSourceParam
     */
    fun updateSource(toolBaseUpdateSourceParam: ToolBaseUpdateSourceParam)

    /**
     * Update tool base dist
     *
     * @param toolBaseUpdateDistParam Update tool base dist parameters
     * @return Version
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ToolBaseUpdateDistParam
     */
    fun updateDist(toolBaseUpdateDistParam: ToolBaseUpdateDistParam): Long

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
