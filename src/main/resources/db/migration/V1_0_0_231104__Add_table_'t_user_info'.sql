drop table if exists t_user_info;

create table if not exists t_user_info
(
    id          bigint       not null primary key,
    user_id     bigint       not null comment '用户ID',
    nick_name   varchar(50)  null comment '昵称',
    avatar      varchar(500) null comment '头像',
    email       varchar(100) null comment '邮箱',
    create_time datetime     not null default (utc_timestamp()) comment '创建时间',
    update_time datetime     not null default (utc_timestamp()) comment '修改时间',
    deleted     bigint       not null default 0,
    version     int          not null default 0,
    constraint t_user_info_unique unique (user_id, deleted)
) comment '用户资料表';