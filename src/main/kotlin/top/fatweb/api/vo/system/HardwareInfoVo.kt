package top.fatweb.api.vo.system

/**
 * Hardware information value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
data class HardwareInfoVo(
    /**
     * Name of CPU
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val cpu: String,

    /**
     * Arch of CPU
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val arch: String,

    /**
     * Is CPU 64bit
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val is64Bit: Boolean,

    /**
     * Number of packages/sockets in the system. A
     * single package may contain multiple cores.
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val cpuPhysicalPackageCount: Int,

    /**
     * Number of physical CPUs/cores available for
     * processing.
     *
     * On some operating systems with variable numbers
     * of physical processors available to the OS, may
     * return a max value.
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val cpuPhysicalProcessorCount: Int,

    /**
     * Number of logical CPUs available for processing.
     * This value may be higher than physical CPUs if
     * hyperthreading is enabled.
     *
     * On some operating systems with variable numbers
     * of logical processors, may return a max value.
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val cpuLogicalProcessorCount: Int,

    /**
     * Processor's microarchitecture, if known.
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val microarchitecture: String,

    /**
     * Memory information overview
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val memories: String,

    /**
     * Disk information overview
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val disks: String
)