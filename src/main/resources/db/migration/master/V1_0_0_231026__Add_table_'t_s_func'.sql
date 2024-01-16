drop table if exists t_s_func;

create table if not exists t_s_func
(
    id        bigint       not null primary key,
    name      varchar(100) not null comment '功能名',
    parent_id bigint       null comment '父ID',
    menu_id   bigint       not null comment '菜单ID'
) comment '系统-功能表';