package top.fatweb.oxygen.api.cron

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import top.fatweb.oxygen.api.entity.system.StatisticsLog
import top.fatweb.oxygen.api.properties.SecurityProperties
import top.fatweb.oxygen.api.service.system.IStatisticsLogService
import top.fatweb.oxygen.api.util.RedisUtil

/**
 * Statistics scheduled tasks
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Component
class StatisticsCron(
    private val redisUtil: RedisUtil,
    private val statisticsLogService: IStatisticsLogService
) {
    /**
     * Auto record number of online users
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Scheduled(cron = "0 * * * * *")
    fun onlineUserCount() {
        statisticsLogService.save(StatisticsLog().apply {
            key = StatisticsLog.KeyItem.ONLINE_USERS_COUNT
            value = redisUtil.keys("${SecurityProperties.tokenIssuer}_access_*")
                .distinctBy { Regex("${SecurityProperties.tokenIssuer}_access_(.*?)_.*:.*").matchEntire(it)?.groupValues?.getOrNull(1) }.size.toString()
        })
    }
}