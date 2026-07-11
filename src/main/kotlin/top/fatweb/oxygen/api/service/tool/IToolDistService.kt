package top.fatweb.oxygen.api.service.tool

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.tool.ToolDist

/**
 * Tool dist service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.3.0
 * @see IService
 * @see ToolDist
 */
interface IToolDistService : IService<ToolDist> {
    /**
     * Generate new dist record
     *
     * @param dist Dist
     * @return Dist record ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    fun generateNewDist(dist: String): Long

    /**
     * Update dist content
     *
     * @param id Dist record ID
     * @param dist content
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    fun updateContent(id: Long, dist: String)
}
