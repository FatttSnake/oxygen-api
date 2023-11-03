package top.fatweb.api.mapper.system

import top.fatweb.api.entity.system.SysLog
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import org.apache.ibatis.annotations.Mapper

/**
 * <p>
 * 系统日志表 Mapper 接口
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-18
 */
@Mapper
interface SysLogMapper : BaseMapper<SysLog> {
    fun selectPage(page: IPage<SysLog>): IPage<SysLog>
}
