package top.fatweb.oxygen.api.entity.tool

import com.baomidou.mybatisplus.annotation.*
import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable

/**
 * Tool base data intermediate entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 */
@TableName("t_r_tool_base_data")
class RToolBaseData : Serializable {
    /**
     * Data type enum
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    enum class DataType(@field:EnumValue @field:JsonValue val code: String) {
        SOURCE("SOURCE"), DIST("DIST")
    }

    /**
     * ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @TableField("id")
    var id: Long? = null

    /**
     * Base ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @TableField("base_id")
    var baseId: Long? = null

    /**
     * Data ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @TableField("data_id")
    var dataId: Long? = null

    /**
     * Data type
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see DataType
     */
    @TableField("data_type")
    var dataType: DataType? = null

    /**
     * Base version
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @TableField("base_version")
    var baseVersion: Long? = null

    /**
     * Deleted
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @TableField("deleted")
    @TableLogic
    var deleted: Long? = null

    /**
     * Version
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @TableField("version")
    @Version
    var version: Int? = null

    override fun toString(): String {
        return "RToolBaseData(id=$id, baseId=$baseId, dataId=$dataId, dataType=$dataType, baseVersion=$baseVersion, deleted=$deleted, version=$version)"
    }
}
