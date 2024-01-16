drop table if exists t_r_power_role;

create table if not exists t_r_power_role
(
    id       bigint not null primary key,
    power_id bigint not null comment '权限',
    role_id  bigint not null comment '角色',
    deleted  bigint not null default 0,
    version  int    not null default 0
) comment '中间表-系统-权限-角色';