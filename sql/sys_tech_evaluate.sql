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

 Date: 07/04/2024 01:04:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_tech_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `sys_tech_evaluate`;
CREATE TABLE `sys_tech_evaluate`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL COMMENT '教员ID',
  `class_id` bigint(20) NOT NULL COMMENT '期数ID',
  `result` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '结果',
  `total` int(4) NOT NULL COMMENT '总分',
  `status` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态',
  `del_flag` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评学信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_tech_evaluate
-- ----------------------------
INSERT INTO `sys_tech_evaluate` VALUES (2, 1, 1, '{\"1\":2,\"2\":2,\"3\":2,\"4\":3,\"5\":3,\"6\":3}', 72, '1', '0', NULL, '2024-04-06 01:12:25', NULL, NULL);
INSERT INTO `sys_tech_evaluate` VALUES (3, 1, 17, '{\"7\":2}', 11, '1', '0', NULL, '2024-04-06 21:56:38', NULL, NULL);

-- ----------------------------
-- Table structure for sys_tech_question
-- ----------------------------
DROP TABLE IF EXISTS `sys_tech_question`;
CREATE TABLE `sys_tech_question`  (
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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评学题库信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_tech_question
-- ----------------------------
INSERT INTO `sys_tech_question` VALUES (1, 1, '2024年第一期', 'cs', 'cs', 11, 'cs', 12, 'cs', 12, 'cs', 11, '0', '1', NULL, '2024-04-05 01:39:05', NULL, NULL);
INSERT INTO `sys_tech_question` VALUES (2, 1, '2024年第一期', 'cs', 'cs', 11, 'cs', 12, 'cs', 12, 'cs', 11, '0', '1', NULL, '2024-04-05 01:39:05', NULL, NULL);
INSERT INTO `sys_tech_question` VALUES (3, 1, '2024年第一期', 'cs', 'cs', 11, 'cs', 12, 'cs', 12, 'cs', 11, '0', '1', NULL, '2024-04-05 01:39:05', NULL, NULL);
INSERT INTO `sys_tech_question` VALUES (4, 1, '2024年第一期', 'cs', 'cs', 11, 'cs', 12, 'cs', 12, 'cs', 11, '0', '2', NULL, '2024-04-05 01:39:05', NULL, NULL);
INSERT INTO `sys_tech_question` VALUES (5, 1, '2024年第一期', 'cs', 'cs', 11, 'cs', 12, 'cs', 12, 'cs', 11, '0', '2', NULL, '2024-04-05 01:39:05', NULL, NULL);
INSERT INTO `sys_tech_question` VALUES (6, 1, '2024年第一期', 'cs', 'cs', 11, 'cs', 12, 'cs', 12, 'cs', 11, '0', '2', NULL, '2024-04-05 01:39:05', NULL, NULL);
INSERT INTO `sys_tech_question` VALUES (7, 17, '顶顶顶顶顶', 'cs', 'cs', 10, 'cs', 11, 'cs', 11, 'cs', 11, '0', '2', NULL, '2024-04-05 22:57:29', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
