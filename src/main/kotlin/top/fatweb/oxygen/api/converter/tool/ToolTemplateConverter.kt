package top.fatweb.oxygen.api.converter.tool

import top.fatweb.oxygen.api.entity.tool.ToolTemplate
import top.fatweb.oxygen.api.vo.tool.ToolTemplateVo

object ToolTemplateConverter {
    fun toolTemplateToToolTemplateVo(toolTemplate: ToolTemplate) = ToolTemplateVo(
        id = toolTemplate.id,
        name = toolTemplate.name,
        ver = toolTemplate.ver,
        baseId = toolTemplate.baseId,
        source = toolTemplate.source?.let(ToolDataConverter::toolDataToToolDataVo),
        dist = toolTemplate.dist?.let(ToolDataConverter::toolDataToToolDataVo),
        createTime = toolTemplate.createTime,
        updateTime = toolTemplate.updateTime,
        enable = toolTemplate.enable == 1
    )
}