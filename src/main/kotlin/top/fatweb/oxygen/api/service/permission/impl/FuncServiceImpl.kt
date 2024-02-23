package top.fatweb.oxygen.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.entity.permission.Func
import top.fatweb.oxygen.api.mapper.permission.FuncMapper
import top.fatweb.oxygen.api.service.permission.IFuncService

/**
 * Function service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see FuncMapper
 * @see Func
 * @see IFuncService
 */
@Service
class FuncServiceImpl : ServiceImpl<FuncMapper, Func>(), IFuncService
