package top.fatweb.api.entity.converter

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import top.fatweb.api.entity.param.LoginParam
import top.fatweb.api.entity.permission.User

@Mapper(componentModel = "spring")
interface UserConverter {
    @Mappings(Mapping(source = "username", target = "username"), Mapping(source = "password", target = "password"))
    fun loginParamToUser(loginParam: LoginParam): User

}