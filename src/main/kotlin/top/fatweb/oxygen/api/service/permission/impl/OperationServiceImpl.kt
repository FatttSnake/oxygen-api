package top.fatweb.oxygen.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.entity.permission.Operation
import top.fatweb.oxygen.api.mapper.permission.OperationMapper
import top.fatweb.oxygen.api.service.permission.IOperationService

/**
 * Operation service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see OperationMapper
 * @see Operation
 * @see IOperationService
 */
@Service
class OperationServiceImpl : ServiceImpl<OperationMapper, Operation>(), IOperationService
