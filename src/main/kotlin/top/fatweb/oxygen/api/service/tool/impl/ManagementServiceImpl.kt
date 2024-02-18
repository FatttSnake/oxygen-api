package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.core.metadata.OrderItem
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.converter.tool.ToolConverter
import top.fatweb.oxygen.api.entity.tool.RToolCategory
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.entity.tool.ToolData
import top.fatweb.oxygen.api.exception.NoRecordFoundException
import top.fatweb.oxygen.api.exception.ToolHasNotBeenPublishedException
import top.fatweb.oxygen.api.exception.ToolNotUnderReviewException
import top.fatweb.oxygen.api.mapper.tool.ManagementMapper
import top.fatweb.oxygen.api.param.tool.ToolManagementGetParam
import top.fatweb.oxygen.api.param.tool.ToolManagementPassParam
import top.fatweb.oxygen.api.service.tool.IManagementService
import top.fatweb.oxygen.api.service.tool.IRToolCategoryService
import top.fatweb.oxygen.api.service.tool.IToolDataService
import top.fatweb.oxygen.api.util.PageUtil
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolVo
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * Tool management service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see ManagementMapper
 * @see Tool
 * @see IManagementService
 */
@Service
class ManagementServiceImpl(
    private val toolDataService: IToolDataService,
    private val rToolCategoryService: IRToolCategoryService
) : ServiceImpl<ManagementMapper, Tool>(), IManagementService {
    override fun getOne(id: Long): ToolVo =
        baseMapper.selectOne(id)
            ?.let(ToolConverter::toolToToolVo) ?: throw NoRecordFoundException()

    override fun getPage(toolManagementGetParam: ToolManagementGetParam?): PageVo<ToolVo> {
        val toolIdsPage = Page<Long>(toolManagementGetParam?.currentPage ?: 1, toolManagementGetParam?.pageSize ?: 20)

        PageUtil.setPageSort(toolManagementGetParam, toolIdsPage, OrderItem.desc("id"))

        val toolIdsIPage =
            baseMapper.selectPage(
                toolIdsPage,
                toolManagementGetParam?.review?.split(","),
                toolManagementGetParam?.searchType ?: "ALL",
                toolManagementGetParam?.searchValue,
                toolManagementGetParam?.searchRegex ?: false
            )
        val toolPage = Page<Tool>(toolIdsIPage.current, toolIdsIPage.size, toolIdsIPage.total)
        if (toolIdsIPage.total > 0) {
            toolPage.setRecords(baseMapper.selectListByIds(toolIdsIPage.records))
        }

        return ToolConverter.toolPageToToolPageVo(toolPage)
    }

    override fun pass(id: Long, toolManagementPassParam: ToolManagementPassParam): ToolVo {
        val tool = this.getById(id) ?: throw NoRecordFoundException()
        if (tool.review !== Tool.ReviewType.PROCESSING) {
            throw ToolNotUnderReviewException()
        }

        toolDataService.update(
            KtUpdateWrapper(ToolData()).eq(ToolData::id, tool.distId).set(ToolData::data, toolManagementPassParam.dist)
        )

        this.update(
            KtUpdateWrapper(Tool())
                .eq(Tool::id, id)
                .set(Tool::review, Tool.ReviewType.PASS)
                .set(Tool::publish, LocalDateTime.now(ZoneOffset.UTC).toInstant(ZoneOffset.UTC).toEpochMilli())
        )

        return this.getOne(id)
    }

    override fun reject(id: Long): ToolVo {
        val tool = this.getById(id) ?: throw NoRecordFoundException()
        if (tool.review !== Tool.ReviewType.PROCESSING) {
            throw ToolNotUnderReviewException()
        }

        this.update(
            KtUpdateWrapper(Tool())
                .eq(Tool::id, id)
                .set(Tool::review, Tool.ReviewType.REJECT)
        )

        return this.getOne(id)
    }

    override fun offShelve(id: Long): ToolVo {
        val tool = this.getById(id) ?: throw NoRecordFoundException()
        if (tool.review !== Tool.ReviewType.PASS && tool.publish == 0L) {
            throw ToolHasNotBeenPublishedException()
        }

        this.update(
            KtUpdateWrapper(Tool())
                .eq(Tool::id, id)
                .set(Tool::review, Tool.ReviewType.REJECT)
        )

        return this.getOne(id)
    }

    @Transactional
    override fun delete(id: Long): Boolean {
        val tool = this.getById(id) ?: throw NoRecordFoundException()

        toolDataService.removeBatchByIds(listOf(tool.sourceId, tool.distId))
        rToolCategoryService.remove(KtQueryWrapper(RToolCategory()).eq(RToolCategory::toolId, id))

        return this.removeById(id)
    }

}