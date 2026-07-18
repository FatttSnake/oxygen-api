drop table if exists t_l_sys_log_tmp;
create table t_l_sys_log_tmp -- Local - System Log
(
    id                     integer not null,
    log_type               text    not null,                                                -- Log type
    trace_id               text    null     default null,                                   -- Trace ID
    operate_user_id        integer not null,                                                -- Operation user ID
    operate_time           text    not null default (strftime('%Y-%m-%d %H:%M:%f', 'now')), -- Operation time
    request_uri            text    null     default null,                                   -- Request URI
    request_method         text    null     default null,                                   -- Request method
    request_params         text    null,                                                    -- Request parameters
    request_ip             text    not null,                                                -- Request IP
    request_server_address text    not null,                                                -- Request server address
    exception              integer not null default 0,                                      -- Exception status
    exception_info         text    null,                                                    -- Exception info
    start_time             text    not null,                                                -- Execution start time
    end_time               text    not null,                                                -- Execution end time
    execute_time           integer null     default null,                                   -- Execution takes time
    user_agent             text    null     default null                                    -- User agent
);

insert into t_l_sys_log_tmp(id, log_type, operate_user_id, operate_time, request_uri, request_method, request_params,
                            request_ip, request_server_address, exception, exception_info, start_time, end_time,
                            execute_time, user_agent)
select id,
       log_type,
       operate_user_id,
       operate_time,
       request_uri,
       request_method,
       request_params,
       request_ip,
       request_server_address,
       exception,
       exception_info,
       start_time,
       end_time,
       execute_time,
       user_agent
from t_l_sys_log;

drop table t_l_sys_log;

alter table t_l_sys_log_tmp
    rename to t_l_sys_log;
