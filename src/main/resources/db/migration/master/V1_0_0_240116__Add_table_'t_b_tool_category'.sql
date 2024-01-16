drop table if exists t_b_tool_category;

create table if not exists t_b_tool_category
(
    id          bigint      not null primary key,
    name        varchar(50) not null comment '工具类别名',
    enable      int         not null default 1 comment '启用',
    create_time datetime    not null default (utc_timestamp()) comment '创建时间',
    update_time datetime    not null default (utc_timestamp()) comment '修改时间',
    deleted     bigint      not null default 0,
    version     int         not null default 0,
    constraint t_tool_category_name unique (name, deleted)
) comment '工具-类别表';