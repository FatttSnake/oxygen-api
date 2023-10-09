drop table if exists t_user;

create table if not exists t_user
(
    id       bigint      not null primary key,
    username varchar(20) not null comment '用户名',
    password char(70)    not null comment '密码',
    enable   int         not null comment '启用',
    deleted  bigint      not null default 0,
    version  int         not null default 0,
    constraint t_user_unique unique (username, deleted)
) comment '用户';
