package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.converter.tool.ToolBaseConverter
import top.fatweb.oxygen.api.entity.tool.ToolBase
import top.fatweb.oxygen.api.entity.tool.ToolData
import top.fatweb.oxygen.api.exception.NoRecordFoundException
import top.fatweb.oxygen.api.mapper.tool.ToolBaseMapper
import top.fatweb.oxygen.api.param.tool.ToolBaseAddParam
import top.fatweb.oxygen.api.param.tool.ToolBaseGetParam
import top.fatweb.oxygen.api.param.tool.ToolBaseUpdateParam
import top.fatweb.oxygen.api.service.tool.IToolBaseService
import top.fatweb.oxygen.api.service.tool.IToolDataService
import top.fatweb.oxygen.api.util.PageUtil
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseVo

/**
 * Tool base service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IToolDataService
 * @see ServiceImpl
 * @see ToolBaseMapper
 * @see ToolBase
 * @see IToolBaseService
 */
@Service
class ToolBaseServiceImpl(
    private val toolDataService: IToolDataService
) : ServiceImpl<ToolBaseMapper, ToolBase>(), IToolBaseService {
    override fun getOne(id: Long): ToolBaseVo =
        baseMapper.selectOne(id)?.let(ToolBaseConverter::toolBaseToToolBaseVo) ?: throw NoRecordFoundException()

    override fun get(toolBaseGetParam: ToolBaseGetParam?): PageVo<ToolBaseVo> {
        val basePage = Page<ToolBase>(toolBaseGetParam?.currentPage ?: 1, toolBaseGetParam?.pageSize ?: 20)

        PageUtil.setPageSort(toolBaseGetParam, basePage)

        return ToolBaseConverter.toolBasePageToToolBasePageVo(
            this.page(
                basePage,
                KtQueryWrapper(ToolBase()).`in`(
                    !toolBaseGetParam?.platform.isNullOrBlank(),
                    ToolBase::platform,
                    toolBaseGetParam?.platform?.split(",")
                )
            )
        )
    }

    override fun getList(): List<ToolBaseVo> = this.list().map(ToolBaseConverter::toolBaseToToolBaseVoByGetList)

    @Transactional
    override fun add(toolBaseAddParam: ToolBaseAddParam): ToolBaseVo {
        val newSource = ToolData().apply { data = "" }
        val newDist = ToolData().apply { data = "" }

        toolDataService.saveBatch(listOf(newSource, newDist))

        val toolBase = ToolBase().apply {
            name = toolBaseAddParam.name
            sourceId = newSource.id
            distId = newDist.id
            source = newSource
            dist = newDist
            platform = toolBaseAddParam.platform
        }

        this.save(toolBase)

        return ToolBaseConverter.toolBaseToToolBaseVo(toolBase)
    }

    @Transactional
    override fun update(toolBaseUpdateParam: ToolBaseUpdateParam): ToolBaseVo {
        val toolBase = baseMapper.selectOne(toolBaseUpdateParam.id!!) ?: throw NoRecordFoundException()

        var hasCompiled: Int? = null

        if (!toolBaseUpdateParam.source.isNullOrBlank()) {
            toolDataService.updateById(ToolData().apply {
                id = toolBase.sourceId
                data = toolBaseUpdateParam.source
            })
            hasCompiled = 0
        }

        if (!toolBaseUpdateParam.dist.isNullOrBlank()) {
            toolDataService.updateById(ToolData().apply {
                id = toolBase.distId
                data = toolBaseUpdateParam.dist
            })
            hasCompiled = 1
        }

        this.updateById(ToolBase().apply {
            id = toolBaseUpdateParam.id
            name = toolBaseUpdateParam.name
            compiled = hasCompiled
        })

        return this.getOne(toolBase.id!!)
    }

    @Transactional
    override fun delete(id: Long): Boolean {
        val toolBase = this.getById(id)

        return toolDataService.removeBatchByIds(listOf(toolBase.sourceId, toolBase.distId))
            && this.removeById(id)
    }
}