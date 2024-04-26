package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.converter.tool.ToolCategoryConverter
import top.fatweb.oxygen.api.converter.tool.ToolConverter
import top.fatweb.oxygen.api.converter.tool.ToolTemplateConverter
import top.fatweb.oxygen.api.entity.tool.*
import top.fatweb.oxygen.api.exception.*
import top.fatweb.oxygen.api.mapper.tool.EditMapper
import top.fatweb.oxygen.api.param.tool.*
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
    private val rToolCategoryService: IRToolCategoryService,
    private val toolFavoriteService: IToolFavoriteService
) : ServiceImpl<EditMapper, Tool>(), IEditService {
    override fun getTemplate(platform: ToolBase.Platform): List<ToolTemplateVo> =
        toolTemplateService.list(
            KtQueryWrapper(ToolTemplate())
                .eq(ToolTemplate::platform, platform)
                .eq(ToolTemplate::enable, 1)
        ).map(ToolTemplateConverter::toolTemplateToToolTemplateVoByList)

    override fun getTemplate(id: Long): ToolTemplateVo =
        baseMapper.selectTemplate(id)?.let(ToolTemplateConverter::toolTemplateToToolTemplateVoWithBaseDist)
            ?: throw NoRecordFoundException()

    override fun getCategory(): List<ToolCategoryVo> =
        toolCategoryService.list(KtQueryWrapper(ToolCategory()).eq(ToolCategory::enable, 1))
            .map(ToolCategoryConverter::toolCategoryToToolCategoryVo)

    override fun getOne(id: Long): ToolVo =
        baseMapper.selectOne(id, WebUtil.getLoginUserId()!!)
            ?.let(ToolConverter::toolToToolVo) ?: throw NoRecordFoundException()

    @Transactional
    override fun create(toolCreateParam: ToolCreateParam): ToolVo {
        val template = this.getTemplate(toolCreateParam.templateId!!)
        baseMapper.selectOne(
            KtQueryWrapper(Tool())
                .eq(Tool::toolId, toolCreateParam.toolId!!)
                .eq(Tool::authorId, WebUtil.getLoginUserId()!!)
                .eq(Tool::platform, template.platform)
        )?.let {
            throw DuplicateKeyException("Duplicate Key")
        }
        val newSource = ToolData().apply { data = template.source!!.data }
        val newDist = ToolData().apply { data = "" }
        toolDataService.saveBatch(listOf(newSource, newDist))

        val tool = Tool().apply {
            name = toolCreateParam.name!!.trim()
            toolId = toolCreateParam.toolId
            icon = toolCreateParam.icon
            platform = template.platform
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

        toolCreateParam.categories!!.forEach {
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
        val originalTool = this.detail("!", toolUpgradeParam.toolId!!, "latest", toolUpgradeParam.platform!!)
        if (originalTool.review == Tool.ReviewType.PROCESSING) {
            throw ToolUnderReviewException()
        }
        if (originalTool.review != Tool.ReviewType.PASS || originalTool.publish == 0L) {
            throw ToolHasUnpublishedVersionException()
        }

        val originalVersion = originalTool.ver!!
        val originalVersionNumberList = originalVersion.split(".").map(String::toLong)
        val newVersionNumberList = toolUpgradeParam.ver!!.split(".").map(String::toLong)
        if (!newVersionNumberList.foldIndexed(false) { index: Int, acc: Boolean, version: Long ->
                if (!acc && originalVersionNumberList[index] > version) {
                    throw IllegalVersionException()
                }
                if (originalVersionNumberList[index] < version) true else acc
            }) {
            throw IllegalVersionException()
        }

        val newSource = ToolData().apply { data = originalTool.source!!.data }
        val newDist = ToolData().apply { data = "" }
        toolDataService.saveBatch(listOf(newSource, newDist))

        val tool = Tool().apply {
            name = originalTool.name!!
            toolId = originalTool.toolId
            icon = originalTool.icon
            platform = originalTool.platform
            description = originalTool.description
            baseId = originalTool.base!!.id
            authorId = WebUtil.getLoginUserId()!!
            ver = newVersionNumberList.joinToString(".")
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
        val tool = getById(toolUpdateParam.id)
        if (tool.review == Tool.ReviewType.PROCESSING) {
            throw ToolUnderReviewException()
        }
        if (tool.review == Tool.ReviewType.PASS || tool.publish != 0L) {
            throw ToolHasBeenPublishedException()
        }

        this.updateById(Tool().apply {
            id = toolUpdateParam.id
            name = toolUpdateParam.name
            icon = toolUpdateParam.icon
            description = toolUpdateParam.description
            keywords = toolUpdateParam.keywords
        })

        if (!toolUpdateParam.categories.isNullOrEmpty()) {
            val oldCategories = rToolCategoryService.list(
                KtQueryWrapper(RToolCategory()).select(RToolCategory::categoryId).eq(RToolCategory::toolId, tool.id)
            ).map(RToolCategory::categoryId)
            val addCategories = HashSet<Long>()
            val removeCategories = HashSet<Long>()
            toolUpdateParam.categories.forEach(addCategories::add)
            oldCategories.forEach {
                it?.let(removeCategories::add)
            }
            removeCategories.removeAll(addCategories)
            oldCategories.toSet().let(addCategories::removeAll)

            removeCategories.forEach {
                rToolCategoryService.remove(
                    KtQueryWrapper(RToolCategory()).eq(
                        RToolCategory::toolId, tool.id
                    ).eq(RToolCategory::categoryId, it)
                )
            }

            addCategories.forEach {
                rToolCategoryService.save(RToolCategory().apply {
                    toolId = tool.id
                    categoryId = it
                })
            }
        }

        if (!toolUpdateParam.source.isNullOrBlank()) {
            toolDataService.updateById(ToolData().apply {
                id = tool.sourceId
                data = toolUpdateParam.source
            })
        }

        return this.getOne(tool.id!!)
    }

    override fun get(): List<ToolVo> =
        baseMapper.selectPersonal(WebUtil.getLoginUserId()!!)
            .map(ToolConverter::toolToToolVo)

    override fun detail(username: String, toolId: String, ver: String, platform: ToolBase.Platform): ToolVo {
        if (username == "!" && WebUtil.getLoginUserId() == null) {
            throw NoRecordFoundException()
        }

        return baseMapper.selectDetail(username, toolId, ver, platform, WebUtil.getLoginUsername())
            ?.let(ToolConverter::toolToToolVo) ?: throw NoRecordFoundException()
    }

    override fun submit(id: Long): Boolean {
        val tool = getById(id)
        if (tool.review == Tool.ReviewType.PROCESSING) {
            throw ToolUnderReviewException()
        }
        if (tool.review == Tool.ReviewType.PASS && tool.publish != 0L) {
            throw ToolHasBeenPublishedException()
        }

        return update(KtUpdateWrapper(Tool()).eq(Tool::id, id).set(Tool::review, Tool.ReviewType.PROCESSING))
    }

    override fun cancel(id: Long): Boolean {
        val tool = getById(id)
        if (tool.review == Tool.ReviewType.PASS && tool.publish != 0L) {
            throw ToolHasBeenPublishedException()
        }
        if (tool.review != Tool.ReviewType.PROCESSING) {
            throw ToolNotUnderReviewException()
        }

        return update(KtUpdateWrapper(Tool()).eq(Tool::id, id).set(Tool::review, Tool.ReviewType.NONE))
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

    @Transactional
    override fun addFavorite(toolFavoriteAddParam: ToolFavoriteAddParam) {
        if (toolFavoriteService.exists(
                KtQueryWrapper(ToolFavorite())
                    .eq(ToolFavorite::userId, WebUtil.getLoginUserId())
                    .eq(ToolFavorite::username, toolFavoriteAddParam.username)
                    .eq(ToolFavorite::toolId, toolFavoriteAddParam.toolId)
                    .eq(ToolFavorite::platform, toolFavoriteAddParam.platform)
            )
        ) {
            throw RecordAlreadyExists()
        }

        this.detail(toolFavoriteAddParam.username!!, toolFavoriteAddParam.toolId!!, "latest", toolFavoriteAddParam.platform!!)

        toolFavoriteService.save(
            ToolFavorite().apply {
                userId = WebUtil.getLoginUserId()
                username = toolFavoriteAddParam.username
                toolId = toolFavoriteAddParam.toolId
                platform = toolFavoriteAddParam.platform
            }
        )
    }

    @Transactional
    override fun removeFavorite(toolFavoriteRemoveParam: ToolFavoriteRemoveParam) {
        if (!toolFavoriteService.remove(
                KtQueryWrapper(ToolFavorite())
                    .eq(ToolFavorite::userId, WebUtil.getLoginUserId())
                    .eq(ToolFavorite::username, toolFavoriteRemoveParam.username)
                    .eq(ToolFavorite::toolId, toolFavoriteRemoveParam.toolId)
                    .eq(ToolFavorite::platform, toolFavoriteRemoveParam.platform)
            )
        ) {
            throw NoRecordFoundException()
        }
    }
}