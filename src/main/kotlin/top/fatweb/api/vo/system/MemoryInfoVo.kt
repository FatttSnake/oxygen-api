package top.fatweb.api.vo.system

data class MemoryInfoVo(
    val total: Long,
    val free: Long,
    val virtualMax: Long,
    val virtualInUse: Long,
    val swapTotal: Long,
    val swapUsed: Long,
    val jvmTotal: Long,
    val jvmFree: Long,
)
