package top.fatweb.api.vo.system

import java.time.LocalDateTime

data class SoftwareInfoVo(
    val os: String,
    val bitness: Int,
    val javaVersion: String,
    val javaVersionDate: String,
    val javaVendor: String,
    val javaRuntime: String,
    val javaRuntimeVersion: String,
    val jvm: String,
    val jvmVersion: String,
    val jvmInfo: String,
    val jvmVendor: String,
    val javaClassVersion: String,
    val osBootTime: LocalDateTime,
    val serverStartupTime: LocalDateTime
)
