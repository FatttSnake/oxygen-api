drop table if exists t_operation;

create table if not exists t_operation
(
    `id`         bigint      not null primary key,
    `name`       varchar(50) not null comment '功能名',
    `code`       varchar(50) null comment '功能编码',
    `power_id`   bigint      not null comment '权限ID',
    `parent_id`  bigint      null comment '父ID',
    `element_id` bigint      not null comment '页面元素ID'
) comment '功能';