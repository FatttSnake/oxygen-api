package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.core.toolkit.IdWorker
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.entity.tool.ToolFileVersion
import top.fatweb.oxygen.api.entity.tool.ToolSource
import top.fatweb.oxygen.api.mapper.tool.ToolSourceMapper
import top.fatweb.oxygen.api.service.system.IStorageBlobService
import top.fatweb.oxygen.api.service.tool.IToolFileVersionService
import top.fatweb.oxygen.api.service.tool.IToolSourceService
import top.fatweb.oxygen.api.util.*

/**
 * Tool source service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.3.0
 * @see ServiceImpl
 * @see ToolSourceMapper
 * @see ToolSource
 * @see IToolSourceService
 */
@Service
class ToolSourceServiceImpl(
    private val storageBlobService: IStorageBlobService,
    private val toolFileVersionService: IToolFileVersionService
) : ServiceImpl<ToolSourceMapper, ToolSource>(), IToolSourceService {
    @Transactional
    override fun generateEmptySource(): Long {
        val newNodeId = IdWorker.getId()
        val rootNode = ToolSource().apply {
            id = newNodeId
            rootId = newNodeId
            fileName = ""
            rootNode = 1
            dirNode = 1
        }
        saveOrThrowException { save(rootNode) }

        return newNodeId
    }

    @Transactional
    override fun addNode(
        rootId: Long,
        parentId: Long,
        fileName: String,
        dirNode: Boolean
    ): Long {
        val exists = getOne(
            KtQueryWrapper(ToolSource())
                .eq(ToolSource::rootId, rootId)
                .eq(ToolSource::parentId, parentId)
                .eq(ToolSource::fileName, fileName)
                .eq(ToolSource::dirNode, if (dirNode) 1 else 0)
        )
        if (exists != null) {
            return exists.id!!
        }

        queryOrThrowException {
            getOne(
                KtQueryWrapper(ToolSource())
                    .eq(ToolSource::id, parentId)
                    .eq(ToolSource::rootId, rootId)
                    .eq(ToolSource::dirNode, 1)
            )
        }
        val newNodeId = IdWorker.getId()
        if (!dirNode) {
            val hash = storageBlobService.saveFile("")
            toolFileVersionService.save(
                ToolFileVersion().apply {
                    nodeId = newNodeId
                    ver = 0
                    fileHash = hash
                    fileSize = 0L
                }
            )
        }
        save(
            ToolSource().apply {
                this.id = newNodeId
                this.rootId = rootId
                this.parentId = parentId
                this.fileName = fileName
                this.rootNode = 0
                this.dirNode = if (dirNode) 1 else 0
            }
        )

        return newNodeId
    }

    @Transactional
    override fun renameNode(rootId: Long, nodeId: Long, fileName: String) {
        updateOrThrowException {
            update(
                KtUpdateWrapper(ToolSource())
                    .eq(ToolSource::id, nodeId)
                    .eq(ToolSource::rootId, rootId)
                    .ne(ToolSource::rootNode, 1)
                    .set(ToolSource::fileName, fileName)
            )
        }
    }

    @Transactional
    override fun moveNode(rootId: Long, nodeId: Long, newParentId: Long) {
        existsOrThrowException {
            exists(
                KtQueryWrapper(ToolSource())
                    .eq(ToolSource::id, newParentId)
                    .eq(ToolSource::rootId, rootId)
                    .eq(ToolSource::dirNode, 1)
            )
        }
        updateOrThrowException {
            update(
                KtUpdateWrapper(ToolSource())
                    .eq(ToolSource::id, nodeId)
                    .eq(ToolSource::rootId, rootId)
                    .ne(ToolSource::rootNode, 1)
                    .set(ToolSource::parentId, newParentId)
            )
        }
    }

    @Transactional
    override fun updateNode(rootId: Long, nodeId: Long, content: ByteArray) {
        existsOrThrowException {
            exists(
                KtQueryWrapper(ToolSource())
                    .eq(ToolSource::id, nodeId)
                    .eq(ToolSource::rootId, rootId)
                    .eq(ToolSource::dirNode, 0)
            )
        }
        val latestVersion = queryOrThrowException {
            toolFileVersionService.getOne(
                KtQueryWrapper(ToolFileVersion())
                    .eq(ToolFileVersion::nodeId, nodeId)
                    .orderByDesc(ToolFileVersion::ver)
                    .last("limit 1")
            )
        }
        if (content.sha256HexString() == latestVersion.fileHash) {
            return
        }

        val hash = storageBlobService.saveFile(content)
        saveOrThrowException {
            toolFileVersionService.save(
                ToolFileVersion().apply {
                    this.nodeId = nodeId
                    this.ver = latestVersion.ver!! + 1
                    this.fileHash = hash
                    this.fileSize = content.size.toLong()
                }
            )
        }
    }

    @Transactional
    override fun removeNode(rootId: Long, nodeId: Long) {
        val node = getOne(
            KtQueryWrapper(ToolSource())
                .eq(ToolSource::id, nodeId)
                .eq(ToolSource::rootId, rootId)
                .ne(ToolSource::rootNode, 1)
        ) ?: return

        if (node.dirNode == 0) {
            removeById(nodeId)
            toolFileVersionService.remove(
                KtQueryWrapper(ToolFileVersion())
                    .eq(ToolFileVersion::nodeId, nodeId)
            )

            return
        }

        val nodeIds = getDescendantNodeIds(rootId = rootId, nodeId = nodeId)
        if (nodeIds.isNotEmpty()) {
            toolFileVersionService.remove(
                KtQueryWrapper(ToolFileVersion())
                    .`in`(ToolFileVersion::nodeId, nodeIds)
            )
        }
        removeBatchByIds(nodeIds)
    }

    override fun getDescendantNodeIds(rootId: Long, nodeId: Long): List<Long> =
        baseMapper.getDescendantNodeIds(rootId = rootId, nodeId = nodeId)
}
