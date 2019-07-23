-- ----------------------------
-- 创建数据库
-- ----------------------------
DROP DATABASE IF EXISTS sugar;
CREATE DATABASE IF NOT EXISTS sugar DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE sugar;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 创建用户表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `account` varchar(45) DEFAULT NULL COMMENT '账号',
  `password` varchar(45) DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) DEFAULT NULL COMMENT '密码盐',
  `user_name` varchar(45) DEFAULT NULL COMMENT '用户名字',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` int(1) DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(45) DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话',
  `status` int(1) DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `creator` varchar(45) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(45) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT='用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- 初始化用户表数据
-- ----------------------------
INSERT INTO `sys_user`(`user_id`, `account`, `password`, `salt`, `user_name`, `birthday`, `sex`, `email`, `phone`, `status`, `creator`, `create_time`, `modifier`, `modify_time`) VALUES (1000000234, 'admin', 'a8afcf856029ccf5d6c4afec7d5e38e3', 'sugar', '系统管理员', '2019-06-05 15:31:00', 1, 'marklogzhu.163.com', '1358872xxxx', 1, 'suagr', '2019-06-20 12:31:00', 'sugar', '2019-06-20 12:31:00');


-- ----------------------------
-- 创建菜单权限表
-- ----------------------------
drop table if exists sys_menu;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
	`menu_code` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `display_order` int(4) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) DEFAULT '#' COMMENT '请求地址',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` int(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `creator` varchar(45) DEFAULT '' COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(45) DEFAULT '' COMMENT '更新者',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
	`del_flag` tinyint(1) DEFAULT '0'COMMENT '是否删除标记',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单权限表';


-- ----------------------------
-- 初始化菜单表数据
-- ----------------------------
-- 一级菜单
INSERT INTO sys_menu values('10001','系统管理','sys_management','0','1','#','M','0','fa fa-gear','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('10002','系统监控','sys_monitor','0','2','#','M','0','fa fa-gear','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('10003','系统工具','sys_tool','0','3','#','M','0','fa fa-gear','admin',NOW(),'admin',NOW(),'0','');
-- 二级菜单
INSERT INTO sys_menu values('10101','用户管理','sys_management_user','10001','1','/sys/user/managementPage','C','0','fa fa-gear','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('10102','菜单管理','sys_management_menu','10001','2','/sys/menu/managementPage','C','0','fa fa-gear','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('10103','角色管理','sys_management_role','10001','3','/sys/role/managementPage','C','0','fa fa-gear','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('10104','字典管理','sys_management_dict','10001','4','/sys/dict/managementPage','C','0','fa fa-gear','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('10105','日志管理','sys_management_log','10001','5','/sys/log/managementPage','C','0','fa fa-gear','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('10201','数据库监控','sys_monitor_database','10002','1','/sys/monitor/database/managementPage','C','0','fa fa-gear','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('10301','接口文档','sys_tool_swagger','10003','1','/sys/monitor/database/managementPage','C','0','fa fa-gear','admin',NOW(),'admin',NOW(),'0','');
-- 三级菜单
-- 用户管理按钮
INSERT INTO sys_menu values('11001','用户查询','sys_management_user_query','10101','1','#','F','0','#','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('11002','用户新增','sys_management_user_add','10101','2','#','F','0','#','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('11003','用户修改','sys_management_user_edit','10101','3','#','F','0','#','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('11004','用户删除','sys_management_user_remove','10101','4','#','F','0','#','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('11005','重置密码','sys_management_user_resetPwd','10101','5','#','F','0','#','admin',NOW(),'admin',NOW(),'0','');
-- 菜单管理按钮
INSERT INTO sys_menu values('11101','菜单查询','sys_management_menu_query','10102','1','#','F','0','#','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('11102','菜单新增','sys_management_menu_add','10102','2','#','F','0','#','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('11103','菜单修改','sys_management_menu_edit','10102','3','#','F','0','#','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('11104','菜单删除','sys_management_menu_remove','10102','4','#','F','0','#','admin',NOW(),'admin',NOW(),'0','');
-- 角色管理按钮
INSERT INTO sys_menu values('11201','角色查询','sys_management_role_query','10103','1','#','F','0','#','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('11202','角色新增','sys_management_role_add','10103','2','#','F','0','#','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('11203','角色修改','sys_management_role_edit','10103','3','#','F','0','#','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('11204','角色删除','sys_management_role_remove','10103','4','#','F','0','#','admin',NOW(),'admin',NOW(),'0','');
-- 字典管理按钮
INSERT INTO sys_menu values('11301','字典查询','sys_management_menu_query','10104','1','#','F','0','#','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('11302','字典新增','sys_management_menu_add','10104','2','#','F','0','#','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('11303','字典修改','sys_management_menu_edit','10104','3','#','F','0','#','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('11304','字典删除','sys_management_menu_remove','10104','4','#','F','0','#','admin',NOW(),'admin',NOW(),'0','');
INSERT INTO sys_menu values('11305','字典导出','sys_management_menu_export','10104','5','#','F','0','#','admin',NOW(),'admin',NOW(),'0','');



SET FOREIGN_KEY_CHECKS = 1;