package top.fatweb.api.vo.system

data class FileStoreInfoVo(
    val mount: String,
    val total: Long,
    val free: Long
)
