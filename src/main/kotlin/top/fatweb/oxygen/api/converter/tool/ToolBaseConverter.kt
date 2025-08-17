package top.fatweb.oxygen.api.converter.tool

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.oxygen.api.entity.tool.ToolBase
import top.fatweb.oxygen.api.entity.tool.ToolData
import top.fatweb.oxygen.api.param.tool.ToolBaseUpdateParam
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseWithDistVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseWithSourceVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseWithVersionsVo

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
    platform = this.platform,
    version = this.baseVersion,
    createTime = this.createTime,
    updateTime = this.updateTime
)

/**
 * Convert to ToolBaseWithSourceVo object
 *
 * @return ToolBaseWithSourceVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see ToolBase
 * @see ToolBaseWithSourceVo
 */
fun ToolBase.toVoWithSource() = ToolBaseWithSourceVo(
    id = this.id,
    name = this.name,
    source = this.source?.let(ToolData::toVo),
    platform = this.platform,
    version = this.baseVersion,
    createTime = this.createTime,
    updateTime = this.updateTime
)

/**
 * Convert to ToolBaseWithDistVo object
 *
 * @return ToolBaseWithDistVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see ToolBase
 * @see ToolBaseWithDistVo
 */
fun ToolBase.toVoWithDist() = ToolBaseWithDistVo(
    id = this.id,
    name = this.name,
    dist = this.dist?.let(ToolData::toVo),
    platform = this.platform,
    version = this.baseVersion,
    createTime = this.createTime,
    updateTime = this.updateTime
)

/**
 * Convert to ToolBaseWithVersionsVo object
 *
 * @return ToolBaseWithVersionsVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see ToolBase
 * @see ToolBaseWithVersionsVo
 */
fun ToolBase.toVoWithVersions() = ToolBaseWithVersionsVo(
    id = this.id,
    name = this.name,
    platform = this.platform,
    createTime = this.createTime,
    updateTime = this.updateTime,
    versions = this.versions
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
    records = this.records.map(ToolBase::toVoWithVersions)
)

/**
 * Convert to ToolBase object
 *
 * @return ToolBase object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see ToolBaseUpdateParam
 * @see ToolBase
 */
fun ToolBaseUpdateParam.toEntity() = ToolBase().apply {
    id = this@toEntity.id
    name = this@toEntity.name
}
