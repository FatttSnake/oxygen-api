drop table if exists t_operation;

create table if not exists t_operation
(
    id         bigint      not null primary key,
    name       varchar(50) not null comment '功能名',
    code       varchar(50) null comment '功能编码',
    element_id bigint      not null comment '页面元素ID'
) comment '功能表';