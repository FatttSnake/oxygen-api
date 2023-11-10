package top.fatweb.api.entity.permission

import java.io.Serializable

/**
 * 权限集合
 */
class PowerSet : Serializable {
    var moduleList: List<Module>? = null

    var menuList: List<Menu>? = null

    var elementList: List<Element>? = null

    var operationList: List<Operation>? = null
}