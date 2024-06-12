/*
 Navicat Premium Data Transfer

 Source Server         : localhost3306
 Source Server Type    : MySQL
 Source Server Version : 80037
 Source Host           : localhost:3306
 Source Schema         : big_event

 Target Server Type    : MySQL
 Target Server Version : 80037
 File Encoding         : 65001

 Date: 05/06/2024 14:24:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('fb26433f-15ac-4cb7-887e-a89a0765c8ca', '樊福蕰', '15035456641', '山西省运城市临猗县');
INSERT INTO `address` VALUES ('8259adcb-e648-4935-afab-21eb3dda45fc', '1234567890', '12345678901', '12345678901');
INSERT INTO `address` VALUES ('ecf403d2-221c-4603-96e9-c291b9445373', '123', '12345678901', '123');

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章标题',
  `content` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章内容',
  `cover_img` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章封面',
  `state` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '草稿' COMMENT '文章状态: 只能是[已发布] 或者 [草稿]',
  `category_id` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '文章分类ID',
  `create_user` int(0) UNSIGNED NOT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_article_category`(`category_id`) USING BTREE,
  INDEX `fk_article_user`(`create_user`) USING BTREE,
  CONSTRAINT `fk_article_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_article_user` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (7, 'qwerwqer', '<p>qwrewqer</p>', 'url访问地址', '已发布', 2, 1, '2024-05-24 13:24:25', '2024-05-24 13:24:25');
INSERT INTO `article` VALUES (8, 'qwerwqer', '<p>qwrewqer</p>', 'url访问地址', '已发布', 2, 1, '2024-05-24 13:24:27', '2024-05-24 13:24:27');
INSERT INTO `article` VALUES (10, 'qwerwqer', '<p>qwrewqer</p>', 'url访问地址', '已发布', 2, 1, '2024-05-24 13:24:31', '2024-05-24 13:24:31');
INSERT INTO `article` VALUES (11, 'qwer', '<p>qwrewqe</p>', 'url访问地址', '已发布', 1, 1, '2024-05-24 13:26:03', '2024-05-24 13:26:03');
INSERT INTO `article` VALUES (12, 'wqreet', '<p>asdfsd</p>', 'url访问地址', '已发布', 1, 1, '2024-05-24 13:26:40', '2024-05-24 13:26:40');
INSERT INTO `article` VALUES (13, 'qrweqwre', '<p>qwrwqerwqe</p>', 'url访问地址', '已发布', 1, 1, '2024-05-24 13:28:47', '2024-05-24 13:28:47');
INSERT INTO `article` VALUES (14, 'qwerqwer', '<p>qwrweqr</p>', 'url访问地址', '已发布', 1, 1, '2024-05-24 13:30:34', '2024-05-24 13:30:34');
INSERT INTO `article` VALUES (15, 'qwrqwer', '<p>qwrweqrqwrqrw</p>', 'url访问地址', '已发布', 1, 1, '2024-05-24 13:30:56', '2024-05-24 13:30:56');
INSERT INTO `article` VALUES (17, 'qwerwqer', '<p>qwrewqerasdfsdaf</p>', 'url访问地址', '已发布', 2, 1, '2024-05-24 14:09:00', '2024-05-24 14:09:00');
INSERT INTO `article` VALUES (18, 'qwerwqer', '<p>qwrewqer</p>', 'url访问地址', '已发布', 2, 1, '2024-05-24 14:09:46', '2024-05-24 14:09:46');
INSERT INTO `article` VALUES (19, 'asfsafd', '<p>qwrewqerasdf</p>', 'url访问地址', '已发布', 1, 1, '2024-05-24 14:10:00', '2024-05-24 14:10:00');
INSERT INTO `article` VALUES (20, '111', '<p>123</p>', 'url访问地址', '已发布', 1, 1, '2024-06-03 16:23:53', '2024-06-03 16:23:53');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `category_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称',
  `category_alias` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类别名',
  `create_user` int(0) UNSIGNED NOT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_category_user`(`create_user`) USING BTREE,
  CONSTRAINT `fk_category_user` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '123', '456asdf', 1, '2024-05-24 09:50:50', '2024-05-24 14:10:35');
INSERT INTO `category` VALUES (2, '678', '890', 1, '2024-05-24 09:50:56', '2024-05-24 09:50:56');
INSERT INTO `category` VALUES (3, '142', '1567', 1, '2024-05-28 14:31:56', '2024-05-28 14:31:56');
INSERT INTO `category` VALUES (4, '123', '123', 1, '2024-05-28 15:35:20', '2024-05-28 15:35:20');
INSERT INTO `category` VALUES (5, '111', '111', 1, '2024-05-28 16:48:25', '2024-05-28 16:48:25');
INSERT INTO `category` VALUES (6, '222', '222', 1, '2024-05-28 16:48:29', '2024-05-28 16:48:29');
INSERT INTO `category` VALUES (7, '333', '333', 1, '2024-05-28 16:48:32', '2024-05-28 16:48:32');
INSERT INTO `category` VALUES (8, '444', '444', 1, '2024-05-28 16:48:35', '2024-05-28 16:48:35');

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` float(100, 2) NULL DEFAULT NULL,
  `urlimage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `updatebyId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `commoditynums` int(0) NULL DEFAULT NULL,
  `bookdetails` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createtime` datetime(0) NULL DEFAULT NULL,
  `updatetime` datetime(0) NULL DEFAULT NULL,
  `state` int(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('88a65552-fa0c-47fe-bced-5eb44ddf0e34', 'java开发', '樊富云', 123.00, 'url', '1', 12345, NULL, '2024-05-28 14:35:17', '2024-05-28 14:35:17', 1);
INSERT INTO `commodity` VALUES ('a1dbe7b4-91ca-4336-8884-dd30c1d79eb9', '1', '1', 1.00, '1', '1', 1, NULL, '2024-05-28 16:09:55', '2024-05-28 16:09:55', 1);
INSERT INTO `commodity` VALUES ('5f1a8a42-b507-4de9-ac68-f060a15afce1', '2', '2', 2.00, '2', '1', 2, NULL, '2024-05-28 16:11:20', '2024-05-28 16:11:20', 1);
INSERT INTO `commodity` VALUES ('f03dfa93-42ad-436f-a447-f3de3047b664', '简爱', '反无用', 1234.00, '1234', '1', 1234, NULL, '2024-05-31 09:31:30', '2024-05-31 09:31:30', 1);
INSERT INTO `commodity` VALUES ('689694d9-bdc5-417a-908c-62aaf247f1b3', '简爱', '123', 123.00, '123', '1', 123, '《简爱》是一部经典的19世纪英国小说，作者是夏洛蒂·勃朗特。主人公简·爱，一个孤儿，出身低微却坚韧不屈。她在充满苦难的童年中展现出非凡的勇气，后成为家庭教师，与雇主罗切斯特先生展开了一段充满激情与挑战的爱情故事。小说探讨了女性的自我独立、尊严及对平等爱情的追求，同时揭示了社会阶级与个人命运的深刻冲突。这部作品以其独特的女性视角和强烈的情感共鸣，至今仍广受读者喜爱。', '2024-05-31 09:35:51', '2024-05-31 09:35:51', 1);
INSERT INTO `commodity` VALUES ('c862dba4-439c-4a61-b10d-bd1faefec7dc', '老人与海', '123', 123.00, '132', '1', 123, '《老人与海》是海明威的力作，讲述了一位古巴老渔夫桑提亚哥，在经历了长时间的孤独和失败后，独自出海挑战深不可测的大海。他执着地追求一条硕大马林鱼，尽管鱼儿带走了他的体力和耐心，甚至引来鲨鱼的围攻。象征着坚毅不屈的桑提亚哥，虽败犹荣，展现出人类对自然、生命极限的无畏对抗和对生存尊严的坚守。这部作品弘扬了硬汉精神，成为世界文学经典。', '2024-05-31 09:48:19', '2024-05-31 09:48:19', 0);
INSERT INTO `commodity` VALUES ('bc0dbafb-17cd-4c4a-af20-44ab48c7079d', '猫和老鼠', '111', 111.00, '111', '1', 111, '《猫和老鼠》是一套深受全球儿童喜爱的经典漫画合集，改编自美国华纳兄弟出品的同名动画。作者帕特森以简洁幽默的手法描绘了一只永不放弃追捕的猫汤姆与一只机智灵活的老鼠杰瑞之间的永恒追逐游戏。这套图书以色彩明亮的插图和轻松的故事展现了智慧与策略的碰撞，陪伴了几代读者度过无数欢乐时光。无论是重温童年记忆，还是初次接触，都能在每个篇章中感受到这对欢喜冤家的独特魅力。', '2024-05-31 09:49:19', '2024-05-31 09:49:19', 1);
INSERT INTO `commodity` VALUES ('2ab0065c-13c3-4c52-9c22-af4815d2e81a', '程序设计', '123', 123.00, '123', '1', 123, '\"《程序设计》是一本全面而深入的指南，涵盖了从基础知识到高级技术的全方位内容。无论是初学者还是经验丰富的开发者，都能在此找到关键概念的阐述，如C、Java等语言的精讲，以及Windows编程实践。它不仅教授编程原理，还强调代码质量与算法优化，适合不同层次的学习者提升编程技能和解决问题的能力。无论你是为了职业发展还是个人兴趣，这本书都是你探索软件世界的实用宝典。\"', '2024-05-31 10:26:07', '2024-05-31 10:26:07', 1);
INSERT INTO `commodity` VALUES ('bdb1a036-3fde-4bad-b06c-1d5d42b75ed1', '111', '111', 222.00, '222', '1', 222, '\"《111》是一部多维度的作品。可能是杨佳富编著的中国反间谍题材小说，揭示了当代社会的复杂安全问题；另一方面，它也可能是一部网络热门连载，如爱看阁上的大神作品，以悬疑剧情吸引读者。对于少儿读者，可能是剑桥少儿英语科普系列的一部分，通过生动的双语内容培养孩子们的兴趣和科学素养。而提到的其他版本则暗示这可能是一部小说集或者音乐相关的回忆录，记录着生活中的点滴和艺术历程。无论哪种解读，都展现出书籍丰富的层次和广泛的受众吸引力。\"', '2024-06-03 16:24:56', '2024-06-03 16:24:56', 1);

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `commodityid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `addressid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `commoditynum` int(0) NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------
INSERT INTO `shoppingcart` VALUES ('24974363-2c57-4d87-b2a1-7dff0bf7ce85', '1', '88a65552-fa0c-47fe-bced-5eb44ddf0e34', 'fb26433f-15ac-4cb7-887e-a89a0765c8ca', 1, '0');
INSERT INTO `shoppingcart` VALUES ('6a2571e0-14fb-466b-be0b-a7fb16b67829', '1', '88a65552-fa0c-47fe-bced-5eb44ddf0e34', 'fb26433f-15ac-4cb7-887e-a89a0765c8ca', 4, '0');
INSERT INTO `shoppingcart` VALUES ('d6fddc00-728a-40e4-8b82-0968330ab413', '1', '88a65552-fa0c-47fe-bced-5eb44ddf0e34', '', 1, '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '昵称',
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '邮箱',
  `user_pic` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '头像',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'fanfuyun', 'e3ceb5881a0a1fdaad01296d7554868d', '', '824821545@qq.com', '', '2024-05-22 16:20:23', '2024-05-24 11:21:56');

-- ----------------------------
-- Table structure for userandhistory
-- ----------------------------
DROP TABLE IF EXISTS `userandhistory`;
CREATE TABLE `userandhistory`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userId` int(0) NULL DEFAULT NULL,
  `userchardata` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userandhistory
-- ----------------------------
INSERT INTO `userandhistory` VALUES ('ef1fba8a-31d0-4d23-b9d1-212d52f0fc96', 1, '123 is a numeric value. It does not have any specific meaning unless it is part of a mathematical equation or sequence.');

-- ----------------------------
-- Table structure for usercommoditys
-- ----------------------------
DROP TABLE IF EXISTS `usercommoditys`;
CREATE TABLE `usercommoditys`  (
  `id` varchar(0) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userId` int(0) NULL DEFAULT NULL,
  `usercommodityId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `commoditynums` int(0) NULL DEFAULT NULL,
  `createtime` datetime(0) NULL DEFAULT NULL,
  `updatetime` datetime(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'stata 	0--加入购物车且未支付\r\n	1--已支付' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
