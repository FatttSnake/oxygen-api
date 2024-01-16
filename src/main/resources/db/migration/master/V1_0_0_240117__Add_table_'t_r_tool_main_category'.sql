drop table if exists t_r_tool_main_category;

create table if not exists t_r_tool_main_category
(
    id          bigint   not null primary key,
    tool_id     bigint   not null comment '工具',
    category_id bigint   not null comment '类别',
    create_time datetime not null default (utc_timestamp()) comment '创建时间',
    update_time datetime not null default (utc_timestamp()) comment '修改时间',
    deleted     bigint   not null default 0,
    version     int      not null default 0
) comment '中间表-工具-主表-类别';