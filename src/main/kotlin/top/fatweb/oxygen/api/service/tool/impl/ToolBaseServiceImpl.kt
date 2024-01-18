package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.converter.tool.ToolBaseConverter
import top.fatweb.oxygen.api.entity.tool.ToolBase
import top.fatweb.oxygen.api.entity.tool.ToolData
import top.fatweb.oxygen.api.exception.NoRecordFoundException
import top.fatweb.oxygen.api.mapper.tool.ToolBaseMapper
import top.fatweb.oxygen.api.param.tool.ToolBaseAddParam
import top.fatweb.oxygen.api.param.tool.ToolBaseUpdateParam
import top.fatweb.oxygen.api.service.tool.IToolBaseService
import top.fatweb.oxygen.api.service.tool.IToolDataService
import top.fatweb.oxygen.api.vo.tool.ToolBaseVo

/**
 * Tool base service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see ToolBaseMapper
 * @see ToolBase
 * @see IToolBaseService
 */
@Service
class ToolBaseServiceImpl(
    private val toolDataService: IToolDataService
) : ServiceImpl<ToolBaseMapper, ToolBase>(), IToolBaseService {
    override fun getOne(id: Long): ToolBaseVo? = baseMapper.selectOne(id)?.let(ToolBaseConverter::toolBaseToToolBaseVo)

    override fun get(): List<ToolBaseVo> = baseMapper.selectList().map(ToolBaseConverter::toolBaseToToolBaseVo)

    @Transactional
    override fun add(toolBaseAddParam: ToolBaseAddParam): ToolBaseVo {
        val newSource = ToolData().apply { data = toolBaseAddParam.source }
        val newDist = ToolData().apply { data = toolBaseAddParam.dist }

        toolDataService.save(newSource)
        toolDataService.save(newDist)

        val toolBase = ToolBase().apply {
            name = toolBaseAddParam.name
            sourceId = newSource.id
            distId = newDist.id
            source = newSource
            dist = newDist
        }

        this.save(toolBase)

        return ToolBaseConverter.toolBaseToToolBaseVo(toolBase)
    }

    @Transactional
    override fun update(toolBaseUpdateParam: ToolBaseUpdateParam): ToolBaseVo {
        val toolBase = baseMapper.selectOne(toolBaseUpdateParam.id!!) ?: throw NoRecordFoundException()

        toolDataService.updateById(ToolData().apply {
            id = toolBase.sourceId
            data = toolBaseUpdateParam.source
        })

        toolDataService.updateById(ToolData().apply {
            id = toolBase.distId
            data = toolBaseUpdateParam.dist
        })

        this.updateById(ToolBase().apply {
            id = toolBaseUpdateParam.id
            name = toolBaseUpdateParam.name
        })

        return this.getOne(toolBase.id!!)!!
    }

    @Transactional
    override fun delete(id: Long): Boolean {
        val toolBase = this.getById(id)

        return toolDataService.removeBatchByIds(listOf(toolBase.sourceId, toolBase.distId))
            && this.removeById(id)
    }
}