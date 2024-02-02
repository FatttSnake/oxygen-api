package top.fatweb.oxygen.api.mapper.tool

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import top.fatweb.oxygen.api.entity.tool.Tool

/**
 * Tool mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see BaseMapper
 * @see Tool
 */
@Mapper
interface ManagementMapper : BaseMapper<Tool> {
    fun selectOne(@Param("id") id: Long): Tool?

    fun selectPage(
        page: IPage<Long>,
        @Param("review") review: List<String>?,
        @Param("searchType") searchType: String,
        @Param("searchValue") searchValue: String?,
        @Param("searchRegex") searchRegex: Boolean
    ): IPage<Long>

    fun selectListByIds(@Param("ids") ids: List<Long>): List<Tool>
}