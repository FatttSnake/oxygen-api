package top.fatweb.api.vo.system

data class MemoryInfoVo(
    val total: Long,
    val free: Long,
    val virtualInUse: Long,
    val virtualMax: Long,
    val swapTotal: Long,
    val swapUsed: Long
)
