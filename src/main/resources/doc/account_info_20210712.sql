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

 Date: 12/07/2021 03:23:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_info
-- ----------------------------
DROP TABLE IF EXISTS `account_info`;
CREATE TABLE `account_info`  (
  `account_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号密码',
  `contact_mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `use_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用时间',
  `encryptStr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录信息',
  `session` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'session',
  PRIMARY KEY (`account_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of account_info
-- ----------------------------
INSERT INTO `account_info` VALUES ('13409246697', '石竟二', 'ASDFGH123', '13409246697', '2021-07-10', 'NrcZ9YVVM/N5PuZaHJfqltN6wPGOIfrHwwFNJ4DTKCFHbCJH+gQAIg0DcFee9/+sdF9Wp6yYfyth9eqBA1jHECZxFcebg/Xx2cJyLaqj9Y30aCrOS9m2n/hdiBsuhyBx', 's~346f28b8-48f9-44b8-bc54-e1d4f3bcb9a9.f72dd11d1cecf2e780f6874c23b8da21');
INSERT INTO `account_info` VALUES ('15083142384', '石竟革', 'ASDFGH123', '15083142384', '2021-07-11', 'NrcZ9YVVM/N5PuZaHJfqltN6wPGOIfrHwwFNJ4DTKCEm3SxfgMZ8I/hpPPwEJ24WdF9Wp6yYfyth9eqBA1jHECZxFcebg/Xx2cJyLaqj9Y30aCrOS9m2n/hdiBsuhyBx', '');
INSERT INTO `account_info` VALUES ('15136795044', '石竟一', 'ASDFGH123', '15136795044', '2021-07-11', 'NrcZ9YVVM/N5PuZaHJfqltN6wPGOIfrHwwFNJ4DTKCFLMNVYQQRSRd7UHGf5I1rGdF9Wp6yYfyth9eqBA1jHECZxFcebg/Xx2cJyLaqj9Y30aCrOS9m2n/hdiBsuhyBx', '');
INSERT INTO `account_info` VALUES ('15236469495', '石竟三', 'ASDFGH123', '15236469495', '2021-07-11', 'NrcZ9YVVM/N5PuZaHJfqltN6wPGOIfrHwwFNJ4DTKCG+rNXIDFj/CquYsimyqEmBdF9Wp6yYfyth9eqBA1jHECZxFcebg/Xx2cJyLaqj9Y30aCrOS9m2n/hdiBsuhyBx', '');
INSERT INTO `account_info` VALUES ('18240695516', '石竟四', 'ASDFGH123', '18240695516', '2021-07-11', 'NrcZ9YVVM/N5PuZaHJfqltN6wPGOIfrHwwFNJ4DTKCEv2tfVvtLGh5wowyMWksgGdF9Wp6yYfyth9eqBA1jHECZxFcebg/Xx2cJyLaqj9Y30aCrOS9m2n/hdiBsuhyBx', '');

SET FOREIGN_KEY_CHECKS = 1;
