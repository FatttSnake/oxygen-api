package top.fatweb.oxygen.api.cron

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import top.fatweb.oxygen.api.component.storage.RedisProvider
import top.fatweb.oxygen.api.entity.system.StatisticsLog
import top.fatweb.oxygen.api.properties.ServerProperties
import top.fatweb.oxygen.api.service.system.IStatisticsLogService

/**
 * Statistics scheduled tasks
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServerProperties
 * @see RedisProvider
 * @see IStatisticsLogService
 */
@Component
class StatisticsCron(
    private val serverProperties: ServerProperties,
    private val redisProvider: RedisProvider,
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
            value = redisProvider.keys("${serverProperties.security.tokenIssuer}_access_*")
                .distinctBy {
                    Regex("${serverProperties.security.tokenIssuer}_access_(.*?)_.*:.*").matchEntire(it)?.groupValues?.getOrNull(
                        1
                    )
                }.size.toString()
        })
    }
}
