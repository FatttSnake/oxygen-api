package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.converter.tool.ToolTemplateConverter
import top.fatweb.oxygen.api.entity.tool.ToolData
import top.fatweb.oxygen.api.entity.tool.ToolTemplate
import top.fatweb.oxygen.api.exception.NoRecordFoundException
import top.fatweb.oxygen.api.mapper.tool.ToolTemplateMapper
import top.fatweb.oxygen.api.param.tool.ToolTemplateAddParam
import top.fatweb.oxygen.api.param.tool.ToolTemplateGetParam
import top.fatweb.oxygen.api.param.tool.ToolTemplateUpdateParam
import top.fatweb.oxygen.api.service.tool.IToolBaseService
import top.fatweb.oxygen.api.service.tool.IToolDataService
import top.fatweb.oxygen.api.service.tool.IToolTemplateService
import top.fatweb.oxygen.api.util.PageUtil
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolTemplateVo

/**
 * Tool template service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
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
    override fun getOne(id: Long): ToolTemplateVo =
        baseMapper.selectOne(id)?.let(ToolTemplateConverter::toolTemplateToToolTemplateVo)
            ?: throw NoRecordFoundException()

    override fun get(toolTemplateGetParam: ToolTemplateGetParam?): PageVo<ToolTemplateVo> {
        val templatePage =
            Page<ToolTemplate>(toolTemplateGetParam?.currentPage ?: 1, toolTemplateGetParam?.pageSize ?: 20)

        PageUtil.setPageSort(toolTemplateGetParam, templatePage)

        return ToolTemplateConverter.toolTemplatePageToToolTemplatePageVo(
            baseMapper.selectListWithBaseName(templatePage, toolTemplateGetParam?.platform?.split(","))
        )
    }

    @Transactional
    override fun add(toolTemplateAddParam: ToolTemplateAddParam): ToolTemplateVo {
        val toolBase = toolBaseService.getOne(toolTemplateAddParam.baseId!!)

        val newSource = ToolData().apply { data = "" }

        toolDataService.save(newSource)

        val toolTemplate = ToolTemplate().apply {
            name = toolTemplateAddParam.name
            baseId = toolTemplateAddParam.baseId
            sourceId = newSource.id
            source = newSource
            platform = toolBase.platform
            entryPoint = toolTemplateAddParam.entryPoint
            enable = if (toolTemplateAddParam.enable) 1 else 0
        }

        this.save(toolTemplate)

        return ToolTemplateConverter.toolTemplateToToolTemplateVo(toolTemplate)
    }

    @Transactional
    override fun update(toolTemplateUpdateParam: ToolTemplateUpdateParam): ToolTemplateVo {
        val toolTemplate = baseMapper.selectOne(toolTemplateUpdateParam.id!!) ?: throw NoRecordFoundException()

        toolDataService.updateById(ToolData().apply {
            id = toolTemplate.sourceId
            data = toolTemplateUpdateParam.source
        })

        this.updateById(ToolTemplate().apply {
            id = toolTemplateUpdateParam.id
            name = toolTemplateUpdateParam.name
            entryPoint = toolTemplateUpdateParam.entryPoint
            enable = toolTemplateUpdateParam.enable?.let { if (it) 1 else 0 }
        })

        return this.getOne(toolTemplate.id!!)
    }

    @Transactional
    override fun delete(id: Long): Boolean {
        val toolTemplate = this.getById(id)

        return toolDataService.removeById(toolTemplate.sourceId)
            && this.removeById(id)
    }
}