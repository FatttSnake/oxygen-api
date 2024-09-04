drop table if exists t_s_power;

create table if not exists t_s_power
(
    id      bigint not null primary key,
    type_id int    not null comment '权限类型'
) comment '系统-权限表';