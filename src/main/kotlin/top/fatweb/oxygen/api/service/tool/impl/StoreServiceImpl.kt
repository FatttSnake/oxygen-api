package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.converter.tool.toVo
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.entity.tool.ToolFavorite
import top.fatweb.oxygen.api.entity.tool.ToolIdentifier
import top.fatweb.oxygen.api.exception.NoRecordFoundException
import top.fatweb.oxygen.api.exception.RecordAlreadyExistsException
import top.fatweb.oxygen.api.mapper.tool.StoreMapper
import top.fatweb.oxygen.api.param.PageSortParam
import top.fatweb.oxygen.api.param.tool.ToolFavoriteAddParam
import top.fatweb.oxygen.api.param.tool.ToolFavoriteRemoveParam
import top.fatweb.oxygen.api.param.tool.ToolStoreGetParam
import top.fatweb.oxygen.api.service.tool.IStoreService
import top.fatweb.oxygen.api.service.tool.IToolFavoriteService
import top.fatweb.oxygen.api.util.getLoginUserId
import top.fatweb.oxygen.api.util.saveOrThrowException
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
        val toolIdsPage = Page<ToolIdentifier>(toolStoreGetParam.currentPage, 20)
        toolIdsPage.setOptimizeCountSql(false)

        val toolIdsIPage =
            baseMapper.selectAuthorToolIdentifierPage(
                toolIdsPage,
                toolStoreGetParam.searchValue,
                toolStoreGetParam.platform
            )
        val toolPage = Page<Tool>(toolIdsIPage.current, toolIdsIPage.size, toolIdsIPage.total)
        if (toolIdsIPage.total > 0) {
            toolPage.setRecords(
                baseMapper.selectListByAuthorToolIds(
                    toolIdsIPage.records,
                    getLoginUserId(),
                    toolStoreGetParam.platform
                )
            )
        }

        return toolPage.toVo()
    }

    override fun getPage(pageSortParam: PageSortParam, username: String): PageVo<ToolVo> {
        val toolIdsPage = Page<ToolIdentifier>(pageSortParam.currentPage, 20)
        toolIdsPage.setOptimizeCountSql(false)

        val toolIdsIPage = baseMapper.selectAuthorToolIdentifierPageByUsername(toolIdsPage, username)
        val toolPage = Page<Tool>(toolIdsIPage.current, toolIdsIPage.size, toolIdsIPage.total)
        if (toolIdsIPage.total > 0) {
            toolPage.setRecords(baseMapper.selectListByAuthorToolIds(toolIdsIPage.records, getLoginUserId()))
        }

        return toolPage.toVo()
    }

    @Transactional
    override fun addFavorite(toolFavoriteAddParam: ToolFavoriteAddParam) {
        if (toolFavoriteAddParam.authorId == getLoginUserId()) {
            throw NoRecordFoundException()
        }

        if (toolFavoriteService.exists(
                KtQueryWrapper(ToolFavorite())
                    .eq(ToolFavorite::userId, getLoginUserId())
                    .eq(ToolFavorite::authorId, toolFavoriteAddParam.authorId)
                    .eq(ToolFavorite::toolId, toolFavoriteAddParam.toolId)
            )
        ) {
            throw RecordAlreadyExistsException()
        }

        if (baseMapper.countPublishedToolByAuthorAndToolId(
                toolFavoriteAddParam.authorId!!,
                toolFavoriteAddParam.toolId!!
            ) <= 0
        ) {
            throw NoRecordFoundException()
        }

        saveOrThrowException {
            toolFavoriteService.save(
                ToolFavorite().apply {
                    userId = getLoginUserId()
                    authorId = toolFavoriteAddParam.authorId
                    toolId = toolFavoriteAddParam.toolId
                }
            )
        }
    }

    @Transactional
    override fun removeFavorite(toolFavoriteRemoveParam: ToolFavoriteRemoveParam) {
        if (!toolFavoriteService.remove(
                KtQueryWrapper(ToolFavorite())
                    .eq(ToolFavorite::userId, getLoginUserId())
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
            KtQueryWrapper(ToolFavorite()).eq(ToolFavorite::userId, getLoginUserId())
        )

        val toolPage = Page<Tool>(toolFavoriteIPage.current, toolFavoriteIPage.size, toolFavoriteIPage.total)
        if (toolFavoriteIPage.total > 0) {
            toolPage.setRecords(
                baseMapper.selectListByAuthorToolIds(
                    toolFavoriteIPage.records.map {
                        ToolIdentifier().apply {
                            authorId = it.authorId
                            toolId = it.toolId
                        }
                    },
                    getLoginUserId()
                )
            )
        }

        return toolPage.toVo()
    }
}
