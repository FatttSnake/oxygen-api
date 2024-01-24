package top.fatweb.oxygen.api.converter.system

import top.fatweb.oxygen.api.entity.system.SensitiveWord
import top.fatweb.oxygen.api.param.system.SensitiveWordAddParam
import top.fatweb.oxygen.api.vo.system.SensitiveWordVo

/**
 * Settings converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object SettingsConverter {
    /**
     * Convert SensitiveWord object into SensitiveWordVo object
     *
     * @param sensitiveWord SensitiveWord object
     * @return SensitiveWordVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see SensitiveWord
     * @see SensitiveWordVo
     */
    fun sensitiveWordToSensitiveWordVo(sensitiveWord: SensitiveWord) = SensitiveWordVo(
        id = sensitiveWord.id,
        word = sensitiveWord.word,
        useFor = sensitiveWord.useFor?.map(SensitiveWord.Use::valueOf)?.toSet(),
        enable = sensitiveWord.enable == 1
    )

    /**
     * Convert SensitiveWordAddParam object into SensitiveWord object
     *
     * @param sensitiveWordAddParam SensitiveWordAddParam object
     * @return SensitiveWord object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see SensitiveWordAddParam
     * @see SensitiveWord
     */
    fun sensitiveWordAddParamToSensitiveWord(sensitiveWordAddParam: SensitiveWordAddParam) = SensitiveWord().apply {
        word = sensitiveWordAddParam.word
        useFor = sensitiveWordAddParam.useFor.map(SensitiveWord.Use::code).toSet()
        enable = if (sensitiveWordAddParam.enable) 1 else 0
    }
}