package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.converter.tool.ToolConverter
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.entity.tool.ToolFavorite
import top.fatweb.oxygen.api.exception.NoRecordFoundException
import top.fatweb.oxygen.api.exception.RecordAlreadyExists
import top.fatweb.oxygen.api.mapper.tool.StoreMapper
import top.fatweb.oxygen.api.param.PageSortParam
import top.fatweb.oxygen.api.param.tool.ToolFavoriteAddParam
import top.fatweb.oxygen.api.param.tool.ToolFavoriteRemoveParam
import top.fatweb.oxygen.api.param.tool.ToolStoreGetParam
import top.fatweb.oxygen.api.service.tool.IStoreService
import top.fatweb.oxygen.api.service.tool.IToolFavoriteService
import top.fatweb.oxygen.api.util.WebUtil
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
class StoreServiceImpl(
    private val toolFavoriteService: IToolFavoriteService
) : ServiceImpl<StoreMapper, Tool>(), IStoreService {
    override fun getPage(toolStoreGetParam: ToolStoreGetParam): PageVo<ToolVo> {
        val toolIdsPage = Page<Long>(toolStoreGetParam.currentPage, 20)
        toolIdsPage.setOptimizeCountSql(false)

        val toolIdsIPage =
            baseMapper.selectAuthorToolIdPage(toolIdsPage, toolStoreGetParam.searchValue, toolStoreGetParam.platform)
        val toolPage = Page<Tool>(toolIdsIPage.current, toolIdsIPage.size, toolIdsIPage.total)
        if (toolIdsIPage.total > 0) {
            toolPage.setRecords(baseMapper.selectListByAuthorToolIds(toolIdsIPage.records, WebUtil.getLoginUserId()))
        }

        return ToolConverter.toolPageToToolPageVo(toolPage)
    }

    override fun getPage(pageSortParam: PageSortParam, username: String): PageVo<ToolVo> {
        val toolIdsPage = Page<Long>(pageSortParam.currentPage, 20)
        toolIdsPage.setOptimizeCountSql(false)

        val toolIdsIPage = baseMapper.selectAuthorToolIdPageByUsername(toolIdsPage, username)
        val toolPage = Page<Tool>(toolIdsIPage.current, toolIdsIPage.size, toolIdsIPage.total)
        if (toolIdsIPage.total > 0) {
            toolPage.setRecords(baseMapper.selectListByAuthorToolIds(toolIdsIPage.records, WebUtil.getLoginUserId()))
        }

        return ToolConverter.toolPageToToolPageVo(toolPage)
    }

    @Transactional
    override fun addFavorite(toolFavoriteAddParam: ToolFavoriteAddParam) {
        if (toolFavoriteAddParam.authorId == WebUtil.getLoginUserId()) {
            throw NoRecordFoundException()
        }

        if (toolFavoriteService.exists(
                KtQueryWrapper(ToolFavorite())
                    .eq(ToolFavorite::userId, WebUtil.getLoginUserId())
                    .eq(ToolFavorite::authorId, toolFavoriteAddParam.authorId)
                    .eq(ToolFavorite::toolId, toolFavoriteAddParam.toolId)
            )
        ) {
            throw RecordAlreadyExists()
        }

        if (baseMapper.countPublishedToolByAuthorAndToolId(
                toolFavoriteAddParam.authorId!!,
                toolFavoriteAddParam.toolId!!
            ) <= 0
        ) {
            throw NoRecordFoundException()
        }

        toolFavoriteService.save(
            ToolFavorite().apply {
                userId = WebUtil.getLoginUserId()
                authorId = toolFavoriteAddParam.authorId
                toolId = toolFavoriteAddParam.toolId
            }
        )
    }

    @Transactional
    override fun removeFavorite(toolFavoriteRemoveParam: ToolFavoriteRemoveParam) {
        if (!toolFavoriteService.remove(
                KtQueryWrapper(ToolFavorite())
                    .eq(ToolFavorite::userId, WebUtil.getLoginUserId())
                    .eq(ToolFavorite::authorId, toolFavoriteRemoveParam.authorId)
                    .eq(ToolFavorite::toolId, toolFavoriteRemoveParam.toolId)
            )
        ) {
            throw NoRecordFoundException()
        }
    }

    override fun getFavorite(pageSortParam: PageSortParam): PageVo<ToolVo> {
        val toolFavoritePage = Page<ToolFavorite>(pageSortParam.currentPage, 20)

        val toolFavoriteIPage = toolFavoriteService.page(
            toolFavoritePage,
            KtQueryWrapper(ToolFavorite()).eq(ToolFavorite::userId, WebUtil.getLoginUserId())
        )

        val toolPage = Page<Tool>(toolFavoriteIPage.current, toolFavoriteIPage.size, toolFavoriteIPage.total)
        if (toolFavoriteIPage.total > 0) {
            toolPage.setRecords(
                baseMapper.selectListByAuthorToolIds(
                    toolFavoriteIPage.records.map { "${it.authorId}:${it.toolId}" },
                    WebUtil.getLoginUserId()
                )
            )
        }

        return ToolConverter.toolPageToToolPageVo(toolPage)
    }
}