package top.fatweb.oxygen.api.service.system

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.system.SensitiveWord
import top.fatweb.oxygen.api.param.system.SensitiveWordAddParam
import top.fatweb.oxygen.api.param.system.SensitiveWordUpdateParam
import top.fatweb.oxygen.api.vo.system.SensitiveWordVo

/**
 * Sensitive word service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
interface ISensitiveWordService : IService<SensitiveWord> {
    /**
     * Get sensitive word settings
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see SensitiveWordVo
     */
    fun get(): List<SensitiveWordVo>

    /**
     * Add sensitive word settings item
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see SensitiveWordAddParam
     */
    fun add(sensitiveWordAddParam: SensitiveWordAddParam)

    /**
     * Update sensitive word settings
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see SensitiveWordUpdateParam
     */
    fun update(sensitiveWordUpdateParam: SensitiveWordUpdateParam)

    /**
     * Delete sensitive word settings item
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun delete(id: Long)

    /**
     * Check sensitive word
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    fun checkSensitiveWord(str: String)
}