drop table if exists t_r_tool_main_category;

create table if not exists t_r_tool_main_category
(
    id          bigint not null primary key,
    tool_id     bigint not null comment '工具',
    category_id bigint not null comment '类别',
    deleted     bigint not null default 0,
    version     int    not null default 0
) comment '中间表-工具-主表-类别';