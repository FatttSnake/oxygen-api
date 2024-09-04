drop table if exists t_r_role_group;

create table if not exists t_r_role_group
(
    id       bigint not null primary key,
    role_id  bigint not null comment '角色',
    group_id bigint not null comment '用户组',
    deleted  bigint not null default 0,
    version  int    not null default 0
) comment '中间表-系统-角色-用户组';