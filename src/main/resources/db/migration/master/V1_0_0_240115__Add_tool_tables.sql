drop table if exists t_b_tool_main;
create table t_b_tool_main
(
    id          bigint       not null primary key,
    name        varchar(50)  not null comment 'Name',
    tool_id     varchar(50)  not null comment 'Tool Identification ID',
    icon        text         not null comment 'Icon',
    platform    varchar(20)  not null comment 'Platform',
    description varchar(500) null comment 'Description',
    base_id     bigint       not null comment 'Tool base ID',
    author_id   bigint       not null comment 'Author user ID',
    ver         varchar(20)  not null comment 'Version',
    keywords    varchar(500) not null comment 'Keywords',
    source_id   bigint       not null comment 'Source data ID',
    dist_id     bigint       not null comment 'Dist data ID',
    entry_point varchar(64)  not null default 'main.tsx' comment 'Entry file name',
    publish     bigint       not null default 0 comment 'Publish',
    review      varchar(10)  not null default 'NONE' comment 'Review',
    create_time datetime     not null default (utc_timestamp()) comment 'Create time',
    update_time datetime     not null default (utc_timestamp()) comment 'Update time',
    deleted     bigint       not null default 0,
    version     int          not null default 0,
    constraint t_b_tool_main_unique_tool_id_platform_author_ver unique (tool_id, platform, author_id, ver, deleted),
    constraint t_b_tool_main_unique_tool_id_platform_author_publish unique (tool_id, platform, author_id, publish, deleted)
) comment 'Tool - Main';

drop table if exists t_b_tool_category;
create table t_b_tool_category
(
    id          bigint      not null primary key,
    name        varchar(50) not null comment 'Name',
    enable      int         not null default 1 comment 'Enable status',
    create_time datetime    not null default (utc_timestamp()) comment 'Create time',
    update_time datetime    not null default (utc_timestamp()) comment 'Update time',
    deleted     bigint      not null default 0,
    version     int         not null default 0,
    constraint t_tool_category_name unique (name, deleted)
) comment 'Tool - Category';

drop table if exists t_r_tool_main_category;
create table t_r_tool_main_category
(
    id          bigint not null primary key,
    tool_id     bigint not null comment 'Tool ID',
    category_id bigint not null comment 'Tool category ID',
    deleted     bigint not null default 0,
    version     int    not null default 0
) comment 'Bridge - Tool Main - Tool Category';

drop table if exists t_b_tool_data;
create table t_b_tool_data
(
    id          bigint     not null primary key,
    data        mediumtext not null comment 'Data',
    create_time datetime   not null default (utc_timestamp()) comment 'Create time',
    update_time datetime   not null default (utc_timestamp()) comment 'Update time',
    deleted     bigint     not null default 0,
    version     int        not null default 0
) comment 'Tool - Data';

drop table if exists t_b_tool_template;
create table t_b_tool_template
(
    id          bigint      not null primary key,
    name        varchar(40) not null comment 'Name',
    base_id     bigint      not null comment 'Tool base ID',
    source_id   bigint      not null comment 'Source data ID',
    platform    varchar(20) not null comment 'Platform',
    entry_point varchar(64) not null default 'main.tsx' comment 'Entry file name',
    enable      int         not null default 1 comment 'Enable status',
    create_time datetime    not null default (utc_timestamp()) comment 'Create time',
    update_time datetime    not null default (utc_timestamp()) comment 'Update time',
    deleted     bigint      not null default 0,
    version     int         not null default 0,
    constraint t_b_tool_template_unique_name_platform unique (name, platform, deleted)
) comment 'Tool - Template';

drop table if exists t_b_tool_base;
create table t_b_tool_base
(
    id          bigint      not null primary key,
    name        varchar(20) not null comment 'Name',
    source_id   bigint      not null comment 'Source data ID',
    dist_id     bigint      not null comment 'Dist data ID',
    platform    varchar(20) not null comment 'Platform',
    compiled    int         not null default 0 comment 'Compilation status',
    create_time datetime    not null default (utc_timestamp()) comment 'Create time',
    update_time datetime    not null default (utc_timestamp()) comment 'Update time',
    deleted     bigint      not null default 0,
    version     int         not null default 0,
    constraint t_b_tool_base_unique_name_platform unique (name, platform, deleted)
) comment 'Tool - Base';

drop table if exists t_b_tool_favorite;
create table t_b_tool_favorite
(
    id        bigint      not null primary key,
    user_id   bigint      not null comment 'User ID',
    author_id bigint      not null comment 'Author user ID',
    tool_id   varchar(50) not null comment 'Tool Identification ID',
    deleted   bigint      not null default 0,
    version   int         not null default 0,
    constraint t_b_tool_favorite_unique_user_id_username_tool_id_platform unique (user_id, author_id, tool_id, deleted)
) comment 'Tool - Favorite';
