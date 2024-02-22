package top.fatweb.oxygen.api.service.permission.impl

import com.baomidou.dynamic.datasource.annotation.DS
import com.baomidou.mybatisplus.core.metadata.OrderItem
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.converter.permission.UserConverter
import top.fatweb.oxygen.api.entity.permission.RUserGroup
import top.fatweb.oxygen.api.entity.permission.RUserRole
import top.fatweb.oxygen.api.entity.permission.User
import top.fatweb.oxygen.api.entity.permission.UserInfo
import top.fatweb.oxygen.api.exception.DatabaseInsertException
import top.fatweb.oxygen.api.exception.DatabaseUpdateException
import top.fatweb.oxygen.api.exception.NoRecordFoundException
import top.fatweb.oxygen.api.exception.UserNotFoundException
import top.fatweb.oxygen.api.mapper.permission.UserMapper
import top.fatweb.oxygen.api.param.permission.user.*
import top.fatweb.oxygen.api.service.permission.*
import top.fatweb.oxygen.api.util.PageUtil
import top.fatweb.oxygen.api.util.RedisUtil
import top.fatweb.oxygen.api.util.StrUtil
import top.fatweb.oxygen.api.util.WebUtil
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.permission.UserWithPasswordRoleInfoVo
import top.fatweb.oxygen.api.vo.permission.UserWithPowerInfoVo
import top.fatweb.oxygen.api.vo.permission.UserWithRoleInfoVo
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

/**
 * User service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see PasswordEncoder
 * @see RedisUtil
 * @see IUserInfoService
 * @see IModuleService
 * @see IMenuService
 * @see IFuncService
 * @see IOperationService
 * @see IRUserRoleService
 * @see IRUserGroupService
 * @see ServiceImpl
 * @see UserMapper
 * @see User
 * @see IUserService
 */
@DS("master")
@Service
class UserServiceImpl(
    private val passwordEncoder: PasswordEncoder,
    private val redisUtil: RedisUtil,
    private val userInfoService: IUserInfoService,
    private val moduleService: IModuleService,
    private val menuService: IMenuService,
    private val funcService: IFuncService,
    private val operationService: IOperationService,
    private val rUserRoleService: IRUserRoleService,
    private val rUserGroupService: IRUserGroupService
) : ServiceImpl<UserMapper, User>(), IUserService {
    override fun getUserWithPowerByAccount(account: String): User? {
        val user = baseMapper.selectOneWithPowerInfoByAccount(account)
        user ?: return null

        if (user.id == 0L) {
            user.modules = moduleService.list()
            user.menus = menuService.list()
            user.funcs = funcService.list()
            user.operations = operationService.list()
        }

        return user
    }

    override fun getInfo(): UserWithPowerInfoVo =
        WebUtil.getLoginUsername()?.let(::getUserWithPowerByAccount)?.let(UserConverter::userToUserWithPowerInfoVo)
            ?: throw UserNotFoundException()

    override fun updateInfo(userInfoUpdateParam: UserInfoUpdateParam): Boolean {
        val userId = WebUtil.getLoginUserId() ?: throw UserNotFoundException()
        return userInfoService.update(
                KtUpdateWrapper(UserInfo()).eq(UserInfo::userId, userId)
                    .set(UserInfo::avatar, userInfoUpdateParam.avatar)
                    .set(UserInfo::nickname, userInfoUpdateParam.nickname)
            )
    }

    override fun getOne(id: Long): UserWithRoleInfoVo =
        baseMapper.selectOneWithRoleInfoById(id)?.let(UserConverter::userToUserWithRoleInfoVo)
            ?: throw UserNotFoundException()

    override fun getPage(userGetParam: UserGetParam?): PageVo<UserWithRoleInfoVo> {
        val userIdsPage = Page<Long>(userGetParam?.currentPage ?: 1, userGetParam?.pageSize ?: 20)

        PageUtil.setPageSort(userGetParam, userIdsPage, OrderItem.asc("id"))

        val userIdsIPage =
            baseMapper.selectPage(
                userIdsPage,
                userGetParam?.searchType ?: "ALL",
                userGetParam?.searchValue,
                userGetParam?.searchRegex ?: false
            )
        val userPage = Page<User>(userIdsIPage.current, userIdsIPage.size, userIdsIPage.total)
        if (userIdsIPage.total > 0) {
            userPage.setRecords(baseMapper.selectListWithRoleInfoByIds(userIdsIPage.records))
        }

        return UserConverter.userPageToUserWithRoleInfoPageVo(userPage)
    }

    override fun getList() = baseMapper.selectListWithInfo().map(UserConverter::userToUserWithInfoVo)

    @Transactional
    override fun add(userAddParam: UserAddParam): UserWithPasswordRoleInfoVo {
        val rawPassword =
            if (userAddParam.password.isNullOrBlank()) StrUtil.getRandomPassword(10) else userAddParam.password
        val user = UserConverter.userAddParamToUser(userAddParam)

        user.apply {
            password = passwordEncoder.encode(rawPassword)
            verify = if (userAddParam.verified) null else "${
                LocalDateTime.now(ZoneOffset.UTC).toInstant(ZoneOffset.UTC).toEpochMilli()
            }-${UUID.randomUUID()}-${UUID.randomUUID()}-${UUID.randomUUID()}"
        }

        if (!this.save(user)) {
            throw DatabaseInsertException()
        }


        user.userInfo?.apply { userId = user.id }?.let(userInfoService::save)

        if (!user.roles.isNullOrEmpty()) {
            rUserRoleService.saveBatch(user.roles!!.map {
                RUserRole().apply {
                    userId = user.id
                    roleId = it.id
                }
            })
        }

        if (!user.groups.isNullOrEmpty()) {
            rUserGroupService.saveBatch(user.groups!!.map {
                RUserGroup().apply {
                    userId = user.id
                    groupId = it.id
                }
            })
        }

        user.password = rawPassword

        return UserConverter.userToUserWithPasswordRoleInfoVo(user)
    }

    @Transactional
    override fun update(userUpdateParam: UserUpdateParam): UserWithRoleInfoVo {
        val user = UserConverter.userUpdateParamToUser(userUpdateParam)
        user.updateTime = LocalDateTime.now(ZoneOffset.UTC)

        val oldRoleList = rUserRoleService.list(
            KtQueryWrapper(RUserRole()).select(RUserRole::roleId).eq(RUserRole::userId, userUpdateParam.id)
        ).map { it.roleId }
        val addRoleIds = HashSet<Long>()
        val removeRoleIds = HashSet<Long>()
        userUpdateParam.roleIds?.forEach(addRoleIds::add)
        oldRoleList.forEach {
            it?.let(removeRoleIds::add)
        }
        removeRoleIds.removeAll(addRoleIds)
        oldRoleList.toSet().let(addRoleIds::removeAll)

        val oldGroupList = rUserGroupService.list(
            KtQueryWrapper(RUserGroup()).select(RUserGroup::groupId).eq(RUserGroup::userId, userUpdateParam.id)
        ).map { it.groupId }
        val addGroupIds = HashSet<Long>()
        val removeGroupIds = HashSet<Long>()
        userUpdateParam.groupIds?.forEach(addGroupIds::add)
        oldGroupList.forEach {
            it?.let(removeGroupIds::add)
        }
        removeGroupIds.removeAll(addGroupIds)
        oldGroupList.toSet().let(addGroupIds::removeAll)

        this.updateById(user)
        this.update(
            KtUpdateWrapper(User()).eq(User::id, user.id)
                .set(
                    User::verify,
                    if (userUpdateParam.verified) null else "${
                        LocalDateTime.now(ZoneOffset.UTC).toInstant(ZoneOffset.UTC).toEpochMilli()
                    }-${UUID.randomUUID()}-${UUID.randomUUID()}-${UUID.randomUUID()}"
                )
                .set(User::expiration, user.expiration)
                .set(User::credentialsExpiration, user.credentialsExpiration)
        )

        user.userInfo?.let { userInfo ->
            userInfoService.getOne(
                KtQueryWrapper(UserInfo()).select(UserInfo::id).eq(UserInfo::userId, userUpdateParam.id)
            )?.let {
                userInfo.id = it.id
                userInfoService.updateById(userInfo)
            }
        }

        removeRoleIds.forEach {
            rUserRoleService.remove(
                KtQueryWrapper(RUserRole()).eq(
                    RUserRole::userId, userUpdateParam.id
                ).eq(RUserRole::roleId, it)
            )
        }

        addRoleIds.forEach {
            rUserRoleService.save(RUserRole().apply {
                userId = userUpdateParam.id
                roleId = it
            })
        }

        removeGroupIds.forEach {
            rUserGroupService.remove(
                KtQueryWrapper(RUserGroup()).eq(
                    RUserGroup::userId, userUpdateParam.id
                ).eq(RUserGroup::groupId, it)
            )
        }

        addGroupIds.forEach {
            rUserGroupService.save(RUserGroup().apply {
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

        val user = this.getById(userUpdatePasswordParam.id)
        user?.let {
            val wrapper = KtUpdateWrapper(User())
            wrapper.eq(User::id, user.id)
                .set(User::password, passwordEncoder.encode(userUpdatePasswordParam.password))
                .set(
                    User::credentialsExpiration,
                    if (user.id != 0L) userUpdatePasswordParam.credentialsExpiration else null
                )
                .set(User::updateTime, LocalDateTime.now(ZoneOffset.UTC))

            if (!this.update(wrapper)) {
                throw DatabaseUpdateException()
            }

            userUpdatePasswordParam.id?.let { WebUtil.offlineUser(redisUtil, it) }
        } ?: throw NoRecordFoundException()
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
        val ids = userDeleteParam.ids!!.filter { it != 0L }
        if (ids.isEmpty()) {
            return
        }

        this.removeBatchByIds(ids)
        userInfoService.remove(KtQueryWrapper(UserInfo()).`in`(UserInfo::userId, ids))
        rUserRoleService.remove(KtQueryWrapper(RUserRole()).`in`(RUserRole::userId, ids))
        rUserGroupService.remove(KtQueryWrapper(RUserGroup()).`in`(RUserGroup::userId, ids))

        WebUtil.offlineUser(redisUtil, *ids.toLongArray())
    }

    override fun getIdsWithRoleIds(roleIds: List<Long>) = baseMapper.selectIdsWithRoleIds(roleIds)

    override fun getIdsWithGroupIds(groupIds: List<Long>) = baseMapper.selectIdsWithGroupIds(groupIds)
}
