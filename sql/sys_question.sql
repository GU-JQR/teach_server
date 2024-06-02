/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : teach

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 10/03/2024 16:49:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_question
-- ----------------------------
DROP TABLE IF EXISTS `sys_question`;
CREATE TABLE `sys_question`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `class_id` bigint(20) NOT NULL COMMENT '期数ID',
  `class_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '期数名称',
  `question` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目',
  `option_one` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选项一',
  `score_one` int(4) NOT NULL COMMENT '选项一分数',
  `option_two` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选项二',
  `score_two` int(4) NOT NULL COMMENT '选项二分数',
  `option_three` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选项三',
  `score_three` int(4) NOT NULL COMMENT '选项三分数',
  `option_four` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选项四',
  `score_four` int(4) NOT NULL COMMENT '选项四分数',
  `del_flag` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志',
  `question_type` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目类型',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评教题库信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_question
-- ----------------------------
INSERT INTO `sys_question` VALUES (14, 3, '2023年第2期', 'cs', 'cs', 2, 'cs', 5, 'cs', 8, 'cs', 10, '0', '1', NULL, '2024-03-09 18:52:52', NULL, '2024-03-09 19:10:09');

SET FOREIGN_KEY_CHECKS = 1;
