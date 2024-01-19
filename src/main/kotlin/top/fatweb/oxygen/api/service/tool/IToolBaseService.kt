package top.fatweb.oxygen.api.service.tool

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.tool.ToolBase
import top.fatweb.oxygen.api.param.tool.ToolBaseAddParam
import top.fatweb.oxygen.api.param.tool.ToolBaseUpdateParam
import top.fatweb.oxygen.api.vo.tool.ToolBaseVo

/**
 * Tool base service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IService
 * @see ToolBase
 */
interface IToolBaseService : IService<ToolBase> {
    fun getOne(id: Long): ToolBaseVo

    fun get(): List<ToolBaseVo>

    fun getList(): List<ToolBaseVo>

    fun add(toolBaseAddParam: ToolBaseAddParam): ToolBaseVo

    fun update(toolBaseUpdateParam: ToolBaseUpdateParam): ToolBaseVo

    fun delete(id: Long): Boolean
}