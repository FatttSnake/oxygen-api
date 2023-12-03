package top.fatweb.api.setting

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File
import java.io.IOException

object SettingOperator {
    const val SETTING_FILE_NAME = "data/config/setting.yaml"
    private val yamlMapper = YAMLMapper()

    var systemSetting: SystemSetting? = null

    fun init() {

    }

    fun getFromSettingFile(): SystemSetting {
        val settingFile = File(SETTING_FILE_NAME)
        if (!settingFile.isFile) {
            if (!settingFile.parentFile.isDirectory) {
                if (!settingFile.parentFile.mkdirs()) {
                    throw IOException("Can not make directory: ${settingFile.parentFile.absolutePath}")
                }
            }
            settingFile.delete()
            settingFile.createNewFile()
        }
        return yamlMapper.readValue<SystemSetting>(settingFile)
    }
}