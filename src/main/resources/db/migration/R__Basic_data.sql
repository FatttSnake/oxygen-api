insert into t_power_type (id, name)
    values (1, 'module'),
           (2, 'menu'),
           (3, 'element'),
           (4, 'operation') as new_value
on duplicate key update name = new_value.name;

insert into t_power (id, type_id)
    values (1000000, 1) as new_value
on duplicate key update type_id = new_value.type_id;

insert into t_module (id, name, power_id)
    values (1000000, '系统', id) as new_value
on duplicate key update name     = new_value.name,
                        power_id = new_value.power_id;

insert into t_menu (id, name, url, power_id, parent_id, module_id)
    values (1990000, '系统管理', '/system', id, null, 1000000),
           (1010000, '用户管理', '/system/user', id, 1990000, 1000000),
           (1020000, '角色管理', '/system/role', id, 1990000, 1000000),
           (1030000, '用户组管理', '/system/group', id, 1990000, 1000000) as new_value
on duplicate key update name      =new_value.name,
                        url       =new_value.url,
                        power_id  =new_value.power_id,
                        parent_id =new_value.parent_id;

insert into t_element(id, name, power_id, menu_id, parent_id)
    values (1010100, '查询', id, 1010000, null),
           (1010200, '增加', id, 1010000, 1010100),
           (1010300, '修改', id, 1010000, 1010100),
           (1020100, '查询', id, 1020000, null),
           (1020200, '增加', id, 1020000, 1020100),
           (1020300, '修改', id, 1020000, 1020100),
           (1020400, '删除', id, 1020000, 1020100),
           (1030100, '查询', id, 1030000, null),
           (1030200, '增加', id, 1030000, 1030100),
           (1030300, '修改', id, 1030000, 1030100),
           (1030400, '删除', id, 1030000, 1030100) as new_value
on duplicate key update name      = new_value.name,
                        power_id=new_value.power_id,
                        menu_id   = new_value.menu_id,
                        parent_id = new_value.parent_id;

insert into t_operation(id, name, code, power_id, element_id)
    values (1010101, '全部', 'system:user:query:all', id, 1010100),
           (1010102, '单个', 'system:user:query:one', id, 1010100),
           (1010201, '全部', 'system:user:add:all', id, 1010200),
           (1010301, '全部', 'system:user:modify:all', id, 1010300),
           (1020101, '全部', 'system:role:query:all', id, 1020100),
           (1020201, '全部', 'system:role:add:all', id, 1020200),
           (1020301, '全部', 'system:role:modify:all', id, 1020300),
           (1020401, '全部', 'system:role:delete:all', id, 1020400),
           (1030101, '全部', 'system:group:query:all', id, 1030100),
           (1030201, '全部', 'system:group:add:all', id, 1030200),
           (1030301, '全部', 'system:group:modify:all', id, 1030300),
           (1030401, '全部', 'system:group:delete:all', id, 1030400) as new_value
on duplicate key update name=new_value.name,
                        code=new_value.code,
                        power_id=new_value.power_id,
                        element_id=new_value.element_id;