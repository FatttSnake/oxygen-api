package top.fatweb.oxygen.api.converter.tool

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.oxygen.api.converter.permission.toVoWithInfo
import top.fatweb.oxygen.api.entity.permission.User
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.entity.tool.ToolBase
import top.fatweb.oxygen.api.entity.tool.ToolCategory
import top.fatweb.oxygen.api.entity.tool.ToolData
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolVo

/**
 * Convert to ToolVo object
 *
 * @return ToolVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see Tool
 * @see ToolVo
 */
fun Tool.toVo() = ToolVo(
    id = this.id,
    name = this.name,
    toolId = this.toolId,
    icon = this.icon,
    platform = this.platform,
    description = this.description,
    base = this.base?.let(ToolBase::toVo),
    author = this.author?.let(User::toVoWithInfo),
    ver = this.ver,
    keywords = this.keywords,
    categories = this.categories?.map(ToolCategory::toVo),
    source = this.source?.let(ToolData::toVo),
    dist = this.dist?.let(ToolData::toVo),
    entryPoint = this.entryPoint,
    publish = this.publish,
    review = this.review,
    createTime = this.createTime,
    updateTime = this.updateTime,
    favorite = this.favorite?.let { it == 1 }
)

/**
 * Convert to PageVo<ToolVo> object
 *
 * @return PageVo<ToolVo> object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see IPage
 * @see Tool
 * @see PageVo
 */
fun IPage<Tool>.toVo() = PageVo(
    total = this.total,
    pages = this.pages,
    size = this.size,
    current = this.current,
    records = this.records.map(Tool::toVo)
)
