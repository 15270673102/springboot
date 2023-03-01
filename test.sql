/*
 Navicat Premium Data Transfer

 Source Server         : localhost 3307
 Source Server Type    : MySQL
 Source Server Version : 50741
 Source Host           : localhost:3307
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50741
 File Encoding         : 65001

 Date: 01/03/2023 09:45:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '王家羽1', 16, '1421');
INSERT INTO `user` VALUES (2, '王家羽2', 18, '432');

SET FOREIGN_KEY_CHECKS = 1;
