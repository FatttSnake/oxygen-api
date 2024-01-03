drop table if exists t_statistics_log;

create table if not exists t_statistics_log -- 统计日志表
(
    id          integer not null primary key,
    key         text    not null,                                               -- 记录键
    value       text    not null,                                               -- 记录值
    record_time text    not null default (strftime('%Y-%m-%d %H:%M:%f', 'now')) -- 记录时间
)