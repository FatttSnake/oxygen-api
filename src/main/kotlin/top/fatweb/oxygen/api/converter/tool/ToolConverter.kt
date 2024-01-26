package top.fatweb.oxygen.api.converter.tool

import top.fatweb.oxygen.api.converter.permission.UserConverter
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.vo.tool.ToolVo

/**
 * Tool converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object ToolConverter {
    /**
     * Convert Tool object into ToolVo object
     *
     * @param tool Tool object
     * @return ToolVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Tool
     * @see ToolVo
     */
    fun toolToToolVo(tool: Tool) = ToolVo(
        id = tool.id,
        name = tool.name,
        toolId = tool.toolId,
        description = tool.description,
        baseId = tool.baseId,
        author = tool.author?.let(UserConverter::userToUserWithInfoVo),
        ver = tool.ver,
        privately = tool.privately == 1,
        keywords = tool.keywords,
        categories = tool.categories?.map(ToolCategoryConverter::toolCategoryToToolCategoryVo),
        source = tool.source?.let(ToolDataConverter::toolDataToToolDataVo),
        dist = tool.dist?.let(ToolDataConverter::toolDataToToolDataVo),
        entryPoint = tool.entryPoint,
        publish = tool.publish == 1,
        review = tool.review,
        createTime = tool.createTime,
        updateTime = tool.updateTime
    )
}