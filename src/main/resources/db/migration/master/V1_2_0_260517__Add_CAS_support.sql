drop table if exists t_s_storage_blob;
create table t_s_storage_blob
(
    file_hash       varchar(64) primary key comment 'Original file SHA-256 key',
    reference_count bigint   not null comment 'Blob reference count',
    create_time     datetime not null default (utc_timestamp()) comment 'Create time',
    update_time     datetime not null default (utc_timestamp()) comment 'Update time',
    version         int      not null default 0
) comment 'System - Storage blob';

drop table if exists t_b_tool_source;
create table t_b_tool_source
(
    id          bigint       not null primary key,
    root_id     bigint       not null comment 'Root node ID',
    parent_id   bigint       null comment 'Parent node ID',
    file_name   varchar(255) not null comment 'File name',
    root_node   int          not null comment 'Is root node',
    dir_node    int          not null comment 'Is directory node',
    create_time datetime     not null default (utc_timestamp()) comment 'Create time',
    update_time datetime     not null default (utc_timestamp()) comment 'Update time',
    version     int          not null default 0,
    constraint t_b_tool_storage_node_check_file_name check ((root_node = 1 and file_name = '') or (root_node = 0 and file_name != '')),
    constraint t_b_tool_storage_node_unique_parent_file_name_dir unique (parent_id, file_name, dir_node)
) comment 'Tool - Source';

drop table if exists t_b_tool_file_version;
create table t_b_tool_file_version
(
    id          bigint      not null primary key,
    node_id     bigint      not null comment 'Source node ID',
    ver         int         not null comment 'File version',
    file_hash   varchar(64) not null comment 'File SHA-256 key',
    file_size   bigint      not null comment 'File size',
    create_time datetime    not null default (utc_timestamp()) comment 'Create time',
    update_time datetime    not null default (utc_timestamp()) comment 'Update time',
    version     int         not null default 0,
    constraint t_b_tool_file_version_unique_node_ver unique (node_id, ver)
) comment 'Tool - File version';

drop table if exists t_b_tool_dist;
create table t_b_tool_dist
(
    id          bigint      not null primary key,
    file_hash   varchar(64) not null comment 'File SHA-256 key',
    file_size   bigint      not null comment 'File size',
    create_time datetime    not null default (utc_timestamp()) comment 'Create time',
    update_time datetime    not null default (utc_timestamp()) comment 'Update time',
    version     int         not null default 0
) comment 'Tool - Dist file';
