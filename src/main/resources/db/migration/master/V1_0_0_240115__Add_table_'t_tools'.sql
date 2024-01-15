drop table if exists t_tools;

create table if not exists t_tools (
    id bigint not null primary key ,
    name varchar(50) not null comment '工具名',
    tool_id varchar(50) not null comment '工具 ID',
    description varchar(500) null comment '简介',
    version varchar(20) not null comment '版本',
    private int not null default 0 comment '私有',
    keyword varchar(500) not null comment '关键字',
    category varchar(500) not null comment '类别'
)