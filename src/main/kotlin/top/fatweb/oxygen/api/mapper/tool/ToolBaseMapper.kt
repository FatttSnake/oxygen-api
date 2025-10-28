package top.fatweb.oxygen.api.mapper.tool

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import top.fatweb.oxygen.api.entity.tool.ToolBase

/**
 * Tool base mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see BaseMapper
 * @see ToolBase
 */
@Mapper
interface ToolBaseMapper : BaseMapper<ToolBase> {
    /**
     * Select tool base by ID and version
     *
     * @param id Tool base ID
     * @param version Tool base version
     * @return ToolBase object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBase
     */
    fun selectOne(
        @Param("id") id: Long,
        @Param("version") version: Long
    ): ToolBase?

    /**
     * Select latest version tool base info by ID
     *
     * @param id Tool base ID
     * @return ToolBase object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ToolBase
     */
    fun selectLatestVersionInfo(
        @Param("id") id: Long
    ): ToolBase?

    /**
     * Select tool base dist by ID and version
     *
     * @param id Tool base ID
     * @param version Tool base version
     * @return ToolBase object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ToolBase
     */
    fun selectDist(
        @Param("id") id: Long,
        @Param("version") version: Long
    ): ToolBase?

    /**
     * Select tool base latest version by ID
     *
     * @param id Tool base ID
     * @return Version
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    fun selectLatestVersion(@Param("id") id: Long): Long?

    /**
     * Select tool base with version in list by IDs
     *
     * @param ids List of tool base IDs
     * @return List of tool base object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ToolBase
     */
    fun selectListWithVersionByIds(@Param("ids") ids: List<Long>): List<ToolBase>

    /**
     * Select tool base with version in list
     *
     * @return ToolBase in list
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ToolBase
     */
    fun selectListWithVersion(): List<ToolBase>
}
