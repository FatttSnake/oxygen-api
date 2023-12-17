package top.fatweb.api.settings

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.fasterxml.jackson.module.kotlin.readValue
import top.fatweb.api.util.StrUtil
import java.io.File
import java.io.IOException
import kotlin.reflect.KMutableProperty1

/**
 * Settings operator
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object SettingsOperator {
    private const val SETTINGS_FILE_NAME = "data/config/settings.yml"

    private val yamlMapper = YAMLMapper()
    private val settingFile = File(SETTINGS_FILE_NAME)
    private lateinit var systemSettings: SystemSettings

    init {
        getFromSettingsFile()
    }

    /**
     * Get settings from settings file
     *
     * @throws IOException
     * @throws MismatchedInputException
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Throws(IOException::class, MismatchedInputException::class)
    private fun getFromSettingsFile() {
        if (settingFile.isDirectory) {
            throw IOException("'${settingFile.absoluteFile}' is a directory, cannot create settings file")
        }

        if (!settingFile.isFile) {
            if (settingFile.parentFile.isFile) {
                throw IOException("'${settingFile.parentFile.absoluteFile}' is a file, cannot create settings file")
            }

            if (!settingFile.parentFile.isDirectory) {
                if (!settingFile.parentFile.mkdirs()) {
                    throw IOException("Cannot make directory: ${settingFile.parentFile.absolutePath}")
                }
            }
            settingFile.delete()
            settingFile.createNewFile()
        }

        if (settingFile.length() == 0L) {
            systemSettings = SystemSettings()
            saveSettingsToFile()
        }

        systemSettings = yamlMapper.readValue(settingFile)
    }

    /**
     * Save settings to settings file
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    private fun saveSettingsToFile() {
        yamlMapper.writeValue(settingFile, systemSettings)
    }

    /**
     * Set mail settings value
     *
     * @param field Field to set value. e.g. MailSettings::host
     * @param value Value to set
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see KMutableProperty1
     * @see MailSettings
     */
    fun <V> setMailValue(field: KMutableProperty1<MailSettings, V?>, value: V?) {
        systemSettings.mail?.let {
            if (field == MailSettings::password) {
                getMailValue(MailSettings::password)?.let { password ->
                    if (StrUtil.md5(password) == value) {
                        return
                    }
                }
            }
            field.set(it, value)
        } ?: {
            MailSettings().also {
                field.set(it, value)
                systemSettings.mail = it
            }
        }

        saveSettingsToFile()
    }

    /**
     * Get value from mail settings
     *
     * @param field Field to get value from. e.g. MailSettings::host
     * @return Value
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see KMutableProperty1
     * @see MailSettings
     */
    fun <V> getMailValue(field: KMutableProperty1<MailSettings, V?>): V? = systemSettings.mail?.let(field)

    /**
     * Get system settings object
     *
     * @return System settings object (Copy)
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see SystemSettings
     */
    fun settings(): SystemSettings = systemSettings.copy(
        mail = systemSettings.mail?.copy()
    ).apply {
        mail?.apply {
            password = password?.let {
                StrUtil.md5(it)
            }
        }
    }

    /**
     * Overwrite all settings
     *
     * @param systemSettings SystemSettings object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see SystemSettings
     */
    fun overwrite(systemSettings: SystemSettings) {
        this.systemSettings = systemSettings
        saveSettingsToFile()
    }
}