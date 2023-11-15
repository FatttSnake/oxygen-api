drop table if exists t_element;

create table if not exists t_element
(
    id        bigint       not null primary key,
    name      varchar(100) not null comment '元素名',
    parent_id bigint       null comment '父ID',
    menu_id   bigint       not null comment '菜单ID'
) comment '页面元素表';