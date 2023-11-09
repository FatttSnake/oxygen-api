package top.fatweb.api.service.permission

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.api.entity.permission.Group
import top.fatweb.api.param.authentication.GroupGetParam

/**
 * <p>
 * 用户组表 服务类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
interface IGroupService : IService<Group> {
    fun getPage(groupGetParam: GroupGetParam?): IPage<Group>
}
