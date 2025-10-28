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
import top.fatweb.oxygen.api.entity.tool.ToolData
import top.fatweb.oxygen.api.exception.ToolBaseHasBeenCompiledException
import top.fatweb.oxygen.api.mapper.tool.ToolBaseMapper
import top.fatweb.oxygen.api.param.tool.*
import top.fatweb.oxygen.api.service.tool.IRToolBaseDataService
import top.fatweb.oxygen.api.service.tool.IToolBaseService
import top.fatweb.oxygen.api.service.tool.IToolDataService
import top.fatweb.oxygen.api.util.queryOrThrowException
import top.fatweb.oxygen.api.util.saveOrThrowException
import top.fatweb.oxygen.api.util.setPageSort
import top.fatweb.oxygen.api.util.updateOrThrowException
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
 * @see IRToolBaseDataService
 * @see IToolDataService
 * @see ServiceImpl
 * @see ToolBaseMapper
 * @see ToolBase
 * @see IToolBaseService
 */
@Service
class ToolBaseServiceImpl(
    private val rToolBaseDataService: IRToolBaseDataService,
    private val toolDataService: IToolDataService
) : ServiceImpl<ToolBaseMapper, ToolBase>(), IToolBaseService {
    @Transactional
    override fun getOne(id: Long, version: Long): ToolBaseWithSourceVo =
        queryOrThrowException {
            baseMapper.selectOne(id = id, version = version)?.let {
                if (it.source !== null) {
                    return@let it
                }
                if (version != 0L) {
                    return@let null
                }
                val latestVersion = queryOrThrowException { baseMapper.selectLatestVersionInfo(id) }
                val newSource = ToolData().apply { data = latestVersion.source!!.data }
                saveOrThrowException { toolDataService.save(newSource) }

                val rToolBaseSource = RToolBaseData().apply {
                    baseId = id
                    dataId = newSource.id
                    dataType = RToolBaseData.DataType.SOURCE
                }
                saveOrThrowException { rToolBaseDataService.save(rToolBaseSource) }

                baseMapper.selectOne(id = id, version = version)
            }
        }.let(ToolBase::toVoWithSource)

    override fun getDist(id: Long, version: Long): ToolBaseWithDistVo =
        queryOrThrowException {
            baseMapper.selectDist(id = id, version = version)
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
        val newSource = ToolData().apply { data = "" }
        saveOrThrowException { toolDataService.save(newSource) }

        val toolBase = ToolBase().apply {
            name = toolBaseAddParam.name
            platform = toolBaseAddParam.platform
            source = newSource
        }
        saveOrThrowException { this.save(toolBase) }

        val rToolBaseSource = RToolBaseData().apply {
            baseId = toolBase.id
            dataId = newSource.id
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
    override fun updateSource(toolBaseUpdateSourceParam: ToolBaseUpdateSourceParam) {
        queryOrThrowException { this.getById(toolBaseUpdateSourceParam.id) }
        val rToolBaseSource = queryOrThrowException(ToolBaseHasBeenCompiledException()) {
            rToolBaseDataService.getOne(
                KtQueryWrapper(RToolBaseData())
                    .eq(RToolBaseData::baseId, toolBaseUpdateSourceParam.id)
                    .eq(RToolBaseData::dataType, RToolBaseData.DataType.SOURCE)
                    .eq(RToolBaseData::baseVersion, 0L)
            )
        }

        updateOrThrowException {
            toolDataService.update(
                KtUpdateWrapper(ToolData())
                    .eq(ToolData::id, rToolBaseSource.dataId)
                    .set(ToolData::data, toolBaseUpdateSourceParam.source)
            )
        }
    }

    @Transactional
    override fun updateDist(toolBaseUpdateDistParam: ToolBaseUpdateDistParam): Long {
        queryOrThrowException { this.getById(toolBaseUpdateDistParam.id) }
        val rToolBaseSource = queryOrThrowException(ToolBaseHasBeenCompiledException()) {
            rToolBaseDataService.getOne(
                KtQueryWrapper(RToolBaseData())
                    .eq(RToolBaseData::baseId, toolBaseUpdateDistParam.id)
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

        val newDist = ToolData().apply { data = toolBaseUpdateDistParam.dist }
        updateOrThrowException { toolDataService.save(newDist) }
        updateOrThrowException {
            rToolBaseDataService.save(RToolBaseData().apply {
                baseId = toolBaseUpdateDistParam.id
                dataId = newDist.id
                dataType = RToolBaseData.DataType.DIST
                baseVersion = compiledBaseVersion
            })
        }

        updateOrThrowException {
            this.update(
                KtUpdateWrapper(ToolBase())
                    .eq(ToolBase::id, toolBaseUpdateDistParam.id)
                    .set(ToolBase::updateTime, LocalDateTime.now(ZoneOffset.UTC))
            )
        }

        return compiledBaseVersion
    }

    @Transactional
    override fun delete(id: Long): Boolean {
        val rToolBaseDataList = rToolBaseDataService.list(
            KtQueryWrapper(RToolBaseData())
                .eq(RToolBaseData::baseId, id)
        )
        toolDataService.removeBatchByIds(rToolBaseDataList.map { it.dataId })
        rToolBaseDataService.removeBatchByIds(rToolBaseDataList.map { it.id })

        return this.removeById(id)
    }
}
