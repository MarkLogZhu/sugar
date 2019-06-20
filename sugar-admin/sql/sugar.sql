DROP DATABASE IF EXISTS sugar;
CREATE DATABASE IF NOT EXISTS sugar DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE sugar;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `account` varchar(45) DEFAULT NULL COMMENT '账号',
  `password` varchar(45) DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) DEFAULT NULL COMMENT '密码盐',
  `user_name` varchar(45) DEFAULT NULL COMMENT '用户名字',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` int(11) DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(45) DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话',
  `status` int(11) DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `creator` varchar(45) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(45) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000236 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT='用户表' ROW_FORMAT = Dynamic;

INSERT INTO `sys_user`(`user_id`, `account`, `password`, `salt`, `user_name`, `birthday`, `sex`, `email`, `phone`, `status`, `creator`, `create_time`, `modifier`, `modify_time`) VALUES (1000000234, 'admin', 'a8afcf856029ccf5d6c4afec7d5e38e3', 'sugar', 'admin', '2019-06-05 15:31:00', 1, 'marklogzhu.163.com', '1358872xxxx', 1, 'suagr', '2019-06-20 12:31:00', 'sugar', '2019-06-20 12:31:00');



SET FOREIGN_KEY_CHECKS = 1;