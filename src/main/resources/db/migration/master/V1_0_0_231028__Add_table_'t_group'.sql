drop table if exists t_group;

create table if not exists t_group
(
    id      bigint      not null primary key,
    name    varchar(30) not null comment '用户组名',
    enable  int         not null comment '启用',
    create_time datetime     not null default (utc_timestamp()) comment '创建时间',
    update_time datetime     not null default (utc_timestamp()) comment '修改时间',
    deleted bigint      not null default 0,
    version int         not null default 0,
    constraint t_group_unique unique (name, deleted)
) comment '用户组表';