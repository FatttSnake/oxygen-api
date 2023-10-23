drop table if exists t_power;

create table if not exists t_power
(
    `id`      bigint not null primary key,
    `type_id` bigint not null comment '权限类型'
) comment '权限';