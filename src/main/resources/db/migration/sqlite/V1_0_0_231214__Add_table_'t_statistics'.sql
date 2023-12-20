drop table if exists t_statistics_log;

create table if not exists t_statistics_log -- 统计日志表
(
    id          bigint       not null primary key,
    key         varchar(50)  not null,                                               -- 记录键
    value       varchar(100) not null,                                               -- 记录值
    record_time datetime     not null default (strftime('%Y-%m-%d %H:%M:%f', 'now')) -- 记录时间
)