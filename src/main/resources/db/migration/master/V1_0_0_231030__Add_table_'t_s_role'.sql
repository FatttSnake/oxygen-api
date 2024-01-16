drop table if exists t_s_role;

create table if not exists t_s_role
(
    id          bigint      not null primary key,
    name        varchar(20) not null comment '角色名',
    enable      int         not null comment '启用',
    create_time datetime    not null default (utc_timestamp()) comment '创建时间',
    update_time datetime    not null default (utc_timestamp()) comment '修改时间',
    deleted     bigint      not null default 0,
    version     int         not null default 0,
    constraint t_s_role_unique unique (name, deleted)
) comment '系统-角色表';