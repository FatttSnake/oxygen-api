package top.fatweb.oxygen.api.converter.tool

import top.fatweb.oxygen.api.entity.tool.ToolTemplate
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
        createTime = toolTemplate.createTime,
        updateTime = toolTemplate.updateTime,
        enable = toolTemplate.enable == 1
    )
}