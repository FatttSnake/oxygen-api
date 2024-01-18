package top.fatweb.oxygen.api.service.tool

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.param.tool.ToolAddParam
import top.fatweb.oxygen.api.param.tool.ToolUpdateParam
import top.fatweb.oxygen.api.vo.tool.ToolVo

/**
 * Tool service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IService
 * @see Tool
 */
interface IToolService : IService<Tool> {
    fun getOne(id: Long): ToolVo

    fun get(): List<ToolVo>

    fun add(toolAddParam: ToolAddParam): ToolVo

    fun update(toolUpdateParam: ToolUpdateParam): ToolVo

    fun delete(id: Long): Boolean
}