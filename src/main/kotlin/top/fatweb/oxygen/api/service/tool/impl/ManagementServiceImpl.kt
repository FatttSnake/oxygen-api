package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.converter.tool.toVo
import top.fatweb.oxygen.api.converter.tool.toVoWithSource
import top.fatweb.oxygen.api.entity.tool.RToolCategory
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.exception.ToolHasNotBeenDelisted
import top.fatweb.oxygen.api.exception.ToolHasNotBeenPublishedException
import top.fatweb.oxygen.api.exception.ToolNotUnderReviewException
import top.fatweb.oxygen.api.mapper.tool.ManagementMapper
import top.fatweb.oxygen.api.param.tool.ToolManagementGetParam
import top.fatweb.oxygen.api.service.system.IStorageBlobService
import top.fatweb.oxygen.api.service.tool.IManagementService
import top.fatweb.oxygen.api.service.tool.IRToolCategoryService
import top.fatweb.oxygen.api.service.tool.IToolDistService
import top.fatweb.oxygen.api.util.existsOrThrowException
import top.fatweb.oxygen.api.util.queryOrThrowException
import top.fatweb.oxygen.api.util.setPageSort
import top.fatweb.oxygen.api.util.updateOrThrowException
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolVo
import top.fatweb.oxygen.api.vo.tool.ToolWithSourceVo
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * Tool management service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IStorageBlobService
 * @see IToolDistService
 * @see IRToolCategoryService
 * @see ServiceImpl
 * @see ManagementMapper
 * @see Tool
 * @see IManagementService
 */
@Service
class ManagementServiceImpl(
    private val storageBlobService: IStorageBlobService,
    private val toolDistService: IToolDistService,
    private val rToolCategoryService: IRToolCategoryService
) : ServiceImpl<ManagementMapper, Tool>(), IManagementService {
    override fun getOne(id: Long): ToolWithSourceVo =
        queryOrThrowException {
            baseMapper.selectOne(id)
        }.apply {
            sources?.forEach { source ->
                source.latestFileVersion?.apply {
                    fileContent = storageBlobService.loadFile(fileHash!!)?.toString(Charsets.UTF_8)
                }
            }
        }.let(Tool::toVoWithSource)

    override fun getPage(toolManagementGetParam: ToolManagementGetParam?): PageVo<ToolVo> {
        val toolIdsPage = Page<Long>(toolManagementGetParam?.currentPage ?: 1, toolManagementGetParam?.pageSize ?: 20)

        setPageSort(toolManagementGetParam, toolIdsPage)

        val toolIdsIPage =
            baseMapper.selectPage(
                toolIdsPage,
                toolManagementGetParam?.review?.split(","),
                toolManagementGetParam?.platform?.split(","),
                toolManagementGetParam?.searchType ?: "ALL",
                toolManagementGetParam?.searchValue,
                toolManagementGetParam?.searchRegex ?: false
            )
        val toolPage = Page<Tool>(toolIdsIPage.current, toolIdsIPage.size, toolIdsIPage.total)
        if (toolIdsIPage.total > 0) {
            toolPage.records = baseMapper.selectListByIds(toolIdsIPage.records)
        }

        return toolPage.toVo()
    }

    @Transactional
    override fun pass(id: Long, dist: String) {
        val tool = queryOrThrowException { this.getById(id) }
        if (tool.review !== Tool.ReviewType.PROCESSING) {
            throw ToolNotUnderReviewException()
        }

        toolDistService.updateContent(tool.distId!!, dist)
        updateOrThrowException {
            this.update(
                KtUpdateWrapper(Tool())
                    .eq(Tool::id, id)
                    .set(Tool::review, Tool.ReviewType.PASS)
                    .set(Tool::publish, LocalDateTime.now(ZoneOffset.UTC).toInstant(ZoneOffset.UTC).toEpochMilli())
            )
        }
    }

    @Transactional
    override fun reject(id: Long) {
        val tool = queryOrThrowException { this.getById(id) }
        if (tool.review !== Tool.ReviewType.PROCESSING) {
            throw ToolNotUnderReviewException()
        }

        updateOrThrowException {
            this.update(
                KtUpdateWrapper(Tool())
                    .eq(Tool::id, id)
                    .set(Tool::review, Tool.ReviewType.NONE)
            )
        }
    }

    @Transactional
    override fun delist(id: Long) {
        val tool = queryOrThrowException { this.getById(id) }
        if (tool.review !== Tool.ReviewType.PASS) {
            throw ToolHasNotBeenPublishedException()
        }

        updateOrThrowException {
            this.update(
                KtUpdateWrapper(Tool())
                    .eq(Tool::id, id)
                    .set(Tool::review, Tool.ReviewType.REJECT)
            )
        }
    }

    @Transactional
    override fun delistAllVersion(id: Long) {
        val tool = queryOrThrowException { this.getById(id) }

        updateOrThrowException {
            this.update(
                KtUpdateWrapper(Tool())
                    .eq(Tool::toolId, tool.toolId!!)
                    .eq(Tool::authorId, tool.authorId!!)
                    .eq(Tool::review, Tool.ReviewType.PASS)
                    .set(Tool::review, Tool.ReviewType.REJECT)
            )
        }
    }

    @Transactional
    override fun relist(id: Long) {
        val tool = queryOrThrowException { this.getById(id) }
        if (tool.review !== Tool.ReviewType.REJECT) {
            throw ToolHasNotBeenDelisted()
        }

        updateOrThrowException {
            this.update(
                KtUpdateWrapper(Tool())
                    .eq(Tool::id, id)
                    .set(Tool::review, Tool.ReviewType.PASS)
            )
        }
    }

    @Transactional
    override fun delete(id: Long): Boolean {
        existsOrThrowException {
            baseMapper.exists(
                KtQueryWrapper(Tool())
                    .eq(Tool::id, id)
            )
        }
        rToolCategoryService.remove(
            KtQueryWrapper(RToolCategory())
                .eq(RToolCategory::toolId, id)
        )

        // TODO: keep source & dist, add manual cleanup method later

        return this.removeById(id)
    }
}
