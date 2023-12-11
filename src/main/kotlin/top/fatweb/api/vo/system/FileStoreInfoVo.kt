package top.fatweb.api.vo.system

/**
 * File storage information value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
data class FileStoreInfoVo(
    /**
     * Mount point of the File System. The
     * directory users will normally use to
     * interface with the file store.
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val mount: String,

    /**
     * Total space/capacity of the drive.
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val total: Long,

    /**
     * Free space on the drive. This space is
     * unallocated but may require elevated
     * permissions to write.
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val free: Long
)
