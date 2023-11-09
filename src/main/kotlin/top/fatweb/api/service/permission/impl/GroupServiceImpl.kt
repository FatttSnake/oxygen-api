package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.Group
import top.fatweb.api.mapper.permission.GroupMapper
import top.fatweb.api.param.authentication.GroupGetParam
import top.fatweb.api.service.permission.IGroupService
import top.fatweb.api.util.PageUtil

/**
 * <p>
 * 用户组表 服务实现类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
@Service
class GroupServiceImpl : ServiceImpl<GroupMapper, Group>(), IGroupService {
    override fun getPage(groupGetParam: GroupGetParam?): IPage<Group> {
        val groupPage = Page<Group>(groupGetParam?.currentPage ?: 1, groupGetParam?.pageSize ?: 20)

        PageUtil.setPageSort(groupGetParam, groupPage)

        return baseMapper.selectPage(groupPage)
    }
}
