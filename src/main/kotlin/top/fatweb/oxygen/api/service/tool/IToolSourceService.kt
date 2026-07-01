package top.fatweb.oxygen.api.service.tool

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.tool.ToolSource

/**
 * Tool source service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.2.0
 * @see IService
 * @see ToolSource
 */
interface IToolSourceService : IService<ToolSource> {
    /**
     * Generate empty source
     *
     * @return Root node ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    fun generateEmptySource(): Long

    /**
     * Add file/directory node
     *
     * @param rootId Root node ID
     * @param parentId Parent node ID
     * @param fileName New file name
     * @param dirNode Is directory node
     * @return New node ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    fun addNode(rootId: Long, parentId: Long, fileName: String, dirNode: Boolean): Long

    /**
     * Rename node
     *
     * @param rootId Root node ID
     * @param nodeId Node ID
     * @param fileName New file name
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    fun renameNode(rootId: Long, nodeId: Long, fileName: String)

    /**
     * Move node
     *
     * @param rootId Root node ID
     * @param nodeId Node ID
     * @param newParentId New parent node ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    fun moveNode(rootId: Long, nodeId: Long, newParentId: Long)

    /**
     * Update node content
     *
     * @param rootId Root node ID
     * @param nodeId Node ID
     * @param content New content
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    fun updateNode(rootId: Long, nodeId: Long, content: ByteArray)

    /**
     * Remove node and descendant node(s)
     *
     * @param rootId Root node ID
     * @param nodeId Node ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    fun removeNode(rootId: Long, nodeId: Long)

    /**
     * Get descendant node(s)
     *
     * @param rootId Root node ID
     * @param nodeId Node ID
     * @return List of descendant node(s)
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    fun getDescendantNodeIds(rootId: Long, nodeId: Long): List<Long>
}
