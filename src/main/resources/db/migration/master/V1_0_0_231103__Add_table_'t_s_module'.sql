drop table if exists t_s_module;

create table if not exists t_s_module
(
    id   bigint       not null primary key,
    name varchar(100) not null comment '模块名'
) comment '系统-模块表';