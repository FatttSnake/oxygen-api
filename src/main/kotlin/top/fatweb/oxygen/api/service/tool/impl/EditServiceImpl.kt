package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.converter.tool.ToolCategoryConverter
import top.fatweb.oxygen.api.converter.tool.ToolConverter
import top.fatweb.oxygen.api.converter.tool.ToolTemplateConverter
import top.fatweb.oxygen.api.entity.tool.*
import top.fatweb.oxygen.api.exception.IllegalVersionException
import top.fatweb.oxygen.api.exception.NoRecordFoundException
import top.fatweb.oxygen.api.mapper.tool.EditMapper
import top.fatweb.oxygen.api.param.tool.ToolCreateParam
import top.fatweb.oxygen.api.param.tool.ToolUpdateParam
import top.fatweb.oxygen.api.param.tool.ToolUpgradeParam
import top.fatweb.oxygen.api.service.tool.*
import top.fatweb.oxygen.api.util.WebUtil
import top.fatweb.oxygen.api.vo.tool.ToolCategoryVo
import top.fatweb.oxygen.api.vo.tool.ToolTemplateVo
import top.fatweb.oxygen.api.vo.tool.ToolVo

/**
 * Tool edit service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see EditMapper
 * @see Tool
 * @see IEditService
 */
@Service
class EditServiceImpl(
    private val toolTemplateService: IToolTemplateService,
    private val toolCategoryService: IToolCategoryService,
    private val toolDataService: IToolDataService,
    private val rToolCategoryService: IRToolCategoryService
) : ServiceImpl<EditMapper, Tool>(), IEditService {
    override fun getTemplate(): List<ToolTemplateVo> =
        toolTemplateService.list(KtQueryWrapper(ToolTemplate()).eq(ToolTemplate::enable, 1))
            .map(ToolTemplateConverter::toolTemplateToToolTemplateVoByList)

    override fun getTemplate(id: Long): ToolTemplateVo =
        baseMapper.getTemplate(id)?.let(ToolTemplateConverter::toolTemplateToToolTemplateVoWithBaseDist)
            ?: throw NoRecordFoundException()

    override fun getCategory(): List<ToolCategoryVo> =
        toolCategoryService.list(KtQueryWrapper(ToolCategory()).eq(ToolCategory::enable, 1))
            .map(ToolCategoryConverter::toolCategoryToToolCategoryVo)

    override fun getOne(id: Long): ToolVo =
        baseMapper.selectOne(id, WebUtil.getLoginUserId()!!)
            ?.let(ToolConverter::toolToToolVo) ?: throw NoRecordFoundException()

    @Transactional
    override fun create(toolCreateParam: ToolCreateParam): ToolVo {
        baseMapper.selectOne(
            KtQueryWrapper(Tool()).eq(Tool::toolId, toolCreateParam.toolId!!)
                .eq(Tool::authorId, WebUtil.getLoginUserId()!!)
        )?.let { throw DuplicateKeyException("Duplicate Key") }
        val template = this.getTemplate(toolCreateParam.templateId!!)
        val newSource = ToolData().apply { data = template.source!!.data }
        val newDist = ToolData().apply { data = "" }
        toolDataService.saveBatch(listOf(newSource, newDist))

        val tool = Tool().apply {
            name = toolCreateParam.name!!.trim()
            toolId = toolCreateParam.toolId
            icon = toolCreateParam.icon
            description = toolCreateParam.description
            baseId = template.base!!.id
            authorId = WebUtil.getLoginUserId()!!
            ver = toolCreateParam.ver!!.split(".").map(String::toLong).joinToString(".")
            keywords = toolCreateParam.keywords
            sourceId = newSource.id
            distId = newDist.id
            entryPoint = template.entryPoint
        }

        this.save(tool)

        toolCreateParam.categories.forEach {
            toolCategoryService.getById(it) ?: throw NoRecordFoundException()
            rToolCategoryService.save(RToolCategory().apply {
                toolId = tool.id
                categoryId = it
            })
        }

        return this.getOne(tool.id!!)
    }

    @Transactional
    override fun upgrade(toolUpgradeParam: ToolUpgradeParam): ToolVo {
        val originalTool = this.detail("!", toolUpgradeParam.toolId!!, "latest")

        val originalVersion = originalTool.ver!!
        if (originalVersion.split(".").map(String::toLong).joinToString(".") == toolUpgradeParam.ver!!.split(".")
                .map(String::toLong).joinToString(".")
        ) {
            throw IllegalVersionException()
        }
        originalVersion.split(".").forEachIndexed { index, s ->
            if ((toolUpgradeParam.ver.split(".")[index].toLong() < s.toLong())) {
                throw IllegalVersionException()
            }
        }

        val newSource = ToolData().apply { data = originalTool.source!!.data }
        val newDist = ToolData().apply { data = "" }
        toolDataService.saveBatch(listOf(newSource, newDist))

        val tool = Tool().apply {
            name = originalTool.name!!
            toolId = originalTool.toolId
            icon = originalTool.icon
            description = originalTool.description
            baseId = originalTool.base!!.id
            authorId = WebUtil.getLoginUserId()!!
            ver = toolUpgradeParam.ver.split(".").map(String::toLong).joinToString(".")
            keywords = originalTool.keywords
            sourceId = newSource.id
            distId = newDist.id
            entryPoint = originalTool.entryPoint
        }

        this.save(tool)

        originalTool.categories!!.forEach {
            toolCategoryService.getById(it.id) ?: throw NoRecordFoundException()
            rToolCategoryService.save(RToolCategory().apply {
                toolId = tool.id
                categoryId = it.id
            })
        }

        return this.getOne(tool.id!!)
    }

    @Transactional
    override fun update(toolUpdateParam: ToolUpdateParam): ToolVo {
        TODO("Not yet implemented")
    }

    override fun get(): List<ToolVo> =
        baseMapper.selectPersonal(WebUtil.getLoginUserId()!!)
            .map(ToolConverter::toolToToolVo)

    override fun detail(username: String, toolId: String, ver: String): ToolVo {
        if (username == "!" && WebUtil.getLoginUserId() == null) {
            throw NoRecordFoundException()
        }

        return baseMapper.detail(username, toolId, ver, WebUtil.getLoginUsername())?.let(ToolConverter::toolToToolVo)
            ?: throw NoRecordFoundException()
    }

    @Transactional
    override fun delete(id: Long): Boolean {
        val tool = baseMapper.selectOne(
            KtQueryWrapper(Tool()).eq(Tool::id, id)
                .eq(Tool::authorId, WebUtil.getLoginUserId()!!)
        ) ?: throw NoRecordFoundException()

        toolDataService.removeBatchByIds(listOf(tool.sourceId, tool.distId))
        rToolCategoryService.remove(KtQueryWrapper(RToolCategory()).eq(RToolCategory::toolId, tool.id))
        return this.removeById(id)
    }
}