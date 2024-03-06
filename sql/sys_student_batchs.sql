-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学员分期信息', '1', '1', 'batches', 'teach/batches/index', 1, 0, 'C', '0', '0', 'teach:batches:list', '#', 'admin', sysdate(), '', null, '学员分期信息菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学员分期信息查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'teach:batches:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学员分期信息新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'teach:batches:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学员分期信息修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'teach:batches:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学员分期信息删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'teach:batches:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学员分期信息导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'teach:batches:export',       '#', 'admin', sysdate(), '', null, '');



create table teach.sys_student_batches
(
    id           int auto_increment comment 'ID'
        primary key,
    batch_number varchar(50)  null comment '期数',
    start_date   date         null comment '开始时间',
    end_date     date         null comment '结束时间',
    description  varchar(255) null comment '详细信息',
    create_time  datetime     null comment '创建时间',
    update_time  datetime     null comment '更新时间',
    create_by    varchar(50)  null comment '创建者',
    update_by    varchar(50)  null comment '更新者'
);
