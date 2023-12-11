package top.fatweb.api.vo.system

import java.time.LocalDateTime

/**
 * Software information value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
data class SoftwareInfoVo(
    /**
     * Operating system
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val os: String,

    /**
     * Bitness (32 or 64) of the operating system.
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val bitness: Int,

    /**
     * Version of Java
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val javaVersion: String,

    /**
     * Version date of Java
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val javaVersionDate: String,

    /**
     * Vendor of Java
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val javaVendor: String,

    /**
     * Name of Java runtime
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val javaRuntime: String,

    /**
     * Version of Java runtime
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val javaRuntimeVersion: String,

    /**
     * Name of Java virtual machine
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val jvm: String,

    /**
     * Version of Java virtual machine
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val jvmVersion: String,

    /**
     * Version of Java Virtual machine
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val jvmInfo: String,

    /**
     * Vendor of Java Virtual machine
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val jvmVendor: String,

    /**
     * Version of Java class
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val javaClassVersion: String,

    /**
     * Boot time of operating system
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val osBootTime: LocalDateTime,

    /**
     * Startup time of server
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val serverStartupTime: LocalDateTime
)
