package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.converter.tool.toEntity
import top.fatweb.oxygen.api.converter.tool.toVo
import top.fatweb.oxygen.api.converter.tool.toVoWithDist
import top.fatweb.oxygen.api.converter.tool.toVoWithSource
import top.fatweb.oxygen.api.entity.tool.RToolBaseData
import top.fatweb.oxygen.api.entity.tool.ToolBase
import top.fatweb.oxygen.api.entity.tool.ToolSource.Companion.copy
import top.fatweb.oxygen.api.exception.ToolBaseHasBeenCompiledException
import top.fatweb.oxygen.api.mapper.tool.ToolBaseMapper
import top.fatweb.oxygen.api.param.tool.ToolBaseAddParam
import top.fatweb.oxygen.api.param.tool.ToolBaseGetParam
import top.fatweb.oxygen.api.param.tool.ToolBaseUpdateParam
import top.fatweb.oxygen.api.param.tool.ToolCommonUpdateSourceAddParam
import top.fatweb.oxygen.api.service.system.IStorageBlobService
import top.fatweb.oxygen.api.service.tool.*
import top.fatweb.oxygen.api.util.*
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseWithDistVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseWithSourceVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseWithVersionsVo
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * Tool base service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IStorageBlobService
 * @see IRToolBaseDataService
 * @see IToolSourceService
 * @see IToolFileVersionService
 * @see IToolDistService
 * @see ServiceImpl
 * @see ToolBaseMapper
 * @see ToolBase
 * @see IToolBaseService
 */
@Service
class ToolBaseServiceImpl(
    private val storageBlobService: IStorageBlobService,
    private val rToolBaseDataService: IRToolBaseDataService,
    private val toolSourceService: IToolSourceService,
    private val toolFileVersionService: IToolFileVersionService,
    private val toolDistService: IToolDistService
) : ServiceImpl<ToolBaseMapper, ToolBase>(), IToolBaseService {
    @Transactional
    override fun getOne(id: Long, version: Long): ToolBaseWithSourceVo =
        queryOrThrowException {
            baseMapper.selectOne(id = id, version = version)?.let {
                if (!it.sources.isNullOrEmpty()) {
                    return@let it
                }
                if (version != 0L) {
                    return@let null
                }
                val latestVersion = queryOrThrowException { baseMapper.selectLatestVersionInfo(id) }

                val (newRootId, newSources, newFileVersions) = latestVersion.sources!!.copy()

                saveOrThrowException { toolFileVersionService.saveBatch(newFileVersions) }
                saveOrThrowException { toolSourceService.saveBatch(newSources) }

                val rToolBaseSource = RToolBaseData().apply {
                    baseId = id
                    dataId = newRootId
                    dataType = RToolBaseData.DataType.SOURCE
                }
                saveOrThrowException { rToolBaseDataService.save(rToolBaseSource) }

                baseMapper.selectOne(id = id, version = version)
            }
        }.apply {
            sources?.forEach { source ->
                source.latestFileVersion?.apply {
                    fileContent = storageBlobService.loadFile(fileHash!!)?.toString(Charsets.UTF_8)
                }
            }
        }.let(ToolBase::toVoWithSource)

    override fun getDist(id: Long, version: Long): ToolBaseWithDistVo =
        queryOrThrowException {
            baseMapper.selectDist(id = id, version = version)
        }.apply {
            dist?.apply {
                fileContent = storageBlobService.loadFile(fileHash!!)?.toString(Charsets.UTF_8)
            }
        }.let(ToolBase::toVoWithDist)

    override fun getLatestVersion(id: Long): Long =
        queryOrThrowException {
            baseMapper.selectLatestVersion(id)
        }

    override fun get(toolBaseGetParam: ToolBaseGetParam?): PageVo<ToolBaseWithVersionsVo> {
        var basePage = Page<ToolBase>(toolBaseGetParam?.currentPage ?: 1, toolBaseGetParam?.pageSize ?: 20)

        setPageSort(toolBaseGetParam, basePage)

        basePage = this.page(
            basePage,
            KtQueryWrapper(ToolBase())
                .select(ToolBase::id)
                .`in`(
                    !toolBaseGetParam?.platform.isNullOrBlank(),
                    ToolBase::platform,
                    toolBaseGetParam?.platform?.split(",")
                )
        )

        if (basePage.total > 0) {
            basePage.records = baseMapper.selectListWithVersionByIds(basePage.records.map { it.id!! })
        }

        return basePage.toVo()
    }

    override fun getList(): List<ToolBaseVo> =
        baseMapper.selectListWithVersion().map(ToolBase::toVo)

    @Transactional
    override fun add(toolBaseAddParam: ToolBaseAddParam): ToolBaseVo {
        val toolBase = ToolBase().apply {
            name = toolBaseAddParam.name
            platform = toolBaseAddParam.platform
        }
        saveOrThrowException { this.save(toolBase) }

        val newNodeId = toolSourceService.generateEmptySource()
        val rToolBaseSource = RToolBaseData().apply {
            baseId = toolBase.id
            dataId = newNodeId
            dataType = RToolBaseData.DataType.SOURCE
        }
        saveOrThrowException { rToolBaseDataService.save(rToolBaseSource) }

        return toolBase.toVo()
    }

    @Transactional
    override fun update(toolBaseUpdateParam: ToolBaseUpdateParam) {
        val toolBase = toolBaseUpdateParam.toEntity()

        updateOrThrowException { this.updateById(toolBase) }
    }

    @Transactional
    override fun updateSourceAdd(id: Long, toolCommonUpdateSourceAddParam: ToolCommonUpdateSourceAddParam): String {
        val rToolBaseSource = checkAndGetRToolBaseSource(id)
        return toolSourceService.addNode(
            rootId = rToolBaseSource.dataId!!,
            parentId = toolCommonUpdateSourceAddParam.parentNode!!,
            fileName = toolCommonUpdateSourceAddParam.fileName!!,
            dirNode = toolCommonUpdateSourceAddParam.dirNode!!
        ).toString()
    }

    @Transactional
    override fun updateSourceRename(id: Long, nodeId: Long, fileName: String) {
        val rToolBaseSource = checkAndGetRToolBaseSource(id)
        toolSourceService.renameNode(
            rootId = rToolBaseSource.dataId!!,
            nodeId = nodeId,
            fileName = fileName,
        )
    }

    @Transactional
    override fun updateSourceMove(id: Long, nodeId: Long, newParentId: Long) {
        val rToolBaseSource = checkAndGetRToolBaseSource(id)
        toolSourceService.moveNode(
            rootId = rToolBaseSource.dataId!!,
            nodeId = nodeId,
            newParentId = newParentId
        )
    }

    @Transactional
    override fun updateSourceContent(id: Long, nodeId: Long, content: String) {
        val rToolBaseSource = checkAndGetRToolBaseSource(id)
        toolSourceService.updateNode(
            rootId = rToolBaseSource.dataId!!,
            nodeId = nodeId,
            content = content.toByteArray()
        )
    }

    @Transactional
    override fun updateSourceRemove(id: Long, nodeId: Long) {
        val rToolBaseSource = checkAndGetRToolBaseSource(id)
        toolSourceService.removeNode(
            rootId = rToolBaseSource.dataId!!,
            nodeId = nodeId
        )
    }

    @Transactional
    override fun updateDist(id: Long, dist: String): Long {
        queryOrThrowException { this.getById(id) }
        val rToolBaseSource = queryOrThrowException(ToolBaseHasBeenCompiledException()) {
            rToolBaseDataService.getOne(
                KtQueryWrapper(RToolBaseData())
                    .eq(RToolBaseData::baseId, id)
                    .eq(RToolBaseData::dataType, RToolBaseData.DataType.SOURCE)
                    .eq(RToolBaseData::baseVersion, 0L)
            )
        }
        val compiledBaseVersion = System.currentTimeMillis()
        updateOrThrowException {
            rToolBaseDataService.updateById(
                rToolBaseSource.apply {
                    baseVersion = compiledBaseVersion
                })
        }

        val newDistId = toolDistService.generateNewDist(dist)
        updateOrThrowException {
            rToolBaseDataService.save(RToolBaseData().apply {
                baseId = id
                dataId = newDistId
                dataType = RToolBaseData.DataType.DIST
                baseVersion = compiledBaseVersion
            })
        }

        updateOrThrowException {
            this.update(
                KtUpdateWrapper(ToolBase())
                    .eq(ToolBase::id, id)
                    .set(ToolBase::updateTime, LocalDateTime.now(ZoneOffset.UTC))
            )
        }

        return compiledBaseVersion
    }

    @Transactional
    override fun delete(id: Long): Boolean {
        rToolBaseDataService.remove(
            KtQueryWrapper(RToolBaseData())
                .eq(RToolBaseData::baseId, id)
        )

        // TODO: keep source & dist, add manual cleanup method later

        return this.removeById(id)
    }

    private fun checkAndGetRToolBaseSource(toolBaseId: Long): RToolBaseData {
        existsOrThrowException {
            this.exists(
                KtQueryWrapper(ToolBase())
                    .eq(ToolBase::id, toolBaseId)
            )
        }

        return queryOrThrowException(ToolBaseHasBeenCompiledException()) {
            rToolBaseDataService.getOne(
                KtQueryWrapper(RToolBaseData())
                    .eq(RToolBaseData::baseId, toolBaseId)
                    .eq(RToolBaseData::dataType, RToolBaseData.DataType.SOURCE)
                    .eq(RToolBaseData::baseVersion, 0L)
            )
        }
    }
}
