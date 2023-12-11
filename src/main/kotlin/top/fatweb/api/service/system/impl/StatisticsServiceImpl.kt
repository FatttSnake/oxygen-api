package top.fatweb.api.service.system.impl

import org.springframework.stereotype.Service
import oshi.SystemInfo
import oshi.hardware.CentralProcessor
import top.fatweb.api.properties.ServerProperties
import top.fatweb.api.service.system.IStatisticsService
import top.fatweb.api.util.ByteUtil
import top.fatweb.api.vo.system.*
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Statistics service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Service
class StatisticsServiceImpl : IStatisticsService {
    private val systemProperties: Properties = System.getProperties()
    private val systemInfo: SystemInfo = SystemInfo()
    private val runtime: Runtime = Runtime.getRuntime()

    override fun software() = SoftwareInfoVo(
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

    override fun hardware() = HardwareInfoVo(
        cpu = systemInfo.hardware.processor.processorIdentifier.name,
        arch = systemProperties.getProperty("os.arch"),
        is64Bit = systemInfo.hardware.processor.processorIdentifier.isCpu64bit,
        cpuPhysicalPackageCount = systemInfo.hardware.processor.physicalPackageCount,
        cpuPhysicalProcessorCount = systemInfo.hardware.processor.physicalProcessorCount,
        cpuLogicalProcessorCount = systemInfo.hardware.processor.logicalProcessorCount,
        microarchitecture = systemInfo.hardware.processor.processorIdentifier.microarchitecture,
        memories = "${ByteUtil.formatByteSize(systemInfo.hardware.memory.total)} (${
            systemInfo.hardware.memory.physicalMemory.joinToString(
                " + "
            ) { ByteUtil.formatByteSize(it.capacity) }
        })",
        disks = "${ByteUtil.formatByteSize(systemInfo.hardware.diskStores.sumOf { it.size })} (${
            systemInfo.hardware.diskStores.joinToString(
                " + "
            ) { ByteUtil.formatByteSize(it.size) }
        })"
    )

    override fun cpu(): CpuInfoVo {
        val processor = systemInfo.hardware.processor
        val prevTicks = processor.systemCpuLoadTicks
        val processorPrevTicksList = processor.processorCpuLoadTicks
        TimeUnit.MILLISECONDS.sleep(500)
        val ticks = processor.systemCpuLoadTicks
        val processorTicksList = processor.processorCpuLoadTicks

        val user = ticks[CentralProcessor.TickType.USER.index] - prevTicks[CentralProcessor.TickType.USER.index]
        val nice = ticks[CentralProcessor.TickType.NICE.index] - prevTicks[CentralProcessor.TickType.NICE.index]
        val system = ticks[CentralProcessor.TickType.SYSTEM.index] - prevTicks[CentralProcessor.TickType.SYSTEM.index]
        val idle = ticks[CentralProcessor.TickType.IDLE.index] - prevTicks[CentralProcessor.TickType.IDLE.index]
        val iowait = ticks[CentralProcessor.TickType.IOWAIT.index] - prevTicks[CentralProcessor.TickType.IOWAIT.index]
        val irq = ticks[CentralProcessor.TickType.IRQ.index] - prevTicks[CentralProcessor.TickType.IRQ.index]
        val softirq =
            ticks[CentralProcessor.TickType.SOFTIRQ.index] - prevTicks[CentralProcessor.TickType.SOFTIRQ.index]
        val steal = ticks[CentralProcessor.TickType.STEAL.index] - prevTicks[CentralProcessor.TickType.STEAL.index]
        val total = user + nice + system + idle + iowait + irq + softirq + steal
        return CpuInfoVo(user, nice, system, idle, iowait, irq, softirq, steal, total, mutableListOf()).apply {
            processorPrevTicksList.forEachIndexed { index, processorPrevTicks ->
                run {
                    val processorTicks = processorTicksList[index]
                    val processorUser =
                        processorTicks[CentralProcessor.TickType.USER.index] - processorPrevTicks[CentralProcessor.TickType.USER.index]
                    val processorNice =
                        processorTicks[CentralProcessor.TickType.NICE.index] - processorPrevTicks[CentralProcessor.TickType.NICE.index]
                    val processorSystem =
                        processorTicks[CentralProcessor.TickType.SYSTEM.index] - processorPrevTicks[CentralProcessor.TickType.SYSTEM.index]
                    val processorIdle =
                        processorTicks[CentralProcessor.TickType.IDLE.index] - processorPrevTicks[CentralProcessor.TickType.IDLE.index]
                    val processorIowait =
                        processorTicks[CentralProcessor.TickType.IOWAIT.index] - processorPrevTicks[CentralProcessor.TickType.IOWAIT.index]
                    val processorIrq =
                        processorTicks[CentralProcessor.TickType.IRQ.index] - processorPrevTicks[CentralProcessor.TickType.IRQ.index]
                    val processorSoftirq =
                        processorTicks[CentralProcessor.TickType.SOFTIRQ.index] - processorPrevTicks[CentralProcessor.TickType.SOFTIRQ.index]
                    val processorSteal =
                        processorTicks[CentralProcessor.TickType.STEAL.index] - processorPrevTicks[CentralProcessor.TickType.STEAL.index]
                    val processorTotal =
                        processorUser + processorNice + processorSystem + processorIdle + processorIowait + processorIrq + processorSoftirq + processorSteal
                    processors?.add(
                        CpuInfoVo(
                            processorUser,
                            processorNice,
                            processorSystem,
                            processorIdle,
                            processorIowait,
                            processorIrq,
                            processorSoftirq,
                            processorSteal,
                            processorTotal
                        )
                    )
                }
            }
        }
    }

    override fun storage() = StorageInfoVo(
        memoryTotal = systemInfo.hardware.memory.total,
        memoryFree = systemInfo.hardware.memory.available,
        virtualMemoryMax = systemInfo.hardware.memory.virtualMemory.virtualMax,
        virtualMemoryInUse = systemInfo.hardware.memory.virtualMemory.virtualInUse,
        swapTotal = systemInfo.hardware.memory.virtualMemory.swapTotal,
        swapUsed = systemInfo.hardware.memory.virtualMemory.swapUsed,
        jvmTotal = runtime.totalMemory(),
        jvmFree = runtime.freeMemory(),
        fileStores = systemInfo.operatingSystem.fileSystem.fileStores.map {
            FileStoreInfoVo(
                mount = it.mount,
                total = it.totalSpace,
                free = it.freeSpace
            )
        }
    )
}