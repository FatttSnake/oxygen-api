package top.fatweb.oxygen.api.mapper.tool

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import top.fatweb.oxygen.api.entity.tool.ToolTemplate

/**
 * Tool template mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see BaseMapper
 * @see ToolTemplate
 */
@Mapper
interface ToolTemplateMapper : BaseMapper<ToolTemplate> {
    /**
     * Select tool template by ID
     *
     * @param id Tool template ID
     * @return ToolTemplate object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolTemplate
     */
    fun selectOne(@Param("id") id: Long): ToolTemplate?

    /**
     * Select tool template in list
     *
     * @return List of ToolTemplate object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolTemplate
     */
    fun selectListWithBaseName(
        page: IPage<ToolTemplate>,
        @Param("platform") platform: List<String>?
    ): IPage<ToolTemplate>
}