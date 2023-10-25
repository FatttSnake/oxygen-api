package top.fatweb.api.mapper.permission

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import top.fatweb.api.entity.permission.Element

/**
 * <p>
 * 页面元素 Mapper 接口
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
@Mapper
interface ElementMapper : BaseMapper<Element>
