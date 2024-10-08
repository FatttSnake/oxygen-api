drop table if exists t_b_tool_template;

create table if not exists t_b_tool_template
(
    id          bigint      not null primary key,
    name        varchar(40) not null comment '模板名',
    base_id     bigint      not null comment '基板 ID',
    source_id   bigint      not null comment '源码 ID',
    platform    varchar(20) not null comment '平台',
    entry_point varchar(64) not null default 'main.tsx' comment '入口文件',
    enable      int         not null default 1 comment '启用',
    create_time datetime    not null default (utc_timestamp()) comment '创建时间',
    update_time datetime    not null default (utc_timestamp()) comment '修改时间',
    deleted     bigint      not null default 0,
    version     int         not null default 0,
    constraint t_b_tool_template_unique_name_platform unique (name, platform, deleted)
) comment '工具-模板表';