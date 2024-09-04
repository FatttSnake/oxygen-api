package top.fatweb.oxygen.api.converter.tool

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.oxygen.api.entity.tool.ToolBase
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseVo
import top.fatweb.oxygen.api.vo.tool.ToolDataVo

/**
 * Tool base converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object ToolBaseConverter {
    /**
     * Convert ToolBase object into ToolBaseVo object
     *
     * @param toolBase ToolBase object
     * @return ToolBaseVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBase
     * @see ToolBaseVo
     */
    fun toolBaseToToolBaseVo(toolBase: ToolBase) = ToolBaseVo(
        id = toolBase.id,
        name = toolBase.name,
        source = toolBase.source?.let(ToolDataConverter::toolDataToToolDataVo),
        dist = toolBase.dist?.let(ToolDataConverter::toolDataToToolDataVo),
        platform = toolBase.platform,
        compiled = toolBase.compiled?.let { it == 1},
        createTime = toolBase.createTime,
        updateTime = toolBase.updateTime
    )

    /**
     * Convert ToolBase object into ToolBaseVo object by get page
     *
     * @param toolBase ToolBase object
     * @return ToolBaseVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBase
     * @see ToolBaseVo
     */
    fun toolBaseToToolBaseVoByGetList(toolBase: ToolBase) = ToolBaseVo(
        id = toolBase.id,
        name = toolBase.name,
        source = ToolDataVo(id = toolBase.sourceId, data = null, createTime = null, updateTime = null),
        dist = ToolDataVo(id = toolBase.distId, data = null, createTime = null, updateTime = null),
        platform = toolBase.platform,
        compiled = toolBase.compiled?.let { it == 1},
        createTime = toolBase.createTime,
        updateTime = toolBase.updateTime
    )

    /**
     * Convert IPage<ToolBase> object into PageVo<ToolBaseVo> object
     *
     * @param toolBasePage IPage<ToolBase> object
     * @return PageVo<ToolBaseVo> object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see IPage
     * @see ToolBase
     * @see PageVo
     * @see ToolBaseVo
     */
    fun toolBasePageToToolBasePageVo(toolBasePage: IPage<ToolBase>) = PageVo(
        total = toolBasePage.total,
        pages = toolBasePage.pages,
        size = toolBasePage.size,
        current = toolBasePage.current,
        records = toolBasePage.records.map(::toolBaseToToolBaseVoByGetList)
    )
}