insert into t_power_type (id, name)
    values (1, 'module'),
           (2, 'menu'),
           (3, 'element'),
           (4, 'operation') as new_value
on duplicate key update name = new_value.name;

insert into t_power (id, type_id)
    values (1000000, 1),
           (1990000, 2),
           (1010000, 2),
           (1020000, 2),
           (1030000, 2),
           (1010100, 3),
           (1010200, 3),
           (1010300, 3),
           (1020100, 3),
           (1020200, 3),
           (1020300, 3),
           (1020400, 3),
           (1030100, 3),
           (1030200, 3),
           (1030300, 3),
           (1030400, 3),
           (1010101, 4),
           (1010102, 4),
           (1010201, 4),
           (1010301, 4),
           (1020101, 4),
           (1020201, 4),
           (1020301, 4),
           (1020401, 4),
           (1030101, 4),
           (1030201, 4),
           (1030301, 4),
           (1030401, 4)
        as new_value
on duplicate key update type_id = new_value.type_id;

insert into t_module (id, name)
    values (1000000, '系统') as new_value
on duplicate key update name     = new_value.name;

insert into t_menu (id, name, url, parent_id, module_id)
    values (1990000, '系统管理', '/system', null, 1000000),
           (1010000, '用户管理', '/system/user', 1990000, 1000000),
           (1020000, '角色管理', '/system/role', 1990000, 1000000),
           (1030000, '用户组管理', '/system/group', 1990000, 1000000) as new_value
on duplicate key update name      =new_value.name,
                        url       =new_value.url,
                        parent_id =new_value.parent_id;

insert into t_element(id, name, menu_id, parent_id)
    values (1010100, '查询', 1010000, null),
           (1010200, '增加', 1010000, 1010100),
           (1010300, '修改', 1010000, 1010100),
           (1020100, '查询', 1020000, null),
           (1020200, '增加', 1020000, 1020100),
           (1020300, '修改', 1020000, 1020100),
           (1020400, '删除', 1020000, 1020100),
           (1030100, '查询', 1030000, null),
           (1030200, '增加', 1030000, 1030100),
           (1030300, '修改', 1030000, 1030100),
           (1030400, '删除', 1030000, 1030100) as new_value
on duplicate key update name      = new_value.name,
                        menu_id   = new_value.menu_id,
                        parent_id = new_value.parent_id;

insert into t_operation(id, name, code, element_id)
    values (1010101, '全部', 'system:user:query:all', 1010100),
           (1010102, '单个', 'system:user:query:one', 1010100),
           (1010201, '全部', 'system:user:add:all', 1010200),
           (1010301, '全部', 'system:user:modify:all', 1010300),
           (1020101, '全部', 'system:role:query:all', 1020100),
           (1020201, '全部', 'system:role:add:all', 1020200),
           (1020301, '全部', 'system:role:modify:all', 1020300),
           (1020401, '全部', 'system:role:delete:all', 1020400),
           (1030101, '全部', 'system:group:query:all', 1030100),
           (1030201, '全部', 'system:group:add:all', 1030200),
           (1030301, '全部', 'system:group:modify:all', 1030300),
           (1030401, '全部', 'system:group:delete:all', 1030400) as new_value
on duplicate key update name=new_value.name,
                        code=new_value.code,
                        element_id=new_value.element_id;