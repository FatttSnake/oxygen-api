package top.fatweb.api.vo.system

import java.time.LocalDateTime

/**
 * Online information value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
data class OnlineInfoVo(
    /**
     * Number of user currently online
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val current: Long,

    /**
     * Online number history
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see HistoryVo
     */
    val history: List<HistoryVo>
) {
    data class HistoryVo (
        /**
         * Time
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.0.0
         * @see LocalDateTime
         */
        val time: LocalDateTime,

        /**
         * Record
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.0.0
         */
        val record: String
    )
}
