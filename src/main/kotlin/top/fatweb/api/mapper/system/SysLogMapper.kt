package top.fatweb.api.mapper.system

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import org.apache.ibatis.annotations.Mapper
import top.fatweb.api.entity.system.SysLog
import java.time.LocalDateTime

/**
 * System log mapper
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@Mapper
interface SysLogMapper : BaseMapper<SysLog> {
    fun selectPage(
        page: IPage<SysLog>,
        logType: List<String>?,
        requestMethod: List<String>?,
        searchRequestUrl: String?,
        searchRegex: Boolean,
        searchStartTime: LocalDateTime?,
        searchEndTime: LocalDateTime?
    ): IPage<SysLog>
}
