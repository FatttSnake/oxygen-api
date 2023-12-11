package top.fatweb.api.service.system

import top.fatweb.api.vo.system.*

/**
 * Statistics service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
interface IStatisticsService {
    /**
     * Get software information
     *
     * @return SoftwareInfoVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see SoftwareInfoVo
     */
    fun software(): SoftwareInfoVo

    /**
     * Get hardware information
     *
     * @return HardwareInfoVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see HardwareInfoVo
     */
    fun hardware(): HardwareInfoVo

    /**
     * Get CPU information
     *
     * @return CpuInfoVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see CpuInfoVo
     */
    fun cpu(): CpuInfoVo

    /**
     * Get storage information
     *
     * @return StorageInfoVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see StorageInfoVo
     */
    fun storage(): StorageInfoVo
}