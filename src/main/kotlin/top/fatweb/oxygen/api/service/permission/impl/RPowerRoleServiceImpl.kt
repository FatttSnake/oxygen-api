package top.fatweb.oxygen.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.entity.permission.RPowerRole
import top.fatweb.oxygen.api.mapper.permission.RPowerRoleMapper
import top.fatweb.oxygen.api.service.permission.IRPowerRoleService

/**
 * Power role intermediate service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see RPowerRoleMapper
 * @see RPowerRole
 * @see IRPowerRoleService
 */
@Service
class RPowerRoleServiceImpl : ServiceImpl<RPowerRoleMapper, RPowerRole>(), IRPowerRoleService
