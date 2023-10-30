package top.fatweb.api.mapper.permission

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import top.fatweb.api.entity.permission.UserInfo

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-30
 */
@Mapper
interface UserInfoMapper : BaseMapper<UserInfo>
