package top.fatweb.oxygen.api.service.system.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.converter.system.SettingsConverter
import top.fatweb.oxygen.api.entity.system.SensitiveWord
import top.fatweb.oxygen.api.exception.MatchSensitiveWordException
import top.fatweb.oxygen.api.mapper.system.SensitiveWordMapper
import top.fatweb.oxygen.api.param.system.SensitiveWordAddParam
import top.fatweb.oxygen.api.param.system.SensitiveWordUpdateParam
import top.fatweb.oxygen.api.service.system.ISensitiveWordService
import top.fatweb.oxygen.api.vo.system.SensitiveWordVo

/**
 * Sensitive word service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Service
class SensitiveWordServiceImpl : ServiceImpl<SensitiveWordMapper, SensitiveWord>(), ISensitiveWordService {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun get(): List<SensitiveWordVo> = this.list().map(SettingsConverter::sensitiveWordToSensitiveWordVo)

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun add(sensitiveWordAddParam: SensitiveWordAddParam) {
        checkSensitiveWord(sensitiveWordAddParam.word!!)
        this.save(SettingsConverter.sensitiveWordAddParamToSensitiveWord(sensitiveWordAddParam))
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun update(sensitiveWordUpdateParam: SensitiveWordUpdateParam) {
        this.update(KtUpdateWrapper(SensitiveWord()).set(SensitiveWord::enable, false))
        this.update(
            KtUpdateWrapper(SensitiveWord()).`in`(SensitiveWord::id, sensitiveWordUpdateParam.ids)
                .set(SensitiveWord::enable, true)
        )
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun delete(id: Long) {
        this.removeById(id)
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun checkSensitiveWord(str: String) {
        this.list(KtQueryWrapper(SensitiveWord()).eq(SensitiveWord::enable, 1)).map(SensitiveWord::word).forEach {
            it ?: return@forEach

            try {
                if (Regex(it, RegexOption.IGNORE_CASE).matches(str)) {
                    throw MatchSensitiveWordException()
                }
            } catch (e: MatchSensitiveWordException) {
                throw e
            } catch (_: Exception) {
            }
        }
    }
}