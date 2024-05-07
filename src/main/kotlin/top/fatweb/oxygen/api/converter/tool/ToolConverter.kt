package top.fatweb.oxygen.api.converter.tool

import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import top.fatweb.oxygen.api.converter.permission.UserConverter
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.vo.PageVo
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
        icon = tool.icon,
        platform = tool.platform,
        description = tool.description,
        base = tool.base?.let(ToolBaseConverter::toolBaseToToolBaseVo),
        author = tool.author?.let(UserConverter::userToUserWithInfoVo),
        ver = tool.ver,
        keywords = tool.keywords,
        categories = tool.categories?.map(ToolCategoryConverter::toolCategoryToToolCategoryVo),
        source = tool.source?.let(ToolDataConverter::toolDataToToolDataVo),
        dist = tool.dist?.let(ToolDataConverter::toolDataToToolDataVo),
        entryPoint = tool.entryPoint,
        publish = tool.publish,
        review = tool.review,
        createTime = tool.createTime,
        updateTime = tool.updateTime,
        favorite = tool.favorite?.let { it == 1}
    )

    /**
     * Convert Page<Tool> object into PageVo<ToolVo> object
     *
     * @param toolPage Page<Tool> object
     * @return PageVo<ToolVo> object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Page
     * @see Tool
     * @see PageVo
     * @see ToolVo
     */
    fun toolPageToToolPageVo(toolPage: Page<Tool>): PageVo<ToolVo> = PageVo(
        total = toolPage.total,
        pages = toolPage.pages,
        size = toolPage.size,
        current = toolPage.current,
        records = toolPage.records.map(::toolToToolVo)
    )
}