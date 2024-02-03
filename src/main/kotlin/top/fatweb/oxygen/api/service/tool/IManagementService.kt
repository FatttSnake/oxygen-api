package top.fatweb.oxygen.api.service.tool

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.param.tool.ToolManagementGetParam
import top.fatweb.oxygen.api.param.tool.ToolManagementPassParam
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolVo

/**
 * Tool management service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IService
 * @see Tool
 */
interface IManagementService : IService<Tool> {
    fun getOne(id: Long): ToolVo

    fun getPage(toolManagementGetParam: ToolManagementGetParam?): PageVo<ToolVo>

    fun pass(id: Long, toolManagementPassParam: ToolManagementPassParam): ToolVo

    fun reject(id: Long): ToolVo

    fun offShelve(id: Long): ToolVo

    fun delete(id: Long): Boolean
}