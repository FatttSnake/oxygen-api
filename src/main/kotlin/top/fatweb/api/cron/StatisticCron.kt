package top.fatweb.api.cron

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import top.fatweb.api.entity.system.StatisticLog
import top.fatweb.api.properties.SecurityProperties
import top.fatweb.api.service.system.IStatisticLogService
import top.fatweb.api.util.RedisUtil

@Component
class StatisticCron(
    private val redisUtil: RedisUtil,
    private val statisticLogService: IStatisticLogService
) {
    @Scheduled(cron = "0 * * * * *")
    fun onlineUserCount() {
        statisticLogService.save(StatisticLog().apply {
            key = StatisticLog.KeyItem.ONLINE_USERS_COUNT
            value = redisUtil.keys("${SecurityProperties.jwtIssuer}_login_*")
                .distinctBy { Regex("FatWeb_login_(.*):.*").matchEntire(it)?.groupValues?.getOrNull(1) }.size.toString()
        })
    }
}