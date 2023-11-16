package top.fatweb.api.entity.permission

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

/**
 * Power type entity
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@TableName("t_power_type")
class PowerType : Serializable {

    @TableId("id")
    var id: Long? = null

    /**
     * 权限类型名
     */
    @TableField("name")
    var name: String? = null

    override fun toString(): String {
        return "PowerType(id=$id, name=$name)"
    }
}
