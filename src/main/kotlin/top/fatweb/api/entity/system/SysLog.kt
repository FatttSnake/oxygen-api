package top.fatweb.api.entity.system

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.time.LocalDateTime

/**
 * <p>
 * 系统日志表
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-18
 */
@TableName("t_sys_log")
class SysLog : Serializable {

    @TableId("id")
    var id: Long? = null

    /**
     * 日志类型
     */
    @TableField("log_type")
    var logType: String? = null

    /**
     * 操作用户
     */
    @TableField("operate_user_id")
    var operateUserId: Long? = null

    /**
     * 操作时间
     */
    @TableField("operate_time")
    var operateTime: LocalDateTime? = null

    /**
     * 请求 URI
     */
    @TableField("request_uri")
    var requestUri: String? = null

    /**
     * 请求方式
     */
    @TableField("request_method")
    var requestMethod: String? = null

    /**
     * 请求参数
     */
    @TableField("request_params")
    var requestParams: String? = null

    /**
     * 请求 IP
     */
    @TableField("request_ip")
    var requestIp: String? = null

    /**
     * 请求服务器地址
     */
    @TableField("request_server_address")
    var requestServerAddress: String? = null

    /**
     * 是否异常
     */
    @TableField("exception")
    var exception: Int? = null

    /**
     * 异常信息
     */
    @TableField("exception_info")
    var exceptionInfo: String? = null

    /**
     * 开始时间
     */
    @TableField("start_time")
    var startTime: LocalDateTime? = null

    /**
     * 结束时间
     */
    @TableField("end_time")
    var endTime: LocalDateTime? = null

    /**
     * 执行时间
     */
    @TableField("execute_time")
    var executeTime: Long? = null

    /**
     * 用户代理
     */
    @TableField("user_agent")
    var userAgent: String? = null

    @TableField(exist = false)
    var operateUsername: String? = null

    override fun toString(): String {
        return "SysLog(id=$id, logType=$logType, operateUserId=$operateUserId, operateTime=$operateTime, requestUri=$requestUri, requestMethod=$requestMethod, requestParams=$requestParams, requestIp=$requestIp, requestServerAddress=$requestServerAddress, exception=$exception, exceptionInfo=$exceptionInfo, startTime=$startTime, endTime=$endTime, executeTime=$executeTime, userAgent=$userAgent, operateUsername=$operateUsername)"
    }
}
