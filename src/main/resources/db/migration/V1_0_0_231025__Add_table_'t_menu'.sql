drop table if exists t_menu;

create table if not exists t_menu
(
    id        bigint       not null primary key,
    name      varchar(30)  not null comment ' 菜单名',
    url       varchar(100) null comment 'URL',
    power_id  bigint       not null comment '权限ID',
    parent_id bigint       null comment '父ID'
) comment '菜单';