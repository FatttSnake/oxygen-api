package top.fatweb.api.mapper.permission

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import top.fatweb.api.entity.permission.RoleGroup

/**
 * <p>
 * 中间表-角色-用户组 Mapper 接口
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
@Mapper
interface RoleGroupMapper : BaseMapper<RoleGroup>
