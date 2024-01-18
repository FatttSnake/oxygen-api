package top.fatweb.oxygen.api.service.tool

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.tool.ToolTemplate
import top.fatweb.oxygen.api.param.tool.ToolTemplateAddParam
import top.fatweb.oxygen.api.param.tool.ToolTemplateUpdateParam
import top.fatweb.oxygen.api.vo.tool.ToolTemplateVo

/**
 * Tool template service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IService
 * @see ToolTemplate
 */
interface IToolTemplateService : IService<ToolTemplate> {
    fun getOne(id: Long): ToolTemplateVo

    fun get(): List<ToolTemplateVo>

    fun add(toolTemplateAddParam: ToolTemplateAddParam): ToolTemplateVo

    fun update(toolTemplateUpdateParam: ToolTemplateUpdateParam): ToolTemplateVo

    fun delete(id: Long): Boolean
}