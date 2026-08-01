package top.fatweb.oxygen.api.controller

import org.springframework.web.bind.annotation.GetMapping
import top.fatweb.oxygen.api.annotation.HiddenController
import top.fatweb.oxygen.api.service.system.ISettingsService
import top.fatweb.oxygen.api.vo.metadata.ConfigVo
import top.fatweb.oxygen.api.vo.metadata.HealthVo
import java.time.LocalDateTime

/**
 * Metadata controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.3.0
 */
@HiddenController
class MetadataController(
    private val settingsService: ISettingsService
) {
    @GetMapping("/health")
    fun health() =
        HealthVo(
            status = "UP",
            timestamp = LocalDateTime.now()
        )

    @GetMapping("/config")
    fun config() =
        ConfigVo.fromBaseSettingsVo(settingsService.getBase())
}
