package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.core.metadata.OrderItem
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.api.converter.permission.UserConverter
import top.fatweb.api.entity.permission.User
import top.fatweb.api.entity.permission.UserGroup
import top.fatweb.api.entity.permission.UserInfo
import top.fatweb.api.entity.permission.UserRole
import top.fatweb.api.exception.NoRecordFoundException
import top.fatweb.api.mapper.permission.UserMapper
import top.fatweb.api.param.permission.user.*
import top.fatweb.api.service.permission.*
import top.fatweb.api.util.PageUtil
import top.fatweb.api.util.RedisUtil
import top.fatweb.api.util.StrUtil
import top.fatweb.api.util.WebUtil
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.UserWithPasswordRoleInfoVo
import top.fatweb.api.vo.permission.UserWithRoleInfoVo
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * User service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Service
class UserServiceImpl(
    private val passwordEncoder: PasswordEncoder,
    private val redisUtil: RedisUtil,
    private val userInfoService: IUserInfoService,
    private val moduleService: IModuleService,
    private val menuService: IMenuService,
    private val funcService: IFuncService,
    private val operationService: IOperationService,
    private val userRoleService: IUserRoleService,
    private val userGroupService: IUserGroupService
) : ServiceImpl<UserMapper, User>(), IUserService {
    override fun getUserWithPowerByUsername(username: String): User? {
        val user = baseMapper.selectOneWithPowerInfoByUsername(username)
        user ?: let { return null }

        if (user.id == 0L) {
            user.modules = moduleService.list()
            user.menus = menuService.list()
            user.funcs = funcService.list()
            user.operations = operationService.list()
        }

        return user
    }

    override fun getInfo() = WebUtil.getLoginUsername()
        ?.let { username -> getUserWithPowerByUsername(username)?.let { UserConverter.userToUserWithPowerInfoVo(it) } }

    override fun getPage(userGetParam: UserGetParam?): PageVo<UserWithRoleInfoVo> {
        val userIdsPage = Page<Long>(userGetParam?.currentPage ?: 1, userGetParam?.pageSize ?: 20)

        PageUtil.setPageSort(userGetParam, userIdsPage, OrderItem.asc("id"))

        val userIdsIPage =
            baseMapper.selectPage(userIdsPage,userGetParam?.searchType ?: "ALL", userGetParam?.searchValue, userGetParam?.searchRegex ?: false)
        val userPage = Page<User>(userIdsIPage.current, userIdsIPage.size, userIdsIPage.total)
        if (userIdsIPage.total > 0) {
            userPage.setRecords(baseMapper.selectListWithRoleInfoByIds(userIdsIPage.records))
        }

        return UserConverter.userPageToUserWithRoleInfoPageVo(userPage)
    }

    override fun getOne(id: Long) =
        baseMapper.selectOneWithRoleInfoById(id)?.let { UserConverter.userToUserWithRoleInfoVo(it) }

    override fun getList() = baseMapper.selectListWithInfo().map { UserConverter.userToUserWithInfoVo(it) }

    @Transactional
    override fun add(userAddParam: UserAddParam): UserWithPasswordRoleInfoVo? {
        val rawPassword =
            if (userAddParam.password.isNullOrBlank()) StrUtil.getRandomPassword(10) else userAddParam.password
        val user = UserConverter.userAddParamToUser(userAddParam)

        user.apply {
            password = passwordEncoder.encode(rawPassword)
        }

        if (baseMapper.insert(user) == 1) {
            user.userInfo?.let { userInfoService.save(it.apply { userId = user.id }) }

            if (!user.roles.isNullOrEmpty()) {
                userRoleService.saveBatch(user.roles!!.map {
                    UserRole().apply {
                        userId = user.id
                        roleId = it.id
                    }
                })
            }

            if (!user.groups.isNullOrEmpty()) {
                userGroupService.saveBatch(user.groups!!.map {
                    UserGroup().apply {
                        userId = user.id
                        groupId = it.id
                    }
                })
            }

            user.password = rawPassword

            return UserConverter.userToUserWithPasswordRoleInfoVo(user)
        }

        return null
    }

    @Transactional
    override fun update(userUpdateParam: UserUpdateParam): UserWithRoleInfoVo? {
        val user = UserConverter.userUpdateParamToUser(userUpdateParam)
        user.updateTime = LocalDateTime.now(ZoneOffset.UTC)

        val oldRoleList = userRoleService.list(
            KtQueryWrapper(UserRole()).select(UserRole::roleId).eq(UserRole::userId, userUpdateParam.id)
        ).map { it.roleId }
        val addRoleIds = HashSet<Long>()
        val removeRoleIds = HashSet<Long>()
        userUpdateParam.roleIds?.forEach { addRoleIds.add(it) }
        oldRoleList.forEach {
            if (it != null) {
                removeRoleIds.add(it)
            }
        }
        removeRoleIds.removeAll(addRoleIds)
        oldRoleList.toSet().let { addRoleIds.removeAll(it) }

        val oldGroupList = userGroupService.list(
            KtQueryWrapper(UserGroup()).select(UserGroup::groupId).eq(UserGroup::userId, userUpdateParam.id)
        ).map { it.groupId }
        val addGroupIds = HashSet<Long>()
        val removeGroupIds = HashSet<Long>()
        userUpdateParam.groupIds?.forEach { addGroupIds.add(it) }
        oldGroupList.forEach {
            if (it != null) {
                removeGroupIds.add(it)
            }
        }
        removeGroupIds.removeAll(addGroupIds)
        oldGroupList.toSet().let { addGroupIds.removeAll(it) }

        baseMapper.updateById(user)

        user.userInfo?.let { userInfo ->
            userInfoService.getOne(
                KtQueryWrapper(UserInfo()).select(UserInfo::id).eq(UserInfo::userId, userUpdateParam.id)
            )?.let {
                userInfo.id = it.id
                userInfoService.updateById(userInfo)
            }
        }

        removeRoleIds.forEach {
            userRoleService.remove(
                KtQueryWrapper(UserRole()).eq(
                    UserRole::userId, userUpdateParam.id
                ).eq(UserRole::roleId, it)
            )
        }

        addRoleIds.forEach {
            userRoleService.save(UserRole().apply {
                userId = userUpdateParam.id
                roleId = it
            })
        }

        removeGroupIds.forEach {
            userGroupService.remove(
                KtQueryWrapper(UserGroup()).eq(
                    UserGroup::userId, userUpdateParam.id
                ).eq(UserGroup::groupId, it)
            )
        }

        addGroupIds.forEach {
            userGroupService.save(UserGroup().apply {
                userId = userUpdateParam.id
                groupId = it
            })
        }

        userUpdateParam.id?.let { WebUtil.offlineUser(redisUtil, it) }

        return UserConverter.userToUserWithRoleInfoVo(user)
    }

    override fun password(userUpdatePasswordParam: UserUpdatePasswordParam) {
        if (WebUtil.getLoginUserId() != 0L && userUpdatePasswordParam.id == 0L) {
            throw AccessDeniedException("Access denied")
        }

        val user = baseMapper.selectById(userUpdatePasswordParam.id)
        user?.let {
            val wrapper = KtUpdateWrapper(User())
            wrapper.eq(User::id, user.id)
                .set(User::password, passwordEncoder.encode(userUpdatePasswordParam.password))
                .set(
                    User::credentialsExpiration,
                    if (user.id != 0L) userUpdatePasswordParam.credentialsExpiration else null
                )
                .set(User::updateTime, LocalDateTime.now(ZoneOffset.UTC))

            this.update(wrapper)

            userUpdatePasswordParam.id?.let { WebUtil.offlineUser(redisUtil, it) }
        } ?: let {
            throw NoRecordFoundException()
        }
    }

    @Transactional
    override fun deleteOne(id: Long) {
        if (id == 0L) {
            return
        }

        this.delete(UserDeleteParam(listOf(id)))
    }

    @Transactional
    override fun delete(userDeleteParam: UserDeleteParam) {
        val ids = userDeleteParam.ids.filter { it != 0L }
        if (ids.isEmpty()) {
            return
        }

        baseMapper.deleteBatchIds(ids)
        userInfoService.remove(KtQueryWrapper(UserInfo()).`in`(UserInfo::userId, ids))
        userRoleService.remove(KtQueryWrapper(UserRole()).`in`(UserRole::userId, ids))
        userGroupService.remove(KtQueryWrapper(UserGroup()).`in`(UserGroup::userId, ids))

        WebUtil.offlineUser(redisUtil, *ids.toLongArray())
    }

    override fun selectIdsWithRoleIds(roleIds: List<Long>) = baseMapper.selectIdsWithRoleIds(roleIds)

    override fun selectIdsWithGroupIds(groupIds: List<Long>) = baseMapper.selectIdsWithGroupIds(groupIds)
}
