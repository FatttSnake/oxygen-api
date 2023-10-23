drop table if exists t_sys_log;
create table t_sys_log
(
    id                     bigint      not null,
    log_type               varchar(50) not null comment '日志类型',
    operate_user_id        bigint      not null comment '操作用户',
    operate_time           datetime(3) not null default (utc_timestamp()) comment '操作时间',
    request_uri            varchar(500)         default null comment '请求 URI',
    request_method         varchar(10)          default null comment '请求方式',
    request_params         text comment '请求参数',
    request_ip             varchar(20) not null comment '请求 IP',
    request_server_address varchar(50) not null comment '请求服务器地址',
    is_exception           int         not null default 0 comment '是否异常',
    exception_info         text comment '异常信息',
    start_time             datetime(3) not null comment '开始时间',
    end_time               datetime(3) not null comment '结束时间',
    execute_time           bigint               default null comment '执行时间',
    user_agent             varchar(500)         default null comment '用户代理',
    primary key (id) using btree,
    key idx_sys_log_log_type (log_type) using btree,
    key idx_sys_log_operate_user_id (operate_user_id) using btree,
    key idx_sys_log_is_exception (is_exception) using btree,
    key idx_sys_log_operate_time (operate_time) using btree
) comment '系统日志表';