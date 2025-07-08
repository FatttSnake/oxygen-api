alter table t_s_user
    comment 'System - User',
    modify username varchar(20) not null comment 'Username',
    modify password char(70) not null comment 'Password',
    modify two_factor varchar(40) null comment 'Two-factor authentication secret key',
    modify verify varchar(144) null comment 'Email verification code',
    modify forget varchar(144) null comment 'Retrieve password verification code',
    modify locking int not null comment 'Locking status',
    modify expiration datetime null comment 'Expiration time',
    modify credentials_expiration datetime null comment 'Credentials expiration time',
    modify enable int not null comment 'Enable status',
    modify current_login_time datetime null comment 'Current login time',
    modify current_login_ip varchar(128) null comment 'Current login IP',
    modify last_login_time datetime null comment 'Last login time',
    modify last_login_ip varchar(128) null comment 'Last login IP',
    modify create_time datetime not null default (utc_timestamp()) comment 'Create time',
    modify update_time datetime not null default (utc_timestamp()) comment 'Update time';

alter table t_s_user_info
    comment 'System - User Info',
    modify user_id bigint not null comment 'User ID',
    modify nickname varchar(50) not null comment 'Nickname',
    modify avatar text null comment 'Avatar',
    modify email varchar(100) not null comment 'Email',
    modify create_time datetime not null default (utc_timestamp()) comment 'Create time',
    modify update_time datetime not null default (utc_timestamp()) comment 'Update time';

alter table t_s_power_type
    comment 'System - Power Type',
    modify name varchar(50) not null comment 'Name';

alter table t_s_power
    comment 'System - Power',
    modify type_id int not null comment 'Power type ID';

alter table t_s_module
    comment 'System - Module',
    modify name varchar(100) not null comment 'Name';

alter table t_s_menu
    comment 'System - Menu',
    modify name varchar(30) not null comment 'Name',
    modify url varchar(100) null comment 'URL',
    modify parent_id bigint null comment 'Parent menu ID',
    modify module_id bigint not null comment 'Module ID';

alter table t_s_func
    comment 'System - Function',
    modify name varchar(100) not null comment 'Name',
    modify parent_id bigint null comment 'Parent function ID',
    modify menu_id bigint not null comment 'Menu ID';

alter table t_s_operation
    comment 'System - Operation',
    modify name varchar(50) not null comment 'Name',
    modify code varchar(50) null comment 'Operation code',
    modify func_id bigint not null comment 'Function ID';

alter table t_s_group
    comment 'System - User Group',
    modify name varchar(30) not null comment 'Name',
    modify enable int not null comment 'Enable status',
    modify create_time datetime not null default (utc_timestamp()) comment 'Create time',
    modify update_time datetime not null default (utc_timestamp()) comment 'Update time';

alter table t_r_user_group
    comment 'Bridge - User - User Group',
    modify user_id bigint not null comment 'User ID',
    modify group_id bigint not null comment 'User group ID';

alter table t_s_role
    comment 'System - Role',
    modify name varchar(20) not null comment 'Name',
    modify enable int not null comment 'Enable status',
    modify create_time datetime not null default (utc_timestamp()) comment 'Create time',
    modify update_time datetime not null default (utc_timestamp()) comment 'Update time';

alter table t_r_role_group
    comment 'Bridge - Role - User Group',
    modify role_id bigint not null comment 'Role ID',
    modify group_id bigint not null comment 'User group ID';

alter table t_r_user_role
    comment 'Bridge - User - Role',
    modify user_id bigint not null comment 'User ID',
    modify role_id bigint not null comment 'Role ID';

alter table t_r_power_role
    comment 'Bridge - Power - Role',
    modify power_id bigint not null comment 'Power ID',
    modify role_id bigint not null comment 'Role ID';

alter table t_s_sensitive_word
    comment 'System - Sensitive World',
    modify word varchar(400) not null comment 'Word',
    modify use_for varchar(50) null comment 'Use for',
    modify enable int not null default 1 comment 'Enable status';

alter table t_b_tool_main
    comment 'Tool - Main',
    modify name varchar(50) not null comment 'Name',
    modify tool_id varchar(50) not null comment 'Tool Identification ID',
    modify icon text not null comment 'Icon',
    modify platform varchar(20) not null comment 'Platform',
    modify description varchar(500) null comment 'Description',
    modify base_id bigint not null comment 'Tool base ID',
    modify author_id bigint not null comment 'Author user ID',
    modify ver varchar(20) not null comment 'Version',
    modify keywords varchar(500) not null comment 'Keywords',
    modify source_id bigint not null comment 'Source data ID',
    modify dist_id bigint not null comment 'Dist data ID',
    modify entry_point varchar(64) not null default 'main.tsx' comment 'Entry file name',
    modify publish bigint not null default 0 comment 'Publish',
    modify review varchar(10) not null default 'NONE' comment 'Review',
    modify create_time datetime not null default (utc_timestamp()) comment 'Create time',
    modify update_time datetime not null default (utc_timestamp()) comment 'Update time';

alter table t_b_tool_category
    comment 'Tool - Category',
    modify name varchar(50) not null comment 'Name',
    modify enable int not null default 1 comment 'Enable status',
    modify create_time datetime not null default (utc_timestamp()) comment 'Create time',
    modify update_time datetime not null default (utc_timestamp()) comment 'Update time';

alter table t_r_tool_main_category
    comment 'Bridge - Tool Main - Tool Category',
    modify tool_id bigint not null comment 'Tool ID',
    modify category_id bigint not null comment 'Tool category ID';

alter table t_b_tool_data
    comment 'Tool - Data',
    modify data mediumtext not null comment 'Data',
    modify create_time datetime not null default (utc_timestamp()) comment 'Create time',
    modify update_time datetime not null default (utc_timestamp()) comment 'Update time';

alter table t_b_tool_template
    comment 'Tool - Template',
    modify name varchar(40) not null comment 'Name',
    modify base_id bigint not null comment 'Tool base ID',
    modify source_id bigint not null comment 'Source data ID',
    modify platform varchar(20) not null comment 'Platform',
    modify entry_point varchar(64) not null default 'main.tsx' comment 'Entry file name',
    modify enable int not null default 1 comment 'Enable status',
    modify create_time datetime not null default (utc_timestamp()) comment 'Create time',
    modify update_time datetime not null default (utc_timestamp()) comment 'Update time';

alter table t_b_tool_base
    comment 'Tool - Base',
    modify name varchar(20) not null comment 'Name',
    modify source_id bigint not null comment 'Source data ID',
    modify dist_id bigint not null comment 'Dist data ID',
    modify platform varchar(20) not null comment 'Platform',
    modify compiled int not null default 0 comment 'Compilation status',
    modify create_time datetime not null default (utc_timestamp()) comment 'Create time',
    modify update_time datetime not null default (utc_timestamp()) comment 'Update time';

alter table t_b_tool_favorite
    comment 'Tool - Favorite',
    modify user_id bigint not null comment 'User ID',
    modify author_id bigint not null comment 'Author user ID',
    modify tool_id varchar(50) not null comment 'Tool Identification ID';

delete
from flyway_schema_history
where true;

insert into flyway_schema_history
values (1, '1.0.0.231019', 'Add system tables', 'SQL', 'V1_0_0_231019__Add_system_tables.sql', 537766911,
        substring_index(system_user(), '@', 1), current_timestamp(), 0, 1),
       (2, '1.0.0.240115', 'Add tool tables', 'SQL', 'V1_0_0_240115__Add_tool_tables.sql', -1494464126,
        substring_index(system_user(), '@', 1), current_timestamp(), 0, 1),
       (3, null, 'Basic data', 'SQL', 'R__Basic_data.sql', -1905876221, substring_index(system_user(), '@', 1),
        current_timestamp(), 0, 1)
