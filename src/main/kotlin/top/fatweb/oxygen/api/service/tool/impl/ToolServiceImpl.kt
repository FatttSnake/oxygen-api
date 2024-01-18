package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.converter.tool.ToolConverter
import top.fatweb.oxygen.api.entity.tool.RToolCategory
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.entity.tool.ToolData
import top.fatweb.oxygen.api.exception.NoRecordFoundException
import top.fatweb.oxygen.api.exception.ToolHasPublish
import top.fatweb.oxygen.api.mapper.tool.ToolMapper
import top.fatweb.oxygen.api.param.tool.ToolAddParam
import top.fatweb.oxygen.api.param.tool.ToolUpdateParam
import top.fatweb.oxygen.api.service.permission.IUserService
import top.fatweb.oxygen.api.service.tool.*
import top.fatweb.oxygen.api.vo.tool.ToolVo

/**
 * Tool service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see ToolMapper
 * @see Tool
 * @see IToolService
 */
@Service
class ToolServiceImpl(
    private val toolDataService: IToolDataService,
    private val toolBaseService: IToolBaseService,
    private val toolCategoryService: IToolCategoryService,
    private val rToolCategoryService: IRToolCategoryService,
    private val userService: IUserService
) : ServiceImpl<ToolMapper, Tool>(), IToolService {
    override fun getOne(id: Long): ToolVo =
        baseMapper.selectOne(id)?.let(ToolConverter::toolToToolVo) ?: throw NoRecordFoundException()

    override fun get(): List<ToolVo> = baseMapper.selectList().map(ToolConverter::toolToToolVo)

    @Transactional
    override fun add(toolAddParam: ToolAddParam): ToolVo {
        toolBaseService.getOne(toolAddParam.baseId!!)
        userService.getOne(toolAddParam.authorId!!)

        val newSource = ToolData().apply { data = toolAddParam.source }
        val newDist = ToolData().apply { data = toolAddParam.dist }

        toolDataService.save(newSource)
        toolDataService.save(newDist)

        val tool = Tool().apply {
            name = toolAddParam.name
            toolId = toolAddParam.toolId
            description = toolAddParam.description
            baseId = toolAddParam.baseId
            authorId = toolAddParam.authorId
            ver = toolAddParam.ver
            privately = if (toolAddParam.privately) 1 else 0
            keywords = toolAddParam.keywords
            sourceId = newSource.id
            distId = newDist.id
        }

        this.save(tool)

        toolAddParam.categories.forEach {
            toolCategoryService.getById(it) ?: throw NoRecordFoundException()
            rToolCategoryService.save(RToolCategory().apply {
                toolId = tool.id
                categoryId = it
            })
        }

        return this.getOne(tool.id!!)
    }

    @Transactional
    override fun update(toolUpdateParam: ToolUpdateParam): ToolVo {
        val tool = baseMapper.selectOne(toolUpdateParam.id!!) ?: throw NoRecordFoundException()
        if (tool.publish == 1) {
            throw ToolHasPublish()
        }
        userService.getOne(toolUpdateParam.authorId!!)

        toolDataService.updateById(ToolData().apply {
            id = tool.sourceId
            data = toolUpdateParam.source
        })

        toolDataService.updateById(ToolData().apply {
            id = tool.distId
            data = toolUpdateParam.dist
        })

        this.updateById(Tool().apply {
            id = toolUpdateParam.id
            name = toolUpdateParam.name
            toolId = toolUpdateParam.toolId
            description = toolUpdateParam.description
            authorId = toolUpdateParam.authorId
            ver = toolUpdateParam.ver
            privately = toolUpdateParam.privately?.let { if (it) 1 else 0 }
            keywords = toolUpdateParam.keywords
        })

        // TODO Category process

        return this.getOne(tool.id!!)
    }

    @Transactional
    override fun delete(id: Long): Boolean {
        val tool = this.getById(id)

        return toolDataService.removeBatchByIds(listOf(tool.sourceId, tool.distId))
            && rToolCategoryService.remove(KtQueryWrapper(RToolCategory()).eq(RToolCategory::toolId, tool.id))
            && this.removeById(tool.id)
    }
}