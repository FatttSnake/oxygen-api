package top.fatweb.api.cron

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import top.fatweb.api.entity.system.StatisticsLog
import top.fatweb.api.properties.SecurityProperties
import top.fatweb.api.service.system.IStatisticsLogService
import top.fatweb.api.util.RedisUtil

@Component
class StatisticsCron(
    private val redisUtil: RedisUtil,
    private val statisticsLogService: IStatisticsLogService
) {
    @Scheduled(cron = "0 * * * * *")
    fun onlineUserCount() {
        statisticsLogService.save(StatisticsLog().apply {
            key = StatisticsLog.KeyItem.ONLINE_USERS_COUNT
            value = redisUtil.keys("${SecurityProperties.jwtIssuer}_login_*")
                .distinctBy { Regex("FatWeb_login_(.*):.*").matchEntire(it)?.groupValues?.getOrNull(1) }.size.toString()
        })
    }
}