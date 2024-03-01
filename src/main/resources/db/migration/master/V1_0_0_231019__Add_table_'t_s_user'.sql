drop table if exists t_s_user;

create table if not exists t_s_user
(
    id                     bigint       not null primary key,
    username               varchar(20)  not null comment '用户名',
    password               char(70)     not null comment '密码',
    two_factor             varchar(40)  null comment '双因素',
    verify                 varchar(144) null comment '验证邮箱',
    forget                 varchar(144) null comment '忘记密码',
    locking                int          not null comment '锁定',
    expiration             datetime comment '过期时间',
    credentials_expiration datetime comment '认证过期时间',
    enable                 int          not null comment '启用',
    current_login_time     datetime comment '当前登录时间',
    current_login_ip       varchar(128) comment '当前登录 IP',
    last_login_time        datetime comment '上次登录时间',
    last_login_ip          varchar(128) comment '上次登录 IP',
    create_time            datetime     not null default (utc_timestamp()) comment '创建时间',
    update_time            datetime     not null default (utc_timestamp()) comment '修改时间',
    deleted                bigint       not null default 0,
    version                int          not null default 0,
    constraint t_s_user_unique_username unique (username, deleted),
    constraint t_s_user_unique_two_factor unique (two_factor, deleted),
    constraint t_s_user_unique_verify unique (verify, deleted),
    constraint t_s_user_unique_forget unique (forget, deleted)
) comment '系统-用户表';