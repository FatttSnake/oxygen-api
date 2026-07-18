package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import okio.utf8Size
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.entity.tool.ToolDist
import top.fatweb.oxygen.api.mapper.tool.ToolDistMapper
import top.fatweb.oxygen.api.service.system.IStorageBlobService
import top.fatweb.oxygen.api.service.tool.IToolDistService
import top.fatweb.oxygen.api.util.queryOrThrowException
import top.fatweb.oxygen.api.util.updateOrThrowException

/**
 * Tool dist service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.3.0
 * @see ServiceImpl
 * @see ToolDistMapper
 * @see ToolDist
 * @see IToolDistService
 */
@Service
class ToolDistServiceImpl(
    private val storageBlobService: IStorageBlobService
) : ServiceImpl<ToolDistMapper, ToolDist>(), IToolDistService {
    @Transactional
    override fun generateNewDist(dist: String): Long {
        val hash = storageBlobService.saveFile(dist)
        val newDist = ToolDist().apply {
            fileHash = hash
            fileSize = dist.utf8Size()
        }
        updateOrThrowException { this.save(newDist) }

        return newDist.id!!
    }

    @Transactional
    override fun updateContent(id: Long, dist: String) {
        val toolDist = queryOrThrowException {
            this.getById(id)
        }

        storageBlobService.removeFile(toolDist.fileHash!!)
        val hash = storageBlobService.saveFile(dist)
        updateOrThrowException {
            this.update(
                KtUpdateWrapper(ToolDist())
                    .eq(ToolDist::id, id)
                    .set(ToolDist::fileHash, hash)
                    .set(ToolDist::fileSize, dist.utf8Size())
            )
        }
    }
}
