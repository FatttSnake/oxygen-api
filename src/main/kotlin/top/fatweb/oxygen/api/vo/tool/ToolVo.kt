package top.fatweb.oxygen.api.vo.tool

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.vo.permission.UserWithInfoVo
import java.time.LocalDateTime

/**
 * Tool value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
data class ToolVo(
    /**
     * ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @JsonSerialize(using = ToStringSerializer::class)
    val id: Long?,

    /**
     * Name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "名称")
    val name: String?,

    /**
     * Tool ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "工具 ID")
    val toolId: String?,

    /**
     * Icon
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "图标")
    val icon: String?,

    /**
     * Description
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "简介")
    val description: String?,

    /**
     * Base ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @JsonSerialize(using = ToStringSerializer::class)
    @Schema(description = "基板 ID")
    val baseId: Long?,

    /**
     * Author
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserWithInfoVo
     */
    @Schema(description = "作者")
    val author: UserWithInfoVo?,

    /**
     * Version
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "版本")
    val ver: String?,

    /**
     * Privately
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "私有")
    val privately: Boolean?,

    /**
     * Keywords
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "关键字")
    val keywords: List<String>?,

    /**
     * Categories
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolCategoryVo
     */
    @Schema(description = "类别")
    val categories: List<ToolCategoryVo>?,

    /**
     * Source
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolDataVo
     */
    @Schema(description = "源码")
    val source: ToolDataVo?,

    /**
     * Dist
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolDataVo
     */
    @Schema(description = "产物")
    val dist: ToolDataVo?,

    /**
     * Entry point
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "入口文件")
    val entryPoint: String?,

    /**
     * Publish
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "发布")
    val publish: Boolean?,

    /**
     * Review
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Tool.ReviewType
     */
    @Schema(description = "审核")
    val review: Tool.ReviewType?,

    /**
     * Publish time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LocalDateTime
     */
    @Schema(description = "发布时间", example = "1900-01-01T00:00:00.000Z")
    val publishTime: LocalDateTime?,

    /**
     * Create time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LocalDateTime
     */
    @Schema(description = "创建时间", example = "1900-01-01T00:00:00.000Z")
    val createTime: LocalDateTime?,

    /**
     * Update time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LocalDateTime
     */
    @Schema(description = "修改时间", example = "1900-01-01T00:00:00.000Z")
    val updateTime: LocalDateTime?
)