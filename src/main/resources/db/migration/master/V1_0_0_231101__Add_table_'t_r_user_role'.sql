drop table if exists t_r_user_role;

create table if not exists t_r_user_role
(
    id      bigint not null primary key,
    user_id bigint not null comment '用户',
    role_id bigint not null comment '角色',
    deleted bigint not null default 0,
    version int    not null default 0
) comment '中间表-系统-用户-角色';