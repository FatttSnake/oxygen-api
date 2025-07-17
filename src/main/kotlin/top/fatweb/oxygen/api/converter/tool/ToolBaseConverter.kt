package top.fatweb.oxygen.api.converter.tool

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.oxygen.api.entity.tool.ToolBase
import top.fatweb.oxygen.api.entity.tool.ToolData
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseVo
import top.fatweb.oxygen.api.vo.tool.ToolDataVo

/**
 * Convert to ToolBaseVo object
 *
 * @return ToolBaseVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see ToolBase
 * @see ToolBaseVo
 */
fun ToolBase.toVo() = ToolBaseVo(
    id = this.id,
    name = this.name,
    source = this.source?.let(ToolData::toVo),
    dist = this.dist?.let(ToolData::toVo),
    platform = this.platform,
    compiled = this.compiled?.let { it == 1 },
    createTime = this.createTime,
    updateTime = this.updateTime
)

/**
 * Convert to ToolBaseVo object by get page
 *
 * @return ToolBaseVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see ToolBase
 * @see ToolBaseVo
 */
fun ToolBase.toVoByGetList() = ToolBaseVo(
    id = this.id,
    name = this.name,
    source = ToolDataVo(id = this.sourceId, data = null, createTime = null, updateTime = null),
    dist = ToolDataVo(id = this.distId, data = null, createTime = null, updateTime = null),
    platform = this.platform,
    compiled = this.compiled?.let { it == 1 },
    createTime = this.createTime,
    updateTime = this.updateTime
)

/**
 * Convert to PageVo<ToolBaseVo> object
 *
 * @return PageVo<ToolBaseVo> object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see IPage
 * @see ToolBase
 * @see PageVo
 * @see ToolBaseVo
 */
fun IPage<ToolBase>.toVo() = PageVo(
    total = this.total,
    pages = this.pages,
    size = this.size,
    current = this.current,
    records = this.records.map(ToolBase::toVoByGetList)
)
