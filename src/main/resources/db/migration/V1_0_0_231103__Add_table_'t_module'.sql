drop table if exists t_module;

create table if not exists t_module
(
    id       bigint       not null primary key,
    name     varchar(100) not null comment '模块名',
    power_id bigint       not null comment '权限ID'
) comment '模块表';