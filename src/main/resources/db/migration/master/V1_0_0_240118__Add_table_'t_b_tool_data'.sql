drop table if exists t_b_tool_data;

create table if not exists t_b_tool_data
(
    id          bigint   not null primary key,
    data        text     not null comment '数据',
    create_time datetime not null default (utc_timestamp()) comment '创建时间',
    update_time datetime not null default (utc_timestamp()) comment '修改时间',
    deleted     bigint   not null default 0,
    version     int      not null default 0
) comment '工具-数据表';