drop table if exists t_user_group;

create table if not exists t_user_group
(
    id       bigint not null primary key,
    user_id  bigint not null comment '用户',
    group_id bigint not null comment '用户组',
    deleted  bigint not null default 0,
    version  int    not null default 0
) comment '中间表-用户-用户组';