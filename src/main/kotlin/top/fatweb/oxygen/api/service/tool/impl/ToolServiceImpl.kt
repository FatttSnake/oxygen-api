package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.mapper.tool.ToolMapper
import top.fatweb.oxygen.api.service.tool.IToolService

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
class ToolServiceImpl : ServiceImpl<ToolMapper, Tool>(), IToolService {
    /*
        override fun getOne(id: Long): ToolVo =
            baseMapper.selectOne(id)?.let(ToolConverter::toolToToolVo) ?: throw NoRecordFoundException()

        override fun get(): List<ToolVo> = baseMapper.selectList().map(ToolConverter::toolToToolVo)

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
    */
}