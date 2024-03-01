insert into t_s_power_type (id, name)
    values (1, 'module'),
           (2, 'menu'),
           (3, 'func'),
           (4, 'operation') as new_value
on duplicate key update name = new_value.name;

insert into t_s_power (id, type_id)
    values (1000000, 1),
           (1990000, 2),
           (1010000, 2),
           (1020000, 2),
           (1030000, 2),
           (1040000, 2),
           (1510000, 2),
           (1520000, 2),
           (1530000, 2),
           (1540000, 2),
           (1010100, 3),
           (1010200, 3),
           (1010300, 3),
           (1010400, 3),
           (1020100, 3),
           (1020200, 3),
           (1020300, 3),
           (1020400, 3),
           (1030100, 3),
           (1030200, 3),
           (1030300, 3),
           (1030400, 3),
           (1040100, 3),
           (1510100, 3),
           (1520100, 3),
           (1530100, 3),
           (1530300, 3),
           (1540100, 3),
           (1540200, 3),
           (1540300, 3),
           (1540400, 3),
           (1010101, 4),
           (1010102, 4),
           (1010103, 4),
           (1010201, 4),
           (1010301, 4),
           (1010302, 4),
           (1010401, 4),
           (1010402, 4),
           (1020101, 4),
           (1020102, 4),
           (1020103, 4),
           (1020201, 4),
           (1020301, 4),
           (1020302, 4),
           (1020401, 4),
           (1020402, 4),
           (1030101, 4),
           (1030102, 4),
           (1030103, 4),
           (1030201, 4),
           (1030301, 4),
           (1030302, 4),
           (1030401, 4),
           (1030402, 4),
           (1040103, 4),
           (1510101, 4),
           (1510102, 4),
           (1510103, 4),
           (1520101, 4),
           (1530101, 4),
           (1530102, 4),
           (1530103, 4),
           (1530104, 4),
           (1530301, 4),
           (1530302, 4),
           (1530303, 4),
           (1530304, 4),
           (1540101, 4),
           (1540102, 4),
           (1540103, 4),
           (1540104, 4),
           (1540201, 4),
           (1540202, 4),
           (1540203, 4),
           (1540204, 4),
           (1540301, 4),
           (1540302, 4),
           (1540303, 4),
           (1540304, 4),
           (1540401, 4),
           (1540402, 4),
           (1540403, 4),
           (1540404, 4) as new_value
on duplicate key update type_id = new_value.type_id;

insert into t_s_module (id, name)
    values (1000000, '系统') as new_value
on duplicate key update name = new_value.name;

insert into t_s_menu (id, name, url, parent_id, module_id)
    values (1990000, '系统管理', '^/system$', null, 1000000),
           (1010000, '用户管理', '^/system/user$', 1990000, 1000000),
           (1020000, '角色管理', '^/system/role$', 1990000, 1000000),
           (1030000, '用户组管理', '^/system/group$', 1990000, 1000000),
           (1040000, '权限管理', '^/system/power$', 1990000, 1000000),
           (1510000, '系统概况', '^/system/statistics$', 1990000, 1000000),
           (1520000, '日志管理', '^/system/log$', 1990000, 1000000),
           (1530000, '系统设置', '^/system/settings$', 1990000, 1000000),
           (1540000, '工具配置', '^/system/tools(/.*)?$', 1990000, 1000000) as new_value
on duplicate key update name      =new_value.name,
                        url       =new_value.url,
                        parent_id =new_value.parent_id;

insert into t_s_func(id, name, menu_id, parent_id)
    values (1010100, '查询', 1010000, null),
           (1010200, '增加', 1010000, null),
           (1010300, '修改', 1010000, null),
           (1010400, '删除', 1010000, null),
           (1020100, '查询', 1020000, null),
           (1020200, '增加', 1020000, null),
           (1020300, '修改', 1020000, null),
           (1020400, '删除', 1020000, null),
           (1030100, '查询', 1030000, null),
           (1030200, '增加', 1030000, null),
           (1030300, '修改', 1030000, null),
           (1030400, '删除', 1030000, null),
           (1040100, '查询', 1040000, null),
           (1510100, '查询', 1510000, null),
           (1520100, '查询', 1520000, null),
           (1530100, '查询', 1530000, null),
           (1530300, '修改', 1530000, null),
           (1540100, '查询', 1540000, null),
           (1540200, '增加', 1540000, null),
           (1540300, '修改', 1540000, null),
           (1540400, '删除', 1540000, null) as new_value
on duplicate key update name      = new_value.name,
                        menu_id   = new_value.menu_id,
                        parent_id = new_value.parent_id;

insert into t_s_operation(id, name, code, func_id)
    values (1010101, '单个', 'system:user:query:one', 1010100),
           (1010102, '全部', 'system:user:query:all', 1010100),
           (1010103, '列表', 'system:user:query:list', 1010100),
           (1010201, '单个', 'system:user:add:one', 1010200),
           (1010301, '单个', 'system:user:modify:one', 1010300),
           (1010302, '密码', 'system:user:modify:password', 1010300),
           (1010401, '单个', 'system:user:delete:one', 1010400),
           (1010402, '多个', 'system:user:delete:multiple', 1010400),
           (1020101, '单个', 'system:role:query:one', 1020100),
           (1020102, '全部', 'system:role:query:all', 1020100),
           (1020103, '列表', 'system:role:query:list', 1020100),
           (1020201, '单个', 'system:role:add:one', 1020200),
           (1020301, '单个', 'system:role:modify:one', 1020300),
           (1020302, '状态', 'system:role:modify:status', 1020300),
           (1020401, '单个', 'system:role:delete:one', 1020400),
           (1020402, '多个', 'system:role:delete:multiple', 1020400),
           (1030101, '单个', 'system:group:query:one', 1030100),
           (1030102, '全部', 'system:group:query:all', 1030100),
           (1030103, '列表', 'system:group:query:list', 1030100),
           (1030201, '单个', 'system:group:add:one', 1030200),
           (1030301, '单个', 'system:group:modify:one', 1030300),
           (1030302, '状态', 'system:group:modify:status', 1030300),
           (1030401, '单个', 'system:group:delete:one', 1030400),
           (1030402, '多个', 'system:group:delete:multiple', 1030400),
           (1040103, '列表', 'system:power:query:list', 1040100),
           (1510101, '使用情况', 'system:statistics:query:usage', 1510100),
           (1510102, '基础信息', 'system:statistics:query:base', 1510100),
           (1510103, '实时信息', 'system:statistics:query:real', 1510100),
           (1520101, '全部', 'system:log:query:all', 1520100),
           (1530101, '基础', 'system:settings:query:base', 1530100),
           (1530102, '邮件', 'system:settings:query:mail', 1530100),
           (1530103, '敏感词', 'system:settings:query:sensitive', 1530100),
           (1530104, '双因素', 'system:settings:query:two-factor', 1530100),
           (1530301, '基础', 'system:settings:modify:base', 1530300),
           (1530302, '邮件', 'system:settings:modify:mail', 1530300),
           (1530303, '敏感词', 'system:settings:modify:sensitive', 1530300),
           (1530304, '双因素', 'system:settings:modify:two-factor', 1530300),
           (1540101, '类别', 'system:tool:query:category', 1540100),
           (1540102, '基板', 'system:tool:query:base', 1540100),
           (1540103, '模板', 'system:tool:query:template', 1540100),
           (1540104, '工具', 'system:tool:query:tool', 1540100),
           (1540201, '类别', 'system:tool:add:category', 1540200),
           (1540202, '基板', 'system:tool:add:base', 1540200),
           (1540203, '模板', 'system:tool:add:template', 1540200),
           (1540204, '工具', 'system:tool:add:tool', 1540200),
           (1540301, '类别', 'system:tool:modify:category', 1540300),
           (1540302, '基板', 'system:tool:modify:base', 1540300),
           (1540303, '模板', 'system:tool:modify:template', 1540300),
           (1540304, '工具', 'system:tool:modify:tool', 1540300),
           (1540401, '类别', 'system:tool:delete:category', 1540400),
           (1540402, '基板', 'system:tool:delete:base', 1540400),
           (1540403, '模板', 'system:tool:delete:template', 1540400),
           (1540404, '工具', 'system:tool:delete:tool', 1540400) as new_value
on duplicate key update name=new_value.name,
                        code=new_value.code,
                        func_id=new_value.func_id;