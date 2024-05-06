drop table if exists t_b_tool_favorite;

create table if not exists t_b_tool_favorite
(
    id        bigint      not null primary key,
    user_id   bigint      not null comment '用户 ID',
    author_id bigint      not null comment '作者 ID',
    tool_id   varchar(50) not null comment '工具 ID',
    deleted   bigint      not null default 0,
    version   int         not null default 0,
    constraint t_b_tool_favorite_unique_user_id_username_tool_id_platform unique (user_id, author_id, tool_id, deleted)
) comment '工具-收藏表';