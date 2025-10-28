package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.entity.tool.RToolBaseData
import top.fatweb.oxygen.api.mapper.tool.RToolBaseDataMapper
import top.fatweb.oxygen.api.service.tool.IRToolBaseDataService

/**
 * Tool base data service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see ServiceImpl
 * @see RToolBaseDataMapper
 * @see RToolBaseData
 * @see IRToolBaseDataService
 */
@Service
class RToolBaseDataServiceImpl : ServiceImpl<RToolBaseDataMapper, RToolBaseData>(), IRToolBaseDataService
