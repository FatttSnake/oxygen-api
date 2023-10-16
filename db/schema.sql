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

insert into t_user (id, username, password, locking, enable) value (0, 'admin', '$2a$10$3wDGdzTZlC..7eY6u2XM5u78xUQo0z5Sj5yOpneD4QJ0q/TA5TY0S', 0, 1)