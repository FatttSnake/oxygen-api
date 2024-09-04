package top.fatweb.oxygen.api.settings

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.fasterxml.jackson.module.kotlin.readValue
import top.fatweb.oxygen.api.util.StrUtil
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
     * Set base settings value
     *
     * @param field Field to set value. e.g. BaseSettings::appName
     * @param value Value to set
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see KMutableProperty1
     * @see BaseSettings
     */
    fun <V> setAppValue(field: KMutableProperty1<BaseSettings, V?>, value: V?) {
        systemSettings.base?.let {
            field.set(it, value)
        } ?: let {
            systemSettings.base = BaseSettings().also { field.set(it, value) }
        }

        saveSettingsToFile()
    }

    /**
     * Get base settings value
     *
     * @param field Field to get value from. e.g. BaseSettings::appName
     * @return Value
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see KMutableProperty1
     * @see BaseSettings
     */
    fun <V> getAppValue(field: KMutableProperty1<BaseSettings, V?>): V? =
        this.getAppValue(field, null)

    /**
     * Get base settings value with default value
     *
     * @param field Field to get value from. e.g. BaseSettings::appName
     * @param default Return default value when setting not found
     * @return Value
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see KMutableProperty1
     * @see BaseSettings
     */
    fun <V> getAppValue(field: KMutableProperty1<BaseSettings, V?>, default: V): V =
        systemSettings.base?.let(field) ?: default

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
        } ?: let {
            systemSettings.mail = MailSettings().also { field.set(it, value) }
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
    fun <V> getMailValue(field: KMutableProperty1<MailSettings, V?>): V? =
        this.getMailValue(field, null)

    /**
     * Get value from mail settings with default value
     *
     * @param field Field to get value from. e.g. MailSettings::host
     * @param default Return default value when setting not found
     * @return Value
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see KMutableProperty1
     * @see MailSettings
     */
    fun <V> getMailValue(field: KMutableProperty1<MailSettings, V?>, default: V): V =
        systemSettings.mail?.let(field) ?: default

    /**
     * Set two-factor settings value
     *
     * @param field Field to set value. e.g. TwoFactorSettings::type
     * @param value Value to set
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see KMutableProperty1
     * @see TwoFactorSettings
     */
    fun <V> setTwoFactorValue(field: KMutableProperty1<TwoFactorSettings, V?>, value: V?) {
        systemSettings.twoFactor?.let {
            field.set(it, value)
        } ?: let {
            systemSettings.twoFactor = TwoFactorSettings().also { field.set(it, value) }
        }

        saveSettingsToFile()
    }

    /**
     * Get value from two-factor settings
     *
     * @param field Field to get value from. e.g. TwoFactorSettings::type
     * @return Value
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see KMutableProperty1
     * @see TwoFactorSettings
     */
    fun <V> getTwoFactorValue(field: KMutableProperty1<TwoFactorSettings, V?>): V? =
        this.getTwoFactorValue(field, null)

    /**
     * Get value from two-factor settings with default value
     *
     * @param field Field to get value from. e.g. TwoFactorSettings::type
     * @param default Return default value when setting not found
     * @return Value
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see KMutableProperty1
     * @see TwoFactorSettings
     */
    fun <V> getTwoFactorValue(field: KMutableProperty1<TwoFactorSettings, V?>, default: V): V =
        systemSettings.twoFactor?.let(field) ?: default
}