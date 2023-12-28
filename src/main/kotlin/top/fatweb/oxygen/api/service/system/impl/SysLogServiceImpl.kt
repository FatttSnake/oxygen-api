package top.fatweb.oxygen.api.service.system.impl

import com.baomidou.dynamic.datasource.annotation.DS
import com.baomidou.mybatisplus.core.metadata.OrderItem
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.converter.system.SysLogConverter
import top.fatweb.oxygen.api.entity.permission.User
import top.fatweb.oxygen.api.entity.system.SysLog
import top.fatweb.oxygen.api.mapper.system.SysLogMapper
import top.fatweb.oxygen.api.param.system.SysLogGetParam
import top.fatweb.oxygen.api.service.permission.IUserService
import top.fatweb.oxygen.api.service.system.ISysLogService
import top.fatweb.oxygen.api.util.PageUtil
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.system.SysLogVo

/**
 * System log service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see SysLogMapper
 * @see SysLog
 * @see ISysLogService
 */
@DS("sqlite")
@Service
class SysLogServiceImpl(
    private val userService: IUserService
) : ServiceImpl<SysLogMapper, SysLog>(), ISysLogService {
    override fun getPage(sysLogGetParam: SysLogGetParam?): PageVo<SysLogVo> {
        val sysLogPage = Page<SysLog>(sysLogGetParam?.currentPage ?: 1, sysLogGetParam?.pageSize ?: 20)

        PageUtil.setPageSort(sysLogGetParam, sysLogPage, OrderItem.desc("start_time"))

        val sysLogIPage = baseMapper.selectPage(
            sysLogPage,
            sysLogGetParam?.logType?.split(","),
            sysLogGetParam?.requestMethod?.split(","),
            sysLogGetParam?.searchRequestUrl,
            sysLogGetParam?.searchStartTime,
            sysLogGetParam?.searchEndTime
        )
        sysLogIPage.records.forEach {
            it.operateUsername =
                it.operateUserId?.let { it1 -> userService.getOne(it1)?.username }
        }
        if (sysLogIPage.records.isNotEmpty()) {
            val userIds = sysLogIPage.records.map { it.operateUserId }

            userService.list(KtQueryWrapper(User()).select(User::id, User::username).`in`(User::id, userIds))
                .forEach { user ->
                    sysLogIPage.records.forEach {
                        if (it.operateUserId == user.id) {
                            it.operateUsername = user.username
                        }
                    }
                }
        }

        return SysLogConverter.sysLogPageToSysLogPageVo(sysLogIPage)
    }
}
