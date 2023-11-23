package top.fatweb.api.entity.permission

import java.io.Serializable

/**
 * Set of power entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
class PowerSet : Serializable {
    var moduleList: List<Module>? = null

    var menuList: List<Menu>? = null

    var elementList: List<Element>? = null

    var operationList: List<Operation>? = null
}