/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : lottery

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2018-03-14 03:24:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_area
-- ----------------------------
DROP TABLE IF EXISTS `t_area`;
CREATE TABLE `t_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `area_code` varchar(3) NOT NULL COMMENT '区域代码',
  `area_name` varchar(50) NOT NULL COMMENT '区域名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域设置';

-- ----------------------------
-- Table structure for t_currency
-- ----------------------------
DROP TABLE IF EXISTS `t_currency`;
CREATE TABLE `t_currency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `currency_code` varchar(3) NOT NULL COMMENT '货币代码',
  `currency_name` varchar(50) NOT NULL COMMENT '货币名称',
  `exchange_rate` decimal(10,4) NOT NULL DEFAULT '1.0000' COMMENT '汇率（1 人民币 = xx 外币，默认人民币）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='货币设置';

-- ----------------------------
-- Table structure for t_game
-- ----------------------------
DROP TABLE IF EXISTS `t_game`;
CREATE TABLE `t_game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_name` varchar(50) NOT NULL COMMENT '游戏名称',
  `odds` int(11) NOT NULL DEFAULT '0' COMMENT '赔率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='游戏类型';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `currency_code` varchar(3) DEFAULT NULL COMMENT '货币',
  `first_name` varchar(3) DEFAULT NULL COMMENT '字头',
  `account` varchar(50) NOT NULL COMMENT '用户账号（账号规则：区域+货币+流水号）',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `verification_code` varchar(50) DEFAULT NULL COMMENT '操作验证码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `user_type` smallint(6) NOT NULL DEFAULT '0' COMMENT '用户类型（0 会员 1 代理 2 公司 3 代理子账号）',
  `parent_id` int(11) DEFAULT '0' COMMENT '上级用户',
  `user_level` smallint(6) NOT NULL DEFAULT '0' COMMENT '用户级别（0 会员 1、2、3、4 ... 代理级别）',
  `is_pause` smallint(6) NOT NULL DEFAULT '1' COMMENT '账号是否停用（0 停用 1 正常）',
  `can_bet` smallint(6) NOT NULL DEFAULT '1' COMMENT '账号可下注（0 停押 1 正常）',
  `comission` int(11) NOT NULL DEFAULT '0' COMMENT '占成',
  `pause_user` int(11) DEFAULT NULL COMMENT '账号停用人',
  `staked_user` int(11) DEFAULT NULL COMMENT '账号停押人',
  `max_level` int(11) NOT NULL DEFAULT '10' COMMENT '下属最多级别（默认10级）',
  `is_anonymous` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否隐形账号（1 隐形）',
  `master_id` int(11) NOT NULL DEFAULT '0' COMMENT '主账号（当存在有子账号时非0）',
  `next_time` int(11) NOT NULL DEFAULT '0' COMMENT '下次修改密码时间（0 为不需要修改，如需要修改，则为unix时间戳）',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` int(11) NOT NULL DEFAULT '0' COMMENT '更新日期（unix时间戳）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for t_user_game_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_user_game_rel`;
CREATE TABLE `t_user_game_rel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `game_id` int(11) NOT NULL COMMENT '游戏ID',
  `all_comission` int(11) NOT NULL COMMENT '总占成',
  `comission` int(11) NOT NULL COMMENT '用户占成',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户游戏关联表';

-- ----------------------------
-- Table structure for t_user_login_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_login_info`;
CREATE TABLE `t_user_login_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `account` varchar(50) NOT NULL COMMENT '用户账号',
  `token` varchar(50) NOT NULL COMMENT '登录凭证',
  `login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
  `logout_time` datetime DEFAULT NULL COMMENT '退出时间',
  `login_ip` varchar(50) NOT NULL COMMENT '登录IP',
  `status` smallint(6) NOT NULL DEFAULT '1' COMMENT '状态（0 正常退出 1 正常在线 2 异常退出）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录日志表';
