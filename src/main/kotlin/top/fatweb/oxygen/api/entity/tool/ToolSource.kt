package top.fatweb.oxygen.api.entity.tool

import com.baomidou.mybatisplus.annotation.*
import com.baomidou.mybatisplus.core.toolkit.IdWorker
import top.fatweb.oxygen.api.exception.CorruptedSourceCodeException
import java.io.Serializable
import java.time.LocalDateTime

/**
 * Tool source entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.3.0
 */
@TableName("t_b_tool_source")
class ToolSource : Serializable {
    /**
     * ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @TableId("id")
    var id: Long? = null

    /**
     * Root node ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @TableField("root_id")
    var rootId: Long? = null

    /**
     * Parent node ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @TableField("parent_id")
    var parentId: Long? = null

    /**
     * File name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @TableField("file_name")
    var fileName: String? = null

    /**
     *  Is root node
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @TableField("root_node")
    var rootNode: Int? = null

    /**
     * Is directory node
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @TableField("dir_node")
    var dirNode: Int? = null

    /**
     * Create time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     * @see LocalDateTime
     */
    @TableField("create_time", fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null

    /**
     * Update time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     * @see LocalDateTime
     */
    @TableField("update_time", fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null

    /**
     * Version
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @TableField("version")
    @Version
    var version: Int? = null

    /**
     * Latest file version
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     * @see ToolFileVersion
     */
    @TableField(exist = false)
    var latestFileVersion: ToolFileVersion? = null

    override fun toString(): String {
        return "ToolSource(id=$id, rootId=$rootId, parentId=$parentId, fileName=$fileName, rootNode=$rootNode, dirNode=$dirNode, createTime=$createTime, updateTime=$updateTime, version=$version, latestFileVersion=$latestFileVersion)"
    }

    companion object {
        /**
         * Creates a deep copy of a tree structure of [ToolSource] nodes along with their associated file versions
         *
         * This function transforms a flat list of [ToolSource] nodes (representing a hierarchical file/directory structure)
         * into a new independent copy. It performs the following operations:
         * - Throws an exception if the input list is empty
         * - Validates that the list contains exactly one root node (where `rootNode == 1`)
         * - Recursively clones each node and its children
         * - Generates new unique IDs for each cloned node
         * - Maintains the hierarchical relationships by updating `rootId` and `parentId` references
         * - Creates new [ToolFileVersion] entries **only for file nodes** (`dirNode == 0`)
         * - Preserves file content metadata (hash, size) from the original's latest file version
         *
         * @return A [Triple] containing:
         *   - First: The newly generated ID of the cloned root node
         *   - Second: [List][ToolSource] of newly created source nodes with updated IDs and relationships
         *   - Third: [List][ToolFileVersion] of newly created file versions linked to the new nodes
         * @throws CorruptedSourceCodeException If the source tree is invalid:
         *   - When the input list is empty
         *   - When the list is non-empty but contains zero or more than one root node
         *   - When a file node (where `dirNode == 0`) lacks the latest file version
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.3.0
         * @see ToolSource
         * @see ToolFileVersion
         */
        fun List<ToolSource>.copy(): Triple<Long, List<ToolSource>, List<ToolFileVersion>> {
            if (this.isEmpty()) {
                throw CorruptedSourceCodeException()
            }

            val childrenMap = this.groupBy { it.parentId }
            val originalRoot = this.filter { it.rootNode == 1 }.run {
                if (size != 1) {
                    throw CorruptedSourceCodeException()
                }
                get(0)
            }
            val newSources = mutableListOf<ToolSource>()
            val newFileVersions = mutableListOf<ToolFileVersion>()

            fun cloneNodeAndChildren(
                originalNode: ToolSource,
                newRootId: Long?,
                newParentId: Long?
            ): Long {
                val originalFileVersion = originalNode.latestFileVersion
                if (originalNode.dirNode == 0 && originalFileVersion == null) {
                    throw CorruptedSourceCodeException()
                }

                val newNodeId = IdWorker.getId()
                val newNode = ToolSource().apply {
                    id = newNodeId
                    rootId = newRootId ?: newNodeId
                    parentId = newParentId
                    fileName = originalNode.fileName
                    rootNode = originalNode.rootNode
                    dirNode = originalNode.dirNode
                }
                newSources.add(newNode)
                if (originalNode.dirNode == 0 && originalFileVersion != null) {
                    val newFileVersion = ToolFileVersion().apply {
                        id = IdWorker.getId()
                        nodeId = newNodeId
                        ver = 0
                        fileHash = originalFileVersion.fileHash
                        fileSize = originalFileVersion.fileSize
                    }
                    newFileVersions.add(newFileVersion)
                }

                val children = childrenMap[originalNode.id] ?: emptyList()
                children.forEach { child ->
                    cloneNodeAndChildren(
                        originalNode = child,
                        newRootId = newRootId ?: newNodeId,
                        newParentId = newNodeId
                    )
                }

                return newNodeId
            }

            val newRootId = cloneNodeAndChildren(originalNode = originalRoot, newRootId = null, newParentId = null)

            return Triple(newRootId, newSources, newFileVersions)
        }
    }
}
