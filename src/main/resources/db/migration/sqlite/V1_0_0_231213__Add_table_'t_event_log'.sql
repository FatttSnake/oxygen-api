drop table if exists t_event_log;

create table if not exists t_event_log -- 事件日志表
(
    id              integer not null primary key,
    event           text    not null,                                               -- 事件,
    operate_user_id integer not null,                                               -- 操作用户
    operate_time    text    not null default (strftime('%Y-%m-%d %H:%M:%f', 'now')) -- 操作时间
);