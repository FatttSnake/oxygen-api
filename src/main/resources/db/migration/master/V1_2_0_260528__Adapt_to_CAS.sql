delete
from t_b_tool_favorite
where deleted != 0;

alter table t_b_tool_favorite
    drop column deleted;

drop table if exists t_b_tool_data;

alter table t_b_tool_main
    modify source_id bigint not null comment 'Source ID',
    modify dist_id bigint not null comment 'Dist ID';

alter table t_b_tool_template
    modify source_id bigint not null comment 'Source ID';
