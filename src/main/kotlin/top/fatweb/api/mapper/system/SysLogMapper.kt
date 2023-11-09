package top.fatweb.api.mapper.system

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import org.apache.ibatis.annotations.Mapper
import top.fatweb.api.entity.system.SysLog
import java.time.LocalDateTime

/**
 * <p>
 * 系统日志表 Mapper 接口
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-18
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
