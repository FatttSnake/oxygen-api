package top.fatweb.api.mapper.system

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import org.apache.ibatis.annotations.Mapper
import top.fatweb.api.entity.system.SysLog
import java.time.LocalDateTime

/**
 * System log mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see BaseMapper
 * @see SysLog
 */
@Mapper
interface SysLogMapper : BaseMapper<SysLog> {
    /**
     * Select system log in page
     *
     * @param page Pagination
     * @param logType List of log types
     * @param requestMethod List of request methods
     * @param searchRequestUrl Request URL to search for
     * @param searchRegex Use regex
     * @param searchStartTime Start time to search for
     * @param searchEndTime end time to search for
     * @return System log in page
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see IPage
     * @see SysLog
     * @see LocalDateTime
     */
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
