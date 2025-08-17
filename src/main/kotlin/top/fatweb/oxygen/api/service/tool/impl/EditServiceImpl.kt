package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.converter.tool.toEntity
import top.fatweb.oxygen.api.converter.tool.toVo
import top.fatweb.oxygen.api.converter.tool.toVoWithDist
import top.fatweb.oxygen.api.converter.tool.toVoWithSource
import top.fatweb.oxygen.api.entity.tool.*
import top.fatweb.oxygen.api.exception.*
import top.fatweb.oxygen.api.mapper.tool.EditMapper
import top.fatweb.oxygen.api.param.PageSortParam
import top.fatweb.oxygen.api.param.tool.*
import top.fatweb.oxygen.api.service.tool.*
import top.fatweb.oxygen.api.util.*
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.*

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
    private val toolBaseService: IToolBaseService
) : ServiceImpl<EditMapper, Tool>(), IEditService {
    override fun getTemplate(platform: Platform): List<ToolTemplateVo> =
        toolTemplateService.list(
            KtQueryWrapper(ToolTemplate())
                .eq(ToolTemplate::platform, platform)
                .eq(ToolTemplate::enable, 1)
        ).map(ToolTemplate::toVo)

    override fun getTemplate(id: Long): ToolTemplateWithSourceVo =
        toolTemplateService.getOne(id)

    override fun getCategory(): List<ToolCategoryVo> =
        toolCategoryService.list(
            KtQueryWrapper(ToolCategory())
                .eq(ToolCategory::enable, 1)
        ).map(ToolCategory::toVo)

    override fun getBaseDist(id: Long, version: Long): ToolBaseWithDistVo =
        toolBaseService.getDist(id = id, version = version)

    override fun getBaseLatestVersion(id: Long): Long =
        toolBaseService.getLatestVersion(id)

    override fun getOne(id: Long): ToolWithSourceVo =
        queryOrThrowException {
            baseMapper.selectOne(
                id = id,
                userId = getLoginUserId()!!
            )
        }.let(Tool::toVoWithSource)

    override fun source(
        username: String,
        toolId: String,
        ver: String,
        platform: Platform
    ): ToolWithSourceVo {
        if (username == "!" && getLoginUserId() == null) {
            throw NoRecordFoundException()
        }

        return queryOrThrowException {
            baseMapper.selectSource(
                username = username,
                toolId = toolId,
                ver = ver,
                platform = platform,
                operator = getLoginUsername()
            )
        }.let(Tool::toVoWithSource)
    }

    override fun dist(
        username: String,
        toolId: String,
        ver: String,
        platform: Platform
    ): ToolWithDistVo {
        if (username == "!" && getLoginUserId() == null) {
            throw NoRecordFoundException()
        }

        return queryOrThrowException {
            baseMapper.selectDist(
                username = username,
                toolId = toolId,
                ver = ver,
                platform = platform,
                operator = getLoginUsername()
            )
        }.let(Tool::toVoWithDist)
    }

    override fun getPage(pageSortParam: PageSortParam): PageVo<ToolVo> {
        val toolIdsPage = Page<String>(pageSortParam.currentPage, 20)

        setPageSort(pageSortParam, toolIdsPage)

        val toolIdsIPage = baseMapper.selectPersonalToolIdPage(toolIdsPage, getLoginUserId()!!)
        val toolPage = Page<Tool>(toolIdsIPage.current, toolIdsIPage.size, toolIdsIPage.total)
        if (toolIdsIPage.total > 0) {
            toolPage.setRecords(baseMapper.selectListByToolIds(toolIdsIPage.records, getLoginUserId()!!))
        }

        return toolPage.toVo()
    }

    @Transactional
    override fun create(toolCreateParam: ToolCreateParam): ToolWithSourceVo {
        val template = this.getTemplate(toolCreateParam.templateId!!)
        baseMapper.selectOne(
            KtQueryWrapper(Tool())
                .eq(Tool::toolId, toolCreateParam.toolId!!)
                .eq(Tool::authorId, getLoginUserId()!!)
                .eq(Tool::platform, template.platform)
        )?.let {
            throw DuplicateKeyException("The tool already exists")
        }
        val newSource = ToolData().apply { data = template.source!!.data }
        val newDist = ToolData().apply { data = "" }
        saveOrThrowException { toolDataService.saveBatch(listOf(newSource, newDist)) }

        val tool = Tool().apply {
            name = toolCreateParam.name
            toolId = toolCreateParam.toolId
            icon = toolCreateParam.icon
            platform = template.platform
            description = toolCreateParam.description
            baseId = template.base!!.id
            baseVersion = template.base.version
            authorId = getLoginUserId()!!
            ver = toolCreateParam.ver!!.split(".").map(String::toLong).joinToString(".")
            keywords = toolCreateParam.keywords
            sourceId = newSource.id
            distId = newDist.id
            entryPoint = template.entryPoint
        }

        saveOrThrowException { this.save(tool) }

        toolCreateParam.categories!!.forEach {
            queryOrThrowException {
                toolCategoryService.getById(it)
            }
            saveOrThrowException {
                rToolCategoryService.save(RToolCategory().apply {
                    toolId = tool.id
                    categoryId = it
                })
            }
        }

        return this.getOne(tool.id!!)
    }

    @Transactional
    override fun update(toolUpdateParam: ToolUpdateParam) {
        val tool = queryOrThrowException { this.getOne(toolUpdateParam.id!!) }
        if (tool.review == Tool.ReviewType.PROCESSING) {
            throw ToolUnderReviewException()
        }
        if (tool.review == Tool.ReviewType.PASS && tool.publish != 0L) {
            throw ToolHasBeenPublishedException()
        }

        updateOrThrowException { this.updateById(toolUpdateParam.toEntity()) }

        if (!toolUpdateParam.categories.isNullOrEmpty()) {
            val oldCategories = rToolCategoryService.list(
                KtQueryWrapper(RToolCategory())
                    .select(RToolCategory::categoryId)
                    .eq(RToolCategory::toolId, tool.id)
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
                saveOrThrowException {
                    rToolCategoryService.save(RToolCategory().apply {
                        toolId = tool.id
                        categoryId = it
                    })
                }
            }
        }
    }

    @Transactional
    override fun updateSource(toolUpdateSourceParam: ToolUpdateSourceParam) {
        val tool = queryOrThrowException { this.getOne(toolUpdateSourceParam.id!!) }

        updateOrThrowException {
            toolDataService.update(
                KtUpdateWrapper(ToolData())
                    .eq(ToolData::id, tool.source!!.id)
                    .set(ToolData::data, toolUpdateSourceParam.source)
            )
        }
    }

    @Transactional
    override fun upgrade(toolUpgradeParam: ToolUpgradeParam): ToolWithSourceVo {
        val originalTool = this.source(
            username = "!",
            toolId = toolUpgradeParam.toolId!!,
            ver = "latest",
            platform = toolUpgradeParam.platform!!
        )
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
        saveOrThrowException { toolDataService.saveBatch(listOf(newSource, newDist)) }

        val tool = Tool().apply {
            name = originalTool.name!!
            toolId = originalTool.toolId
            icon = originalTool.icon
            platform = originalTool.platform
            description = originalTool.description
            baseId = originalTool.baseId
            baseVersion = originalTool.baseVersion
            authorId = getLoginUserId()!!
            ver = newVersionNumberList.joinToString(".")
            keywords = originalTool.keywords
            sourceId = newSource.id
            distId = newDist.id
            entryPoint = originalTool.entryPoint
        }

        saveOrThrowException { this.save(tool) }

        originalTool.categories!!.forEach {
            queryOrThrowException { toolCategoryService.getById(it.id) }
            saveOrThrowException {
                rToolCategoryService.save(RToolCategory().apply {
                    toolId = tool.id
                    categoryId = it.id
                })
            }
        }

        return this.getOne(tool.id!!)
    }

    @Transactional
    override fun upgradeBase(toolOrTemplateUpgradeBaseParam: ToolOrTemplateUpgradeBaseParam) {
        val tool = queryOrThrowException { this.getOne(toolOrTemplateUpgradeBaseParam.id!!) }
        if (tool.baseVersion!! >= toolOrTemplateUpgradeBaseParam.baseVersion!!) {
            throw DatabaseUpdateException()
        }
        toolBaseService.getOne(id = tool.baseId!!, version = toolOrTemplateUpgradeBaseParam.baseVersion)

        updateOrThrowException {
            this.update(
                KtUpdateWrapper(Tool())
                    .eq(Tool::id, toolOrTemplateUpgradeBaseParam.id)
                    .set(Tool::baseVersion, toolOrTemplateUpgradeBaseParam.baseVersion)
            )
        }
    }

    override fun submit(id: Long): Boolean {
        val tool = queryOrThrowException { this.getById(id) }
        if (tool.review == Tool.ReviewType.PROCESSING) {
            throw ToolUnderReviewException()
        }
        if (tool.review == Tool.ReviewType.PASS) {
            throw ToolHasBeenPublishedException()
        }

        return update(
            KtUpdateWrapper(Tool())
                .eq(Tool::id, id)
                .set(Tool::review, Tool.ReviewType.PROCESSING)
        )
    }

    override fun cancel(id: Long): Boolean {
        val tool = queryOrThrowException { this.getById(id) }
        if (tool.review == Tool.ReviewType.PASS) {
            throw ToolHasBeenPublishedException()
        }
        if (tool.review != Tool.ReviewType.PROCESSING) {
            throw ToolNotUnderReviewException()
        }

        return update(
            KtUpdateWrapper(Tool())
                .eq(Tool::id, id)
                .set(Tool::review, Tool.ReviewType.NONE)
        )
    }

    @Transactional
    override fun delete(id: Long): Boolean {
        val tool = queryOrThrowException {
            baseMapper.selectOne(
                KtQueryWrapper(Tool()).eq(Tool::id, id)
                    .eq(Tool::authorId, getLoginUserId()!!)
            )
        }

        toolDataService.removeBatchByIds(listOf(tool.sourceId, tool.distId))
        rToolCategoryService.remove(KtQueryWrapper(RToolCategory()).eq(RToolCategory::toolId, tool.id))

        return this.removeById(id)
    }
}
