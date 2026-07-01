package top.fatweb.oxygen.api.mapper.system

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import top.fatweb.oxygen.api.entity.system.StorageBlob

/**
 * Storage blob mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.2.0
 * @see BaseMapper
 * @see StorageBlob
 */
@Mapper
interface StorageBlobMapper : BaseMapper<StorageBlob> {
    /**
     * Increase file reference count
     *
     * @param fileHash File SHA-256 key
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    fun increaseReferenceCount(@Param("fileHash") fileHash: String)
}
