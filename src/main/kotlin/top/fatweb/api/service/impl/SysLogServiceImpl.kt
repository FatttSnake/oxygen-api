package top.fatweb.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.SysLog
import top.fatweb.api.mapper.SysLogMapper
import top.fatweb.api.service.ISysLogService

/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-18
 */
@Service
class SysLogServiceImpl : ServiceImpl<SysLogMapper, SysLog>(), ISysLogService
