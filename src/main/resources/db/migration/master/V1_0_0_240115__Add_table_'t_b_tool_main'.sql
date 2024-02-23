drop table if exists t_b_tool_main;

create table if not exists t_b_tool_main
(
    id          bigint       not null primary key,
    name        varchar(50)  not null comment '工具名',
    tool_id     varchar(50)  not null comment '工具 ID',
    icon        text         not null comment '图标',
    description varchar(500) null comment '简介',
    base_id     bigint       not null comment '基板 ID',
    author_id   bigint       not null comment '作者 ID',
    ver         varchar(20)  not null comment '版本',
    keywords    varchar(500) not null comment '关键字',
    source_id   bigint       not null comment '源码 ID',
    dist_id     bigint       not null comment '产物 ID',
    entry_point varchar(64)  not null default 'main.tsx' comment '入口文件',
    publish     bigint       not null default 0 comment '发布',
    review      varchar(10)  not null default 'NONE' comment '审核',
    create_time datetime     not null default (utc_timestamp()) comment '创建时间',
    update_time datetime     not null default (utc_timestamp()) comment '修改时间',
    deleted     bigint       not null default 0,
    version     int          not null default 0,
    constraint t_b_tool_main_unique_tool_id_ver unique (tool_id, author_id, ver, deleted),
    constraint t_b_tool_main_unique_tool_id_publish unique (tool_id, author_id, publish, deleted)
) comment '工具-主表';