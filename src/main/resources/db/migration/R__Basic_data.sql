insert into t_power_type (id, name)
values (1, 'menu'),
       (2, 'element'),
       (3, 'operation')
on duplicate key update name = values(name);

insert into t_power (id, type_id)
values (1010000, 1),
       (1010100, 2),
       (1010101, 3),
       (100010000, 1),
       (101010000, 1),
       (101010100, 2),
       (101010101, 3),
       (101010102, 3),
       (101010103, 3),
       (101010104, 3),
       (102010000, 1),
       (102010100, 2),
       (102010101, 3),
       (102010102, 3),
       (102010103, 3),
       (102010104, 3),
       (103010000, 1),
       (103010100, 2),
       (103010101, 3),
       (103010102, 3),
       (103010103, 3),
       (103010104, 3),
       (103010105, 3)
on duplicate key update type_id = values(type_id);

insert into t_menu (id, name, url, power_id, parent_id)
values (001010000, '公用', '/', id, null),
       (100010000, '系统管理', '/system', id, null),
       (101010000, '角色管理（权限相关）', '/power/role', id, 100010000),
       (102010000, '用户组管理（权限相关）', '/power/group', id, 100010000),
       (103010000, '用户管理（权限相关）', '/power/user', id, 100010000)
on duplicate key update name      = values(name),
                        url       = values(url),
                        power_id  = values(power_id),
                        parent_id = values(parent_id);

insert into t_element(id, name, power_id, menu_id, parent_id)
values (1010100, '公用', id, 1010000, null),
       (101010100, '角色基础', id, 101010000, null),
       (102010100, '用户组基础', id, 102010000, null),
       (103010100, '用户基础', id, 103010000, null)
on duplicate key update name      = values(name),
                        power_id=values(power_id),
                        menu_id   = values(menu_id),
                        parent_id = values(parent_id);

insert into t_operation(id, name, code, power_id, element_id, parent_id)
values (1010101, '查询当前用户信息', 'common:user:self', id, 1010100, null),
       (101010101, '查询所有角色', 'system:role:get', id, 101010100, null),
       (101010102, '添加角色', 'system:role:add', id, 101010100, null),
       (101010103, '删除角色', 'system:role:delete', id, 101010100, null),
       (101010104, '修改角色', 'system:role:modify', id, 101010100, null),
       (102010101, '查询所有用户组', 'system:group:get', id, 102010100, null),
       (102010102, '添加用户组', 'system:group:add', id, 102010100, null),
       (102010103, '删除用户组', 'system:group:delete', id, 102010100, null),
       (102010104, '修改用户组', 'system:group:modify', id, 102010100, null),
       (103010101, '查看所有用户', 'system:user:get', id, 103010100, null),
       (103010102, '查看单个用户', 'system:user:one', id, 103010100, null),
       (103010103, '添加用户', 'system:user:add', id, 103010100, null),
       (103010104, '删除用户', 'system:user:delete', id, 103010100, null),
       (103010105, '修改用户', 'system:user:modify', id, 103010100, null)
on duplicate key update name=values(name),
                        code=values(code),
                        power_id=values(power_id),
                        element_id=values(element_id),
                        parent_id=values(parent_id);