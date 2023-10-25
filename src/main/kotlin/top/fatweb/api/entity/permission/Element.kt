package top.fatweb.api.entity.permission

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

/**
 * <p>
 * 页面元素
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
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
     * 权限ID
     */
    @TableField("power_id")
    var powerId: Long? = null

    /**
     * 菜单ID
     */
    @TableField("menu_id")
    var menuId: Long? = null

    override fun toString(): String {
        return "Element(id=$id, name=$name, powerId=$powerId, menuId=$menuId)"
    }
}
