drop table if exists t_role;

create table if not exists t_role
(
    id      bigint      not null primary key,
    name    varchar(20) not null comment '角色名',
    enable  int         not null comment '启用',
    deleted bigint      not null default 0,
    version int         not null default 0,
    constraint t_role_unique unique (name, deleted)
) comment '角色';