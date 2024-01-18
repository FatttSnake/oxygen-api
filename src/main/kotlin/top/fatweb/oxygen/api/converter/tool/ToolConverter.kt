package top.fatweb.oxygen.api.converter.tool

import top.fatweb.oxygen.api.converter.permission.UserInfoConverter
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.vo.tool.ToolVo

object ToolConverter {
    fun toolToToolVo(tool: Tool) = ToolVo(
        id = tool.id,
        name = tool.name,
        toolId = tool.toolId,
        description = tool.description,
        baseId = tool.baseId,
        author = tool.author?.let(UserInfoConverter::userInfoToUserInfoVo),
        ver = tool.ver,
        privately = tool.privately == 1,
        keywords = tool.keywords,
        categories = tool.categories?.map(ToolCategoryConverter::toolCategoryToToolCategoryVo),
        source = tool.source?.let(ToolDataConverter::toolDataToToolDataVo),
        dist = tool.dist?.let(ToolDataConverter::toolDataToToolDataVo),
        publish = tool.publish == 1,
        review = tool.review,
        createTime = tool.createTime,
        updateTime = tool.updateTime
    )
}