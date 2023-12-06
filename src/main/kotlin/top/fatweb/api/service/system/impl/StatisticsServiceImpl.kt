package top.fatweb.api.service.system.impl

import org.springframework.stereotype.Service
import oshi.SystemInfo
import top.fatweb.api.properties.ServerProperties
import top.fatweb.api.service.system.IStatisticsService
import top.fatweb.api.vo.system.HardwareInfoVo
import top.fatweb.api.vo.system.SoftwareInfoVo
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Properties

@Service
class StatisticsServiceImpl : IStatisticsService {
    private val systemProperties: Properties = System.getProperties()
    private val systemInfo: SystemInfo = SystemInfo()

    override fun software(): SoftwareInfoVo = SoftwareInfoVo(
        os = systemInfo.operatingSystem.toString(),
        bitness = systemInfo.operatingSystem.bitness,
        javaVersion = systemProperties.getProperty("java.version"),
        javaVersionDate = systemProperties.getProperty("java.version.date"),
        javaVendor = systemProperties.getProperty("java.vendor"),
        javaRuntime = systemProperties.getProperty("java.runtime.name"),
        javaRuntimeVersion = systemProperties.getProperty("java.runtime.version"),
        jvm = systemProperties.getProperty("java.vm.name"),
        jvmVersion = systemProperties.getProperty("java.vm.version"),
        jvmInfo = systemProperties.getProperty("java.vm.info"),
        jvmVendor = systemProperties.getProperty("java.vm.vendor"),
        javaClassVersion = systemProperties.getProperty("java.class.version"),
        osBootTime = LocalDateTime.ofEpochSecond(systemInfo.operatingSystem.systemBootTime, 0, ZoneOffset.UTC),
        serverStartupTime = ServerProperties.startupTime
    )

    override fun hardware(): HardwareInfoVo = HardwareInfoVo(
        cpu = systemInfo.hardware.processor.processorIdentifier.name,
        arch = systemProperties.getProperty("os.arch"),
        is64Bit = systemInfo.hardware.processor.processorIdentifier.isCpu64bit,
        cpuPhysicalPackageCount = systemInfo.hardware.processor.physicalPackageCount,
        cpuPhysicalProcessorCount = systemInfo.hardware.processor.physicalProcessorCount,
        cpuLogicalProcessorCount = systemInfo.hardware.processor.logicalProcessorCount,
        microarchitecture = systemInfo.hardware.processor.processorIdentifier.microarchitecture
    )
}