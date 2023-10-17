drop table if exists t_user;

create table if not exists t_user
(
    id                     bigint      not null primary key,
    username               varchar(20) not null comment '用户名',
    password               char(70)    not null comment '密码',
    locking                int         not null comment '锁定',
    expiration             datetime comment '过期时间',
    credentials_expiration datetime comment '认证过期时间',
    enable                 int         not null comment '启用',
    last_login_time        datetime comment '上次登录时间',
    last_login_ip          varchar(128) comment '上次登录 IP',
    create_time            datetime    not null default (utc_timestamp()) comment '创建时间',
    update_time            datetime    not null default (utc_timestamp()) comment '修改时间',
    deleted                bigint      not null default 0,
    version                int         not null default 0,
    constraint t_user_unique unique (username, deleted)
) comment '用户表';

insert into t_user (id, username, password, locking, enable) value (0, 'admin',
                                                                    '$2a$10$3wDGdzTZlC..7eY6u2XM5u78xUQo0z5Sj5yOpneD4QJ0q/TA5TY0S',
                                                                    0, 1);

drop table if exists t_sys_log;
create table t_sys_log
(
    id                     bigint      not null,
    log_type               varchar(50) not null comment '日志类型',
    operate_user_id        bigint      not null comment '操作用户',
    operate_time           datetime    not null default (utc_timestamp()) comment '操作时间',
    request_uri            varchar(500)         default null comment '请求 URI',
    request_method         varchar(10)          default null comment '请求方式',
    request_params         text comment '请求参数',
    request_ip             varchar(20) not null comment '请求 IP',
    request_server_address varchar(50) not null comment '请求服务器地址',
    is_exception           char(1)              default null comment '是否异常',
    exception_info         text comment '异常信息',
    start_time             datetime    not null comment '开始时间',
    end_time               datetime    not null comment '结束时间',
    execute_time           int                  default null comment '执行时间',
    user_agent             varchar(500)         default null comment '用户代理',
    device_name            varchar(100)         default null comment '操作系统',
    browser_name           varchar(100)         default null comment '浏览器名称',
    primary key (id) using btree,
    key idx_sys_log_log_type (log_type) using btree,
    key idx_sys_log_operate_user_id (operate_user_id) using btree,
    key idx_sys_log_is_exception (is_exception) using btree,
    key idx_sys_log_operate_time (operate_time) using btree
) comment '系统日志表';