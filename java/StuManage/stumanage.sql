/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : stumanage

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2023-04-17 20:48:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz` (
  `clazz_id` int(11) NOT NULL AUTO_INCREMENT,
  `clazz_name` varchar(100) DEFAULT NULL,
  `note` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`clazz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES ('3', '初二', '初二年级的语文数学英语物理');
INSERT INTO `clazz` VALUES ('4', '初三1班', '初三年级的语文英语数学化学物理（能力较强）');
INSERT INTO `clazz` VALUES ('5', '初三2班', '（能力较弱）');
INSERT INTO `clazz` VALUES ('6', '三年级二班', '三年级二班');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(100) DEFAULT NULL,
  `course_type` int(11) DEFAULT '1' COMMENT '1-必修课 2-选修课',
  `teacher_id` int(11) DEFAULT NULL,
  `note` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '语文', '1', '0', '');
INSERT INTO `course` VALUES ('2', '数学', '1', '0', '');
INSERT INTO `course` VALUES ('3', '英语', '1', '0', '');
INSERT INTO `course` VALUES ('4', '物理', '1', '0', '');
INSERT INTO `course` VALUES ('5', '化学', '2', '2', null);
INSERT INTO `course` VALUES ('6', '美术', '2', '2', null);
INSERT INTO `course` VALUES ('7', '体育', '2', '2', '');

-- ----------------------------
-- Table structure for cplan
-- ----------------------------
DROP TABLE IF EXISTS `cplan`;
CREATE TABLE `cplan` (
  `cplan_id` int(11) NOT NULL AUTO_INCREMENT,
  `cplan_year` int(11) DEFAULT NULL,
  `cplan_year_half` int(11) DEFAULT '1',
  `clazz_id` int(11) DEFAULT NULL,
  `cplan_week` int(11) DEFAULT '1' COMMENT '1-5 星期一到星期五',
  `cplan_lesson` int(11) DEFAULT '1' COMMENT '1-8 第1节课到第8节课',
  `course_id` int(11) DEFAULT NULL,
  `note` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`cplan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cplan
-- ----------------------------
INSERT INTO `cplan` VALUES ('37', '2023', '1', '1', '1', '1', '1', '');
INSERT INTO `cplan` VALUES ('38', '2023', '1', '1', '1', '3', '2', '');
INSERT INTO `cplan` VALUES ('39', '2023', '1', '1', '3', '2', '3', '');
INSERT INTO `cplan` VALUES ('40', '2023', '1', '1', '5', '1', '1', '');
INSERT INTO `cplan` VALUES ('41', '2023', '1', '1', '5', '5', '4', '');
INSERT INTO `cplan` VALUES ('42', '2023', '1', '2', '1', '2', '1', null);
INSERT INTO `cplan` VALUES ('43', '2023', '1', '2', '1', '1', '2', null);
INSERT INTO `cplan` VALUES ('44', '2023', '1', '2', '2', '2', '3', null);
INSERT INTO `cplan` VALUES ('45', '2023', '1', '2', '4', '1', '1', null);
INSERT INTO `cplan` VALUES ('46', '2023', '1', '2', '4', '5', '4', null);
INSERT INTO `cplan` VALUES ('49', '2023', '1', '3', '2', '1', '1', null);
INSERT INTO `cplan` VALUES ('50', '2023', '1', '3', '3', '3', '2', null);
INSERT INTO `cplan` VALUES ('51', '2023', '1', '3', '1', '2', '3', null);
INSERT INTO `cplan` VALUES ('52', '2023', '1', '3', '4', '5', '1', null);
INSERT INTO `cplan` VALUES ('53', '2023', '1', '3', '5', '6', '4', null);
INSERT INTO `cplan` VALUES ('64', '2023', '1', '4', '1', '1', '1', null);
INSERT INTO `cplan` VALUES ('65', '2023', '1', '4', '2', '3', '2', null);
INSERT INTO `cplan` VALUES ('66', '2023', '1', '4', '3', '2', '3', null);
INSERT INTO `cplan` VALUES ('67', '2023', '1', '4', '4', '5', '1', null);
INSERT INTO `cplan` VALUES ('68', '2023', '1', '4', '5', '6', '4', null);
INSERT INTO `cplan` VALUES ('71', '2023', '1', '5', '1', '1', '1', null);
INSERT INTO `cplan` VALUES ('72', '2023', '1', '5', '2', '3', '2', null);
INSERT INTO `cplan` VALUES ('73', '2023', '1', '5', '3', '3', '3', null);
INSERT INTO `cplan` VALUES ('74', '2023', '1', '5', '4', '5', '1', null);
INSERT INTO `cplan` VALUES ('75', '2023', '1', '5', '5', '5', '4', null);

-- ----------------------------
-- Table structure for evaluate
-- ----------------------------
DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate` (
  `evaluate_id` int(11) NOT NULL AUTO_INCREMENT,
  `evaluate_year` int(11) DEFAULT NULL,
  `evaluate_year_half` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `quality_score` int(11) DEFAULT NULL,
  `post_score` int(11) DEFAULT NULL,
  `skill_score` int(11) DEFAULT NULL,
  `coach_score` int(11) DEFAULT NULL,
  PRIMARY KEY (`evaluate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of evaluate
-- ----------------------------
INSERT INTO `evaluate` VALUES ('1', '2023', '1', '2', '3', '5', '4', '5', '4');
INSERT INTO `evaluate` VALUES ('2', '2023', '1', '2', '4', '5', '5', '5', '4');

-- ----------------------------
-- Table structure for lessons
-- ----------------------------
DROP TABLE IF EXISTS `lessons`;
CREATE TABLE `lessons` (
  `lesson` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lessons
-- ----------------------------
INSERT INTO `lessons` VALUES ('1');
INSERT INTO `lessons` VALUES ('2');
INSERT INTO `lessons` VALUES ('3');
INSERT INTO `lessons` VALUES ('4');
INSERT INTO `lessons` VALUES ('5');
INSERT INTO `lessons` VALUES ('6');
INSERT INTO `lessons` VALUES ('7');
INSERT INTO `lessons` VALUES ('8');

-- ----------------------------
-- Table structure for plan
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan` (
  `plan_id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_year` int(11) DEFAULT NULL,
  `plan_year_half` int(11) DEFAULT '1',
  `clazz_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `note` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plan
-- ----------------------------
INSERT INTO `plan` VALUES ('17', '2023', '1', '1', '1', '2', null);
INSERT INTO `plan` VALUES ('18', '2023', '1', '1', '2', '2', null);
INSERT INTO `plan` VALUES ('19', '2023', '1', '1', '3', '2', null);
INSERT INTO `plan` VALUES ('20', '2023', '1', '1', '4', '2', null);
INSERT INTO `plan` VALUES ('21', '2023', '1', '2', '1', '2', null);
INSERT INTO `plan` VALUES ('22', '2023', '1', '2', '2', '2', null);
INSERT INTO `plan` VALUES ('23', '2023', '1', '2', '3', '2', null);
INSERT INTO `plan` VALUES ('24', '2023', '1', '2', '4', '2', null);
INSERT INTO `plan` VALUES ('25', '2023', '1', '3', '1', '2', null);
INSERT INTO `plan` VALUES ('26', '2023', '1', '3', '2', '2', null);
INSERT INTO `plan` VALUES ('27', '2023', '1', '3', '3', '2', null);
INSERT INTO `plan` VALUES ('28', '2023', '1', '3', '4', '2', null);
INSERT INTO `plan` VALUES ('29', '2023', '1', '4', '1', '2', null);
INSERT INTO `plan` VALUES ('30', '2023', '1', '4', '2', '2', null);
INSERT INTO `plan` VALUES ('31', '2023', '1', '4', '3', '2', null);
INSERT INTO `plan` VALUES ('32', '2023', '1', '4', '4', '2', null);
INSERT INTO `plan` VALUES ('33', '2023', '1', '5', '1', '2', null);
INSERT INTO `plan` VALUES ('34', '2023', '1', '5', '2', '2', null);
INSERT INTO `plan` VALUES ('35', '2023', '1', '5', '3', '2', null);
INSERT INTO `plan` VALUES ('36', '2023', '1', '5', '4', '2', null);

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `score_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `score_value` double DEFAULT NULL,
  `score_year` int(11) DEFAULT NULL,
  `score_year_half` int(11) DEFAULT '1' COMMENT '1-上半年 2-下半年',
  `note` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`score_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('1', '3', '1', '98', '2023', '1', null);
INSERT INTO `score` VALUES ('2', '4', '1', '89', '2023', '1', null);
INSERT INTO `score` VALUES ('3', '6', '1', '88', '2023', '1', null);
INSERT INTO `score` VALUES ('4', '7', '1', '100', '2023', '1', null);
INSERT INTO `score` VALUES ('5', '8', '1', '99', '2023', '1', null);
INSERT INTO `score` VALUES ('6', '9', '1', '66', '2023', '1', null);
INSERT INTO `score` VALUES ('7', '3', '2', '96', '2023', '1', null);
INSERT INTO `score` VALUES ('8', '4', '2', '88', '2023', '1', null);
INSERT INTO `score` VALUES ('9', '6', '2', '99', '2023', '1', null);
INSERT INTO `score` VALUES ('10', '7', '2', '78', '2023', '1', null);
INSERT INTO `score` VALUES ('11', '8', '2', '80', '2023', '1', null);
INSERT INTO `score` VALUES ('12', '9', '2', '96', '2023', '1', null);
INSERT INTO `score` VALUES ('14', '3', '5', '95', '2023', '1', null);

-- ----------------------------
-- Table structure for scource
-- ----------------------------
DROP TABLE IF EXISTS `scource`;
CREATE TABLE `scource` (
  `scource_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `scource_year` int(11) DEFAULT NULL,
  `scource_year_half` int(11) DEFAULT NULL,
  PRIMARY KEY (`scource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scource
-- ----------------------------
INSERT INTO `scource` VALUES ('1', '3', '5', '2023', '1');
INSERT INTO `scource` VALUES ('3', '3', '6', '2023', '1');
INSERT INTO `scource` VALUES ('4', '4', '5', '2023', '1');
INSERT INTO `scource` VALUES ('5', '4', '6', '2023', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `user_pass` varchar(200) NOT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  `user_sex` int(11) DEFAULT '1' COMMENT '1：男  2：女',
  `user_age` int(11) DEFAULT '22',
  `clazz_id` int(11) DEFAULT '0',
  `reg_date` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '2：管理员 1：注册用户',
  `user_type` int(11) DEFAULT '1' COMMENT '1：学生 2：教师 3：系统管理员 ',
  `user_question` varchar(300) DEFAULT NULL,
  `user_answer` varchar(300) DEFAULT NULL,
  `note` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '4cb0a5502e9aa44a0ca99e96742f2e788aad875a', '管理员', '1', '22', '0', '2023-04-15 20:17:54', '3', '我最喜欢的人', '毛驴大圣', null);
INSERT INTO `user` VALUES ('2', 'tea1', '4cb0a5502e9aa44a0ca99e96742f2e788aad875a', '吕玉华', '1', '24', '0', '2023-04-15 20:46:51', '2', '我最喜欢的人', '毛驴大圣', null);
INSERT INTO `user` VALUES ('3', 'p2023001', '4cb0a5502e9aa44a0ca99e96742f2e788aad875a', '李可馨', '1', '12', '1', '2023-04-16 20:49:31', '1', '我最喜欢的人', '毛驴大圣', null);
INSERT INTO `user` VALUES ('4', 'p2023002', '4cb0a5502e9aa44a0ca99e96742f2e788aad875a', '田诗雨', '2', '15', '1', '2023-04-16 16:54:35', '1', '我最喜欢的人', '毛驴大圣', null);
INSERT INTO `user` VALUES ('6', 'p2023003', '4cb0a5502e9aa44a0ca99e96742f2e788aad875a', '李吉阳', '1', '15', '3', '2023-04-17 12:19:25', '1', '我最喜欢的人', '毛驴大圣', null);
INSERT INTO `user` VALUES ('7', 'p2023004', '4cb0a5502e9aa44a0ca99e96742f2e788aad875a', '姚策', '2', '18', '4', '2023-04-17 13:55:10', '1', '我最喜欢的人', '毛驴大圣', null);
INSERT INTO `user` VALUES ('8', 'p2023005', '4cb0a5502e9aa44a0ca99e96742f2e788aad875a', '王楠', '1', '15', '5', '2023-04-14 13:55:52', '1', '我最喜欢的人', '毛驴大圣', null);
INSERT INTO `user` VALUES ('9', 'p2023006', '4cb0a5502e9aa44a0ca99e96742f2e788aad875a', '卢建如', '1', '19', '5', '2023-04-15 13:56:23', '1', '我最喜欢的人', '毛驴大圣', null);
INSERT INTO `user` VALUES ('10', 'p2023007', '4cb0a5502e9aa44a0ca99e96742f2e788aad875a', '关佳润', '2', '8', '1', '2023-04-14 13:57:06', '1', '我最喜欢的人', '毛驴大圣', null);
INSERT INTO `user` VALUES ('11', 'p2023008', '4cb0a5502e9aa44a0ca99e96742f2e788aad875a', '殷小丽', '2', '21', '3', '2023-04-15 13:57:38', '1', '我最喜欢的人', '毛驴大圣', null);
