package top.fatweb.api.entity.permission

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

/**
 * <p>
 * 权限
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
@TableName("t_power")
class Power : Serializable {

    @TableId("id")
    var id: Long? = null

    /**
     * 权限类型
     */
    @TableField("type_id")
    var typeId: Long? = null

    override fun toString(): String {
        return "Power(id=$id, typeId=$typeId)"
    }
}
