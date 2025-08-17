delete
from flyway_schema_history
where true;

insert into flyway_schema_history
values (1, '1.0.0.231212', 'Add log tables', 'SQL', 'V1_0_0_231212__Add_log_tables.sql', 1989079402, '',
        strftime('%Y-%m-%d %H:%M:%f', 'now'), 0, 1);
