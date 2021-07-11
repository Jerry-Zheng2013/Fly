/*
 Navicat MySQL Data Transfer

 Source Server         : ticket1
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : ticket

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 12/07/2021 03:24:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `oi_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '流水号',
  `account_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '官网账号',
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `trip_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行程代码',
  `flight_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '航班号',
  `cabin_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓位',
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价格',
  `standby_count` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '余票',
  `order_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态',
  `count_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '倒计时',
  `round` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '轮次',
  `input_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入时间',
  `update_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `input_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入人',
  `attribute0` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用字段',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`oi_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('1624914497650X46980', '15136795044', 'FO2106290510525', 'AVH/CANPKX/20210629/D/KN', 'KN5900', 'H', '1372.8', '1', '2', NULL, '2', '2021/06/29 05:08:17', '2021/06/29 05:11:01', 'user', NULL, NULL);
INSERT INTO `order_info` VALUES ('1624914497996X87155', '15083142384', 'FO2106290508189', 'AVH/CANPKX/20210629/D/KN', 'KN5900', 'H', '343.2', '5', '2', NULL, '1', '2021/06/29 05:08:17', '2021/06/29 05:08:17', 'user', NULL, NULL);
INSERT INTO `order_info` VALUES ('1625156614862X55527', '13409246697', 'FO2107020023345', 'AVH/CANPKX/20210723/D/KN', 'KN5900', 'V', '686.4', '10', '1', NULL, '1', '2021/07/02 12:23:34', '2021/07/02 12:23:34', 'user', NULL, NULL);
INSERT INTO `order_info` VALUES ('1625156615761X61890', '15083142384', 'FO2107020023358', 'AVH/CANPKX/20210723/D/KN', 'KN5900', 'V', '686.4', '10', '1', NULL, '1', '2021/07/02 12:23:35', '2021/07/02 12:23:35', 'user', NULL, NULL);
INSERT INTO `order_info` VALUES ('1625156616275X56117', '15136795044', 'FO2107020023363', 'AVH/CANPKX/20210723/D/KN', 'KN5900', 'V', '343.2', '10', '1', NULL, '1', '2021/07/02 12:23:36', '2021/07/02 12:23:36', 'user', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
