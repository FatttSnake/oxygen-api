package top.fatweb.oxygen.api.vo.system

import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.oxygen.api.vo.system.OnlineInfoVo.HistoryVo
import java.time.LocalDateTime

/**
 * Online information value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "在线信息返回参数")
data class OnlineInfoVo(
    /**
     * Number of user currently online
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "当前在线用户数量")
    val current: Long,

    /**
     * Online number history
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see HistoryVo
     */
    @Schema(description = "历史记录")
    val history: List<HistoryVo>
) {
    data class HistoryVo(
        /**
         * Time
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.0.0
         * @see LocalDateTime
         */
        @Schema(description = "记录时间")
        val time: LocalDateTime,

        /**
         * Record
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.0.0
         */
        @Schema(description = "记录")
        val record: String
    )
}
