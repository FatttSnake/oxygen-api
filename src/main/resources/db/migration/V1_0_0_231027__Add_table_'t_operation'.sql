drop table if exists t_operation;

create table if not exists t_operation
(
    id         bigint      not null primary key,
    name       varchar(50) not null comment '操作名',
    code       varchar(50) null comment '操作编码',
    func_id bigint      not null comment '功能ID'
) comment '操作表';