drop table if exists t_power_type;

create table if not exists t_power_type
(
    id   bigint      not null primary key,
    name varchar(50) not null comment '权限类型名'
) comment '权限类型';