package top.fatweb.api.entity.permission

import java.io.Serializable

/**
 * Set of power entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
class PowerSet : Serializable {
    /**
     * Module list
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Module
     */
    var moduleList: List<Module>? = null

    /**
     * Menu list
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Menu
     */
    var menuList: List<Menu>? = null

    /**
     * Function list
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Func
     */
    var funcList: List<Func>? = null

    /**
     * Operation list
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Operation
     */
    var operationList: List<Operation>? = null
}