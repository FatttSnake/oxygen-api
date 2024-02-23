package top.fatweb.oxygen.api.service.permission

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.permission.Role
import top.fatweb.oxygen.api.param.permission.role.*
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.permission.RoleWithPowerVo
import top.fatweb.oxygen.api.vo.permission.base.RoleVo

/**
 * Role service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IService
 * @see Role
 */
interface IRoleService : IService<Role> {
    /**
     * Get user by ID
     *
     * @param id ID
     * @return RoleWithPowerVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see RoleWithPowerVo
     */
    fun getOne(id: Long): RoleWithPowerVo

    /**
     * Get role in page
     *
     * @param roleGetParam Get role parameters
     * @return PageVo<RoleWithPowerVo> object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see RoleGetParam
     * @see PageVo
     * @see RoleWithPowerVo
     */
    fun getPage(roleGetParam: RoleGetParam?): PageVo<RoleWithPowerVo>

    /**
     * Get all role in list
     *
     * @return List of RoleVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see RoleVo
     */
    fun getList(): List<RoleVo>

    /**
     * Add role
     *
     * @param roleAddParam Add role parameters
     * @return RoleVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see RoleAddParam
     * @see RoleVo
     */
    fun add(roleAddParam: RoleAddParam): RoleVo

    /**
     * Update role
     *
     * @param roleUpdateParam Update role parameters
     * @return RoleVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see RoleUpdateParam
     * @see RoleVo
     */
    fun update(roleUpdateParam: RoleUpdateParam): RoleVo

    /**
     * Update status of role
     *
     * @param roleUpdateStatusParam Update status of role parameters
     * @return Result
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see RoleUpdateStatusParam
     */
    fun status(roleUpdateStatusParam: RoleUpdateStatusParam)

    /**
     * Delete role by ID
     *
     * @param id ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun deleteOne(id: Long)

    /**
     * Delete role by list
     *
     * @param roleDeleteParam Delete role parameters
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see RoleDeleteParam
     */
    fun delete(roleDeleteParam: RoleDeleteParam)
}
