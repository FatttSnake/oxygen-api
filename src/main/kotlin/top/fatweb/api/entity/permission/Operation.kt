package top.fatweb.api.entity.permission

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

/**
 * Operation entity
 *
 * @author FatttSnake
 * @since 1.0.0
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
     * 页面元素ID
     */
    @TableField("element_id")
    var elementId: Long? = null

    override fun toString(): String {
        return "Operation(id=$id, name=$name, code=$code, elementId=$elementId)"
    }
}
