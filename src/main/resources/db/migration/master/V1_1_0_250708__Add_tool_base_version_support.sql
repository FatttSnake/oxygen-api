set @VAR_CURRENT_TIME = floor(unix_timestamp(now(3)) * 1000);

alter table t_b_tool_main
    add base_version bigint;

update t_b_tool_main
set base_version = @VAR_CURRENT_TIME
where true;

alter table t_b_tool_main
    modify base_version bigint not null comment 'Tool base version' after base_id;

update t_b_tool_main
set review = 'NONE'
where review = 'REJECT';

alter table t_b_tool_template
    add base_version bigint;

update t_b_tool_template
set base_version = @VAR_CURRENT_TIME
where true;

alter table t_b_tool_template
    modify base_version bigint not null comment 'Tool base version' after base_id;

drop table if exists t_r_tool_base_data;
create table t_r_tool_base_data
(
    id           bigint      not null primary key,
    base_id      bigint      not null comment 'Tool base ID',
    data_id      bigint      not null comment 'Tool data ID',
    data_type    varchar(20) not null comment 'Tool data ID',
    base_version bigint      not null default 0 comment 'Tool base version',
    deleted      bigint      not null default 0,
    version      int         not null default 0,
    constraint t_base_version unique (base_id, data_type, base_version, deleted)
) comment 'Bridge - Tool Base - Tool Data';

delimiter //
create function next_snowflake_id(worker_id bigint, datacenter_id bigint) returns bigint
    deterministic
begin
    declare epoch bigint default 1288834974657;
    declare seq_max bigint default 4095;
    declare timestamp_diff bigint;
    declare sequence bigint;

    if worker_id < 0 or worker_id > 31 then
        signal sqlstate '45000' set message_text = 'workerId must be between 0 and 31';
    end if;

    if datacenter_id < 0 or datacenter_id > 31 then
        signal sqlstate '45000' set message_text = 'datacenterId must be between 0 and 31';
    end if;

    set timestamp_diff = floor(unix_timestamp(now(3)) * 1000) - epoch;

    set sequence = (select ifnull(max(sequence), 0) + 1
                    from (select id & 0xFFF as sequence
                          from t_r_tool_base_data
                          where id >> 22 = timestamp_diff) as temp) % (seq_max + 1);

    return (timestamp_diff << 22) | (datacenter_id << 17) | (worker_id << 12) | sequence;
end //
delimiter ;

delimiter //
create procedure migrate_tool_base_data()
begin
    declare done int default false;
    declare v_id, v_source_id, v_dist_id bigint;
    declare cur cursor for select id, source_id, dist_id from t_b_tool_base where deleted = 0;
    declare continue handler for not found set done = true;

    open cur;

    read_loop:
    LOOP
        fetch cur into v_id, v_source_id, v_dist_id;
        if done then
            leave read_loop;
        end if;

        if v_source_id is not null and v_source_id != 0 then
            insert into t_r_tool_base_data (id, base_id, data_id, data_type, base_version)
            values (next_snowflake_id(1, 1),
                    v_id,
                    v_source_id,
                    'SOURCE',
                    @VAR_CURRENT_TIME);
        end if;

        do sleep(0.001);

        if v_dist_id is not null and v_dist_id != 0 then
            insert into t_r_tool_base_data (id, base_id, data_id, data_type, base_version)
            values (next_snowflake_id(1, 1),
                    v_id,
                    v_dist_id,
                    'DIST',
                    @VAR_CURRENT_TIME);
        end if;

        do sleep(0.001);
    end loop;

    close cur;
end //
delimiter ;

call migrate_tool_base_data();

drop procedure if exists migrate_tool_base_data;
drop function if exists next_snowflake_id;

alter table t_b_tool_base
    drop source_id,
    drop dist_id,
    drop compiled;
