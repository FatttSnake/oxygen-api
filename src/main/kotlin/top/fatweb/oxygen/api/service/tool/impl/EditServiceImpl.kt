package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.converter.tool.ToolCategoryConverter
import top.fatweb.oxygen.api.converter.tool.ToolConverter
import top.fatweb.oxygen.api.converter.tool.ToolTemplateConverter
import top.fatweb.oxygen.api.entity.tool.*
import top.fatweb.oxygen.api.exception.NoRecordFoundException
import top.fatweb.oxygen.api.exception.UserNotFoundException
import top.fatweb.oxygen.api.mapper.tool.EditMapper
import top.fatweb.oxygen.api.param.tool.ToolCreateParam
import top.fatweb.oxygen.api.param.tool.ToolUpdateParam
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
        baseMapper.selectOne(id, WebUtil.getLoginUserId() ?: throw UserNotFoundException())
            ?.let(ToolConverter::toolToToolVo) ?: throw NoRecordFoundException()

    @Transactional
    override fun create(toolCreateParam: ToolCreateParam): ToolVo {
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
            authorId = WebUtil.getLoginUserId() ?: throw UserNotFoundException()
            ver = toolCreateParam.ver!!.split(".").map(String::toLong).joinToString(".")
            keywords = toolCreateParam.keywords
            sourceId = newSource.id
            distId = newDist.id
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
    override fun update(toolUpdateParam: ToolUpdateParam): ToolVo {
        TODO("Not yet implemented")
    }

    override fun get(): List<ToolVo> =
        baseMapper.selectPersonal(WebUtil.getLoginUserId() ?: throw UserNotFoundException())
            .map(ToolConverter::toolToToolVo)

    override fun detail(username: String, toolId: String, ver: String): ToolVo {
        if (username == "!" && WebUtil.getLoginUserId() == null) {
            throw NoRecordFoundException()
        }

        return baseMapper.detail(username, toolId, ver, WebUtil.getLoginUsername())?.let(ToolConverter::toolToToolVo)
            ?: throw NoRecordFoundException()
    }
}