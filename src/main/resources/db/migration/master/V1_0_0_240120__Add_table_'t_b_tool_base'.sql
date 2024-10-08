drop table if exists t_b_tool_base;

create table if not exists t_b_tool_base
(
    id          bigint      not null primary key,
    name        varchar(20) not null comment '基板名',
    source_id   bigint      not null comment '源码 ID',
    dist_id     bigint      not null comment '产物 ID',
    platform    varchar(20) not null comment '平台',
    compiled    int         not null default 0 comment '已编译',
    create_time datetime    not null default (utc_timestamp()) comment '创建时间',
    update_time datetime    not null default (utc_timestamp()) comment '修改时间',
    deleted     bigint      not null default 0,
    version     int         not null default 0,
    constraint t_b_tool_base_unique_name_platform unique (name, platform, deleted)
) comment '工具-基板表';