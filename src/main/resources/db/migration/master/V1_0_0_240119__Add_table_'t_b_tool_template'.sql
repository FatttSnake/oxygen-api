drop table if exists t_b_tool_template;

create table if not exists t_b_tool_template
(
    id          bigint      not null primary key,
    name        varchar(40) not null comment '模板名',
    ver         varchar(20) not null comment '版本',
    base        varchar(20) not null comment '基于',
    source      bigint      not null comment '源码',
    dist        bigint      not null comment '产物',
    create_time datetime    not null default (utc_timestamp()) comment '创建时间',
    update_time datetime    not null default (utc_timestamp()) comment '修改时间',
    deleted     bigint      not null default 0,
    version     int         not null default 0,
    constraint t_b_tool_template_unique_name unique (name, deleted)
) comment '工具-模板表'