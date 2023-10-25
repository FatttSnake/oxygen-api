package top.fatweb.api.entity.permission

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

/**
 * <p>
 * 功能
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
@TableName("t_operation")
class Operation : Serializable {

    @TableId("id")
    var id: Long? = null

    /**
     * 功能名
     */
    @TableField("name")
    var name: String? = null

    /**
     * 功能编码
     */
    @TableField("code")
    var code: String? = null

    /**
     * 权限ID
     */
    @TableField("power_id")
    var powerId: Long? = null

    /**
     * 父ID
     */
    @TableField("parent_id")
    var parentId: Long? = null

    /**
     * 页面元素ID
     */
    @TableField("element_id")
    var elementId: Long? = null

    override fun toString(): String {
        return "Operation(id=$id, name=$name, code=$code, powerId=$powerId, parentId=$parentId, elementId=$elementId)"
    }
}
