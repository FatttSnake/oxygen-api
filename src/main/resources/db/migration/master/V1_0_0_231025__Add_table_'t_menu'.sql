drop table if exists t_menu;

create table if not exists t_menu
(
    id        bigint       not null primary key,
    name      varchar(30)  not null comment ' 菜单名',
    url       varchar(100) null comment 'URL',
    parent_id bigint       null comment '父ID',
    module_id bigint       not null comment '模块ID'
) comment '菜单表';