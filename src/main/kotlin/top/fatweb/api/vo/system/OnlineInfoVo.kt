package top.fatweb.api.vo.system

import java.time.LocalDateTime

data class OnlineInfoVo(
    val current: Long,
    val history: List<HistoryVo>
) {
    data class HistoryVo (
        val time: LocalDateTime,
        val record: String
    )
}
