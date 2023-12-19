package top.fatweb.api.vo.system

import top.fatweb.api.vo.system.ActiveInfoVo.HistoryVo
import java.time.LocalDate

/**
 * Active information value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
data class ActiveInfoVo(
    /**
     * Register user number history
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val registerHistory: List<HistoryVo>,

    /**
     * Login user number history
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see HistoryVo
     */
    val loginHistory: List<HistoryVo>
) {
    data class HistoryVo(
        /**
         * Time
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.0.0
         * @see LocalDate
         */
        val time: LocalDate,

        /**
         * Count
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.0.0
         */
        val count: Int
    )
}
