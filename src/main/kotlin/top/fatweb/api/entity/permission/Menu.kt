package top.fatweb.api.entity.permission

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

/**
 * Menu entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@TableName("t_menu")
class Menu : Serializable {

    @TableId("id")
    var id: Long? = null

    /**
     *  菜单名
     */
    @TableField("name")
    var name: String? = null

    /**
     * URL
     */
    @TableField("url")
    var url: String? = null

    /**
     * 父ID
     */
    @TableField("parent_id")
    var parentId: Long? = null

    /**
     * 模块ID
     */
    @TableField("module_id")
    var moduleId: Long? = null

    override fun toString(): String {
        return "Menu(id=$id, name=$name, url=$url, parentId=$parentId, moduleId=$moduleId)"
    }
}
