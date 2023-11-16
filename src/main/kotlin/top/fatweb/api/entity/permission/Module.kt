package top.fatweb.api.entity.permission

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

/**
 * Module Entity
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@TableName("t_module")
class Module : Serializable {

    @TableId("id")
    var id: Long? = null

    /**
     * 模块名
     */
    @TableField("name")
    var name: String? = null

    override fun toString(): String {
        return "Module(id=$id, name=$name)"
    }
}
