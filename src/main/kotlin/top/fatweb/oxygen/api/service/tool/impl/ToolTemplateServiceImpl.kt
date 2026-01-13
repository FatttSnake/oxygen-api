package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.converter.tool.toEntity
import top.fatweb.oxygen.api.converter.tool.toVo
import top.fatweb.oxygen.api.converter.tool.toVoWithSource
import top.fatweb.oxygen.api.entity.tool.ToolData
import top.fatweb.oxygen.api.entity.tool.ToolTemplate
import top.fatweb.oxygen.api.exception.DatabaseUpdateException
import top.fatweb.oxygen.api.mapper.tool.ToolTemplateMapper
import top.fatweb.oxygen.api.param.tool.*
import top.fatweb.oxygen.api.service.tool.IToolBaseService
import top.fatweb.oxygen.api.service.tool.IToolDataService
import top.fatweb.oxygen.api.service.tool.IToolTemplateService
import top.fatweb.oxygen.api.util.queryOrThrowException
import top.fatweb.oxygen.api.util.saveOrThrowException
import top.fatweb.oxygen.api.util.setPageSort
import top.fatweb.oxygen.api.util.updateOrThrowException
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolTemplateVo
import top.fatweb.oxygen.api.vo.tool.ToolTemplateWithSourceVo
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * Tool template service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IToolDataService
 * @see IToolBaseService
 * @see ServiceImpl
 * @see ToolTemplateMapper
 * @see ToolTemplate
 * @see IToolTemplateService
 */
@Service
class ToolTemplateServiceImpl(
    private val toolDataService: IToolDataService,
    private val toolBaseService: IToolBaseService
) : ServiceImpl<ToolTemplateMapper, ToolTemplate>(), IToolTemplateService {
    override fun getOne(id: Long): ToolTemplateWithSourceVo =
        queryOrThrowException {
            baseMapper.selectOne(id)
        }.let(ToolTemplate::toVoWithSource)

    override fun get(toolTemplateGetParam: ToolTemplateGetParam?): PageVo<ToolTemplateVo> {
        val templatePage =
            Page<ToolTemplate>(toolTemplateGetParam?.currentPage ?: 1, toolTemplateGetParam?.pageSize ?: 20)

        setPageSort(toolTemplateGetParam, templatePage)

        return baseMapper
            .selectPageWithBase(
                page = templatePage,
                platform = toolTemplateGetParam?.platform?.split(",")
            )
            .toVo()
    }

    @Transactional
    override fun add(toolTemplateAddParam: ToolTemplateAddParam): ToolTemplateWithSourceVo {
        val toolBase = toolBaseService.getOne(toolTemplateAddParam.baseId!!, toolTemplateAddParam.baseVersion!!)

        val newSource = ToolData().apply { data = "" }

        saveOrThrowException { toolDataService.save(newSource) }

        val toolTemplate = toolTemplateAddParam.toEntity().apply {
            sourceId = newSource.id
            platform = toolBase.platform
        }

        saveOrThrowException { this.save(toolTemplate) }

        return this.getOne(toolTemplate.id!!)
    }

    @Transactional
    override fun update(toolTemplateUpdateParam: ToolTemplateUpdateParam) {
        val toolTemplate = toolTemplateUpdateParam.toEntity()

        updateOrThrowException { this.updateById(toolTemplate) }
    }

    @Transactional
    override fun updateSource(toolTemplateUpdateSourceParam: ToolTemplateUpdateSourceParam) {
        val toolTemplate = queryOrThrowException { this.getById(toolTemplateUpdateSourceParam.id) }

        updateOrThrowException {
            toolDataService.update(
                KtUpdateWrapper(ToolData())
                    .eq(ToolData::id, toolTemplate.sourceId)
                    .set(ToolData::data, toolTemplateUpdateSourceParam.source)
            )
        }

        updateOrThrowException {
            this.update(
                KtUpdateWrapper(ToolTemplate())
                    .eq(ToolTemplate::id, toolTemplateUpdateSourceParam.id)
                    .set(ToolTemplate::updateTime, LocalDateTime.now(ZoneOffset.UTC))
            )
        }
    }

    @Transactional
    override fun upgradeBase(toolOrTemplateUpgradeBaseParam: ToolOrTemplateUpgradeBaseParam) {
        val toolTemplate = queryOrThrowException { this.getOne(toolOrTemplateUpgradeBaseParam.id!!) }
        if (toolTemplate.base!!.version!! >= toolOrTemplateUpgradeBaseParam.baseVersion!!) {
            throw DatabaseUpdateException()
        }
        toolBaseService.getOne(id = toolTemplate.base.id!!, version = toolOrTemplateUpgradeBaseParam.baseVersion)

        updateOrThrowException {
            this.update(
                KtUpdateWrapper(ToolTemplate())
                    .eq(ToolTemplate::id, toolOrTemplateUpgradeBaseParam.id)
                    .set(ToolTemplate::baseVersion, toolOrTemplateUpgradeBaseParam.baseVersion)
                    .set(ToolTemplate::updateTime, LocalDateTime.now(ZoneOffset.UTC))
            )
        }
    }

    @Transactional
    override fun delete(id: Long): Boolean {
        val toolTemplate = this.getById(id)

        return toolDataService.removeById(toolTemplate.sourceId)
                && this.removeById(id)
    }
}
