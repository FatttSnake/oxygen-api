drop table if exists t_sys_log;
create table t_sys_log -- 系统日志表
(
    id                     bigint      not null,
    log_type               varchar(50) not null,                           -- 日志类型
    operate_user_id        bigint      not null,                           -- 操作用户
    operate_time           datetime not null default (strftime('%Y-%m-%d %H:%M:%f','now')), -- 操作时间
    request_uri            varchar(500)         default null,              -- 请求 URI
    request_method         varchar(10)          default null,              -- 请求方式
    request_params         text,                                           -- 请求参数
    request_ip             varchar(20) not null,                           -- 请求 IP
    request_server_address varchar(50) not null,                           -- 请求服务器地址
    exception              int         not null default 0,                 -- 是否异常
    exception_info         text,                                           -- 异常信息
    start_time             datetime not null,                           -- 开始时间
    end_time               datetime not null,                           -- 结束时间
    execute_time           bigint               default null,              -- 执行时间
    user_agent             varchar(500)         default null               -- 用户代理
);

drop table if exists t_active;
create table t_active_log -- 用户活跃日志表
(
    id bigint not null,
    event varchar(50) not null, -- 事件,
    operate_user_id bigint not null, -- 操作用户
    operate_time datetime not null default (strftime('%Y-%m-%d %H:%M:%f','now')) -- 操作时间
);