package top.fatweb.api.vo.system

data class StorageInfoVo(
    val memoryTotal: Long,
    val memoryFree: Long,
    val virtualMemoryMax: Long,
    val virtualMemoryInUse: Long,
    val swapTotal: Long,
    val swapUsed: Long,
    val jvmTotal: Long,
    val jvmFree: Long,
    val fileStores: List<FileStoreInfoVo>
)
