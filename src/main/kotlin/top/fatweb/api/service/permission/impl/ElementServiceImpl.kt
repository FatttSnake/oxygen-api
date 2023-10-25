package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.Element
import top.fatweb.api.mapper.permission.ElementMapper
import top.fatweb.api.service.permission.IElementService

/**
 * <p>
 * 页面元素 服务实现类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
@Service
class ElementServiceImpl : ServiceImpl<ElementMapper, Element>(), IElementService
