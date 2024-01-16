drop table if exists t_b_tool_main;

create table if not exists t_b_tool_main
(
    id          bigint       not null primary key,
    name        varchar(50)  not null comment '工具名',
    tool_id     varchar(50)  not null comment '工具 ID',
    description varchar(500) null comment '简介',
    base        bigint       not null comment '基于',
    author      bigint       not null comment '作者',
    ver         varchar(20)  not null comment '版本',
    privately   int          not null default 0 comment '私有',
    keyword     varchar(500) not null comment '关键字',
    source      bigint       null comment '源码',
    dist        bigint       null comment '产物',
    publish     int          not null default 0 comment '发布',
    review      varchar(10)  not null default 'NONE' comment '审核',
    create_time datetime     not null default (utc_timestamp()) comment '创建时间',
    update_time datetime     not null default (utc_timestamp()) comment '修改时间',
    deleted     bigint       not null default 0,
    version     int          not null default 0,
    constraint t_b_tool_main_unique_tool_id unique (tool_id, author, deleted)
) comment '工具-主表';