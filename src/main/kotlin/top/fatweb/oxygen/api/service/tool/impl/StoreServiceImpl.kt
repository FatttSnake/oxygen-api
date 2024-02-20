package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.converter.tool.ToolConverter
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.mapper.tool.StoreMapper
import top.fatweb.oxygen.api.param.tool.ToolStoreGetParam
import top.fatweb.oxygen.api.service.tool.IStoreService
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolVo

/**
 * Tool store service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see StoreMapper
 * @see Tool
 * @see IStoreService
 */
@Service
class StoreServiceImpl : ServiceImpl<StoreMapper, Tool>(), IStoreService {
    override fun getPage(toolStoreGetParam: ToolStoreGetParam?): PageVo<ToolVo> {
        val toolIdsPage = Page<Long>(toolStoreGetParam?.currentPage ?: 1, 20)
        toolIdsPage.setOptimizeCountSql(false)

        val toolIdsIPage = baseMapper.selectPage(toolIdsPage, toolStoreGetParam?.searchValue)
        val toolPage = Page<Tool>(toolIdsIPage.current, toolIdsIPage.size, toolIdsIPage.total)
        if (toolIdsIPage.total > 0) {
            toolPage.setRecords(baseMapper.selectListByIds(toolIdsIPage.records))
        }

        return ToolConverter.toolPageToToolPageVo(toolPage)
    }
}