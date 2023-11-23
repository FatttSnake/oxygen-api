package top.fatweb.api.mapper.permission

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import top.fatweb.api.entity.permission.UserRole

/**
 * User role intermediate mapper
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Mapper
interface UserRoleMapper : BaseMapper<UserRole>
