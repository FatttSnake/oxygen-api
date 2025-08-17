package top.fatweb.oxygen.api.entity.tool

import java.io.Serializable

/**
 * Tool identifier
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 */
class ToolIdentifier : Serializable {
    /**
     * Author user ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    var authorId: Long? = null

    /**
     * Tool ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    var toolId: String? = null
}
