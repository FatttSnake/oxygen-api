package top.fatweb.api.vo.system

data class HardwareInfoVo(
    val cpu: String,
    val arch: String,
    val is64Bit: Boolean,
    val cpuPhysicalPackageCount: Int,
    val cpuPhysicalProcessorCount: Int,
    val cpuLogicalProcessorCount: Int,
    val microarchitecture: String
)