package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.Element
import top.fatweb.api.mapper.permission.ElementMapper
import top.fatweb.api.service.permission.IElementService

/**
 * Element service implement
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@Service
class ElementServiceImpl : ServiceImpl<ElementMapper, Element>(), IElementService
