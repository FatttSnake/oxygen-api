drop table if exists t_sys_log;

create table t_sys_log -- 系统日志表
(
    id                     integer not null,
    log_type               text    not null,                                                -- 日志类型
    operate_user_id        integer not null,                                                -- 操作用户
    operate_time           text    not null default (strftime('%Y-%m-%d %H:%M:%f', 'now')), -- 操作时间
    request_uri            text             default null,                                   -- 请求 URI
    request_method         text             default null,                                   -- 请求方式
    request_params         text,                                                            -- 请求参数
    request_ip             text    not null,                                                -- 请求 IP
    request_server_address text    not null,                                                -- 请求服务器地址
    exception              integer not null default 0,                                      -- 是否异常
    exception_info         text,                                                            -- 异常信息
    start_time             text    not null,                                                -- 开始时间
    end_time               text    not null,                                                -- 结束时间
    execute_time           integer          default null,                                   -- 执行时间
    user_agent             text             default null                                    -- 用户代理
);