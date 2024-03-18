package top.fatweb.oxygen.api.converter.tool

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.oxygen.api.entity.tool.ToolTemplate
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseVo
import top.fatweb.oxygen.api.vo.tool.ToolDataVo
import top.fatweb.oxygen.api.vo.tool.ToolTemplateVo

/**
 * Tool template converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object ToolTemplateConverter {
    /**
     * Convert ToolTemplate object into ToolTemplateVo object
     *
     * @param toolTemplate ToolTemplate object
     * @return ToolTemplateVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolTemplate
     * @see ToolTemplateVo
     */
    fun toolTemplateToToolTemplateVo(toolTemplate: ToolTemplate) = ToolTemplateVo(
        id = toolTemplate.id,
        name = toolTemplate.name,
        base = toolTemplate.base?.let(ToolBaseConverter::toolBaseToToolBaseVo),
        source = toolTemplate.source?.let(ToolDataConverter::toolDataToToolDataVo),
        platform = toolTemplate.platform,
        entryPoint = toolTemplate.entryPoint,
        enable = toolTemplate.enable == 1,
        createTime = toolTemplate.createTime,
        updateTime = toolTemplate.updateTime
    )

    /**
     * Convert IPage<ToolTemplate> object into PageVo<ToolTemplateVo> object
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun toolTemplatePageToToolTemplatePageVo(toolTemplatePage: IPage<ToolTemplate>) = PageVo(
        total = toolTemplatePage.total,
        pages = toolTemplatePage.pages,
        size = toolTemplatePage.size,
        current = toolTemplatePage.current,
        records = toolTemplatePage.records.map(::toolTemplateToToolTemplateVo)
    )

    /**
     * Convert ToolTemplate object into ToolTemplateVo object by list
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun toolTemplateToToolTemplateVoByList(toolTemplate: ToolTemplate) = ToolTemplateVo(
        id = toolTemplate.id,
        name = toolTemplate.name,
        base = ToolBaseVo(
            id = toolTemplate.baseId,
            name = null,
            source = null,
            dist = null,
            platform = toolTemplate.base?.platform,
            compiled = null,
            createTime = null,
            updateTime = null
        ),
        source = ToolDataVo(id = toolTemplate.sourceId, data = null, createTime = null, updateTime = null),
        platform = toolTemplate.platform,
        entryPoint = toolTemplate.entryPoint,
        enable = toolTemplate.enable == 1,
        createTime = toolTemplate.createTime,
        updateTime = toolTemplate.updateTime
    )

    /**
     * Convert ToolTemplate object into ToolTemplateVo object with base dist
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun toolTemplateToToolTemplateVoWithBaseDist(toolTemplate: ToolTemplate) = ToolTemplateVo(
        id = toolTemplate.id,
        name = toolTemplate.name,
        base = ToolBaseVo(
            id = toolTemplate.baseId,
            name = toolTemplate.base?.name,
            source = null,
            dist = ToolDataVo(id = null, data = toolTemplate.base?.distData, createTime = null, updateTime = null),
            platform = toolTemplate.base?.platform,
            compiled = null,
            createTime = null,
            updateTime = null
        ),
        source = toolTemplate.source?.let(ToolDataConverter::toolDataToToolDataVo),
        platform = toolTemplate.platform,
        entryPoint = toolTemplate.entryPoint,
        enable = toolTemplate.enable == 1,
        createTime = toolTemplate.createTime,
        updateTime = toolTemplate.updateTime
    )
}