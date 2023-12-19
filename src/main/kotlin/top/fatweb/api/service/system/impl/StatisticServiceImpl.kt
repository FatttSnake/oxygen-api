package top.fatweb.api.service.system.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import org.springframework.stereotype.Service
import oshi.SystemInfo
import oshi.hardware.CentralProcessor
import top.fatweb.api.entity.system.StatisticLog
import top.fatweb.api.param.system.OnlineInfoGetParam
import top.fatweb.api.properties.SecurityProperties
import top.fatweb.api.properties.ServerProperties
import top.fatweb.api.service.system.IStatisticLogService
import top.fatweb.api.service.system.IStatisticService
import top.fatweb.api.util.ByteUtil
import top.fatweb.api.util.RedisUtil
import top.fatweb.api.vo.system.*
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Statistic service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Service
class StatisticServiceImpl(
    private val redisUtil: RedisUtil,
    private val statisticLogService: IStatisticLogService
) : IStatisticService {
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
        return CpuInfoVo(
            user,
            nice,
            system,
            idle,
            iowait,
            irq,
            softirq,
            steal,
            total,
            listOf(*processorPrevTicksList.mapIndexed { index, processorPrevTicks ->
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

            }.toTypedArray())
        )
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

    override fun online(onlineInfoGetParam: OnlineInfoGetParam?): OnlineInfoVo {
        val history: List<OnlineInfoVo.HistoryVo> = statisticLogService.list(
            KtQueryWrapper(StatisticLog())
                .select(StatisticLog::value, StatisticLog::recordTime)
                .eq(StatisticLog::key, StatisticLog.KeyItem.ONLINE_USERS_COUNT)
                .between(
                    onlineInfoGetParam?.scope != OnlineInfoGetParam.Scope.ALL,
                    StatisticLog::recordTime,
                    LocalDateTime.now(ZoneOffset.UTC).run {
                        when (onlineInfoGetParam?.scope) {
                            OnlineInfoGetParam.Scope.DAY -> minusDays(1)
                            OnlineInfoGetParam.Scope.MONTH -> minusMonths(1)
                            OnlineInfoGetParam.Scope.QUARTER -> minusMonths(3)
                            OnlineInfoGetParam.Scope.YEAR -> minusYears(1)
                            OnlineInfoGetParam.Scope.TWO_YEARS -> minusYears(2)
                            OnlineInfoGetParam.Scope.THREE_YEARS -> minusYears(3)
                            OnlineInfoGetParam.Scope.FIVE_YEARS -> minusYears(5)
                            else -> minusWeeks(1)
                        }
                    },
                    LocalDateTime.now(ZoneOffset.UTC)
                )
        ).map {
            OnlineInfoVo.HistoryVo(
                time = it.recordTime!!,
                record = it.value!!
            )
        }

        return OnlineInfoVo(
            current = redisUtil.keys("${SecurityProperties.jwtIssuer}_login_*")
                .distinctBy { Regex("FatWeb_login_(.*):.*").matchEntire(it)?.groupValues?.getOrNull(1) }.size.toLong(),
            history = history
        )
    }
}