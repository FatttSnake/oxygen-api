package top.fatweb.api.entity.permission

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

/**
 * Element entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@TableName("t_element")
class Element : Serializable {

    @TableId("id")
    var id: Long? = null

    /**
     * 元素名
     */
    @TableField("name")
    var name: String? = null

    /**
     * 父ID
     */
    @TableField("parent_id")
    var parentId: Long? = null

    /**
     * 菜单ID
     */
    @TableField("menu_id")
    var menuId: Long? = null

    override fun toString(): String {
        return "Element(id=$id, name=$name, parentId=$parentId, menuId=$menuId)"
    }
}
