/*
Navicat MySQL Data Transfer

Source Server         : db
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : db_shop_wgsc

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-11-13 22:35:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `AdminType` int(4) DEFAULT NULL,
  `AdminName` char(12) DEFAULT NULL,
  `LoginName` char(12) DEFAULT NULL,
  `LoginPwd` char(12) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '1', '商品管理员', 'Admin1', 'Admin1');
INSERT INTO `admin` VALUES ('2', '2', '订单管理员', 'Admin2', 'Admin2');
INSERT INTO `admin` VALUES ('3', '3', '会员管理员', 'Admin3', 'Admin3');
INSERT INTO `admin` VALUES ('4', '4', '系统管理员', 'Admin', 'Admin');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `Member` int(4) NOT NULL,
  `Money` decimal(9,2) DEFAULT NULL,
  `CartStatus` int(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES ('2', '8', '318.50', '1');
INSERT INTO `cart` VALUES ('3', '3', '353.35', '1');
INSERT INTO `cart` VALUES ('4', '3', '120.00', '0');
INSERT INTO `cart` VALUES ('5', '1', '160.00', '1');
INSERT INTO `cart` VALUES ('6', '2', '45.00', '1');
INSERT INTO `cart` VALUES ('7', '1', '56.00', '0');
INSERT INTO `cart` VALUES ('8', '11', '141.20', '1');
INSERT INTO `cart` VALUES ('9', '11', '75.00', '0');

-- ----------------------------
-- Table structure for cartselectedmer
-- ----------------------------
DROP TABLE IF EXISTS `cartselectedmer`;
CREATE TABLE `cartselectedmer` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `Cart` int(4) NOT NULL,
  `Merchandise` int(4) NOT NULL,
  `Number` int(4) NOT NULL DEFAULT '1',
  `Price` decimal(8,2) NOT NULL DEFAULT '0.00',
  `Money` decimal(9,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cartselectedmer
-- ----------------------------
INSERT INTO `cartselectedmer` VALUES ('3', '2', '12', '3', '45.00', '135.00');
INSERT INTO `cartselectedmer` VALUES ('5', '2', '8', '2', '33.00', '66.00');
INSERT INTO `cartselectedmer` VALUES ('6', '2', '5', '1', '42.50', '42.50');
INSERT INTO `cartselectedmer` VALUES ('7', '2', '7', '1', '54.00', '54.00');
INSERT INTO `cartselectedmer` VALUES ('8', '3', '11', '1', '45.00', '45.00');
INSERT INTO `cartselectedmer` VALUES ('9', '3', '4', '2', '41.80', '83.60');
INSERT INTO `cartselectedmer` VALUES ('10', '3', '10', '3', '71.25', '213.75');
INSERT INTO `cartselectedmer` VALUES ('14', '4', '1', '1', '34.00', '34.00');
INSERT INTO `cartselectedmer` VALUES ('16', '4', '3', '2', '43.00', '86.00');
INSERT INTO `cartselectedmer` VALUES ('17', '5', '15', '1', '50.00', '50.00');
INSERT INTO `cartselectedmer` VALUES ('18', '5', '2', '2', '52.00', '104.00');
INSERT INTO `cartselectedmer` VALUES ('19', '6', '1', '1', '34.00', '34.00');
INSERT INTO `cartselectedmer` VALUES ('20', '7', '15', '1', '50.00', '50.00');
INSERT INTO `cartselectedmer` VALUES ('21', '8', '15', '2', '50.00', '100.00');
INSERT INTO `cartselectedmer` VALUES ('22', '8', '4', '1', '35.20', '35.20');
INSERT INTO `cartselectedmer` VALUES ('23', '9', '10', '1', '60.00', '60.00');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `CateName` char(40) DEFAULT NULL,
  `CateDesc` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '叶菜类', '分类描述');
INSERT INTO `category` VALUES ('2', '果菜类', '分类描述');
INSERT INTO `category` VALUES ('3', '特色蔬菜', '分类描述');
INSERT INTO `category` VALUES ('4', '鲜食用菌', '分类描述');
INSERT INTO `category` VALUES ('5', '有机蔬菜', '分类描述');
INSERT INTO `category` VALUES ('6', '蔬果礼盒类', '分类描述');
INSERT INTO `category` VALUES ('7', '家具类', '分类描述');

-- ----------------------------
-- Table structure for leaveword
-- ----------------------------
DROP TABLE IF EXISTS `leaveword`;
CREATE TABLE `leaveword` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `Member` int(4) NOT NULL,
  `Admin` int(4) DEFAULT NULL,
  `Title` char(60) DEFAULT NULL,
  `Content` text,
  `LeaveDate` datetime DEFAULT NULL,
  `AnswerContent` text,
  `AnswerDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leaveword
-- ----------------------------
INSERT INTO `leaveword` VALUES ('1', '2', null, '非常感谢', '非常感谢，很方便！', '2017-04-20 21:14:44', null, null);
INSERT INTO `leaveword` VALUES ('2', '2', null, '不错', '不错的电子商务网站！', '2017-04-20 21:15:40', null, null);
INSERT INTO `leaveword` VALUES ('3', '3', null, '支持', '支持电子商务！', '2017-04-20 22:38:58', null, null);
INSERT INTO `leaveword` VALUES ('4', '11', null, '很好', '非常好', '2017-03-22 17:22:13', null, null);

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `Memberlevel` int(4) NOT NULL,
  `LoginName` char(12) DEFAULT NULL,
  `LoginPwd` char(12) DEFAULT NULL,
  `MemberName` char(20) DEFAULT NULL,
  `Phone` char(15) DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL,
  `Zip` char(10) DEFAULT NULL,
  `RegDate` datetime DEFAULT NULL,
  `LastDate` datetime DEFAULT NULL,
  `LoginTimes` int(4) DEFAULT NULL,
  `EMail` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('1', '4', 'liuqiao', 'liuqiao', '刘桥', '13971559323', '湖北省武汉市洪山区', '432200', '2017-04-20 18:40:30', '2017-04-23 17:40:08', '7', 'liuqiao1982@sina.com');
INSERT INTO `member` VALUES ('2', '1', 'jinwu', 'jinwu', '金武', '13456754323', '广东省深圳市', '435567', '2017-04-20 21:05:58', '2017-04-21 15:33:41', '2', 'jinwu@sina.com');
INSERT INTO `member` VALUES ('3', '1', 'hujie', 'hujie', '胡杰', '13456789067', '武汉市洪山区鲁磨路', '432254', '2017-04-20 21:17:08', '2017-04-21 15:32:36', '3', 'hujie@sohu.com');
INSERT INTO `member` VALUES ('8', '3', 'chenlin', 'chenlin', '陈林', '13456756789', '湖北省武汉市新洲区', '432543', '2017-04-20 22:44:30', '2017-04-21 15:33:08', '2', 'chenlin@sohu.com');
INSERT INTO `member` VALUES ('9', '1', 'ggg', 'ggg', '该干', '66771111111', 'ggg', 'ggg', '2017-04-24 15:44:50', '2017-04-24 15:44:50', '0', 'gg@ff.cn');
INSERT INTO `member` VALUES ('10', '1', 'ggg1', '111', '该干', '66771111111', 'ggg', 'ggg', '2017-04-24 16:11:16', '2017-04-24 16:11:16', '0', 'gg@ff.cn');
INSERT INTO `member` VALUES ('11', '4', 'wanghui', 'wanghui', 'wanghui', '0411-5550999', '乌干达坎帕拉', '123456', '2017-03-22 15:43:54', '2017-03-22 17:21:45', '3', '994566883@qq.com');

-- ----------------------------
-- Table structure for memberlevel
-- ----------------------------
DROP TABLE IF EXISTS `memberlevel`;
CREATE TABLE `memberlevel` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `LevelName` char(20) DEFAULT NULL,
  `Favourable` int(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of memberlevel
-- ----------------------------
INSERT INTO `memberlevel` VALUES ('1', '普通会员', '95');
INSERT INTO `memberlevel` VALUES ('2', '黄金会员', '90');
INSERT INTO `memberlevel` VALUES ('3', '白金会员', '85');
INSERT INTO `memberlevel` VALUES ('4', '钻石会员', '80');

-- ----------------------------
-- Table structure for merchandise
-- ----------------------------
DROP TABLE IF EXISTS `merchandise`;
CREATE TABLE `merchandise` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `Category` int(4) NOT NULL,
  `MerName` char(40) DEFAULT NULL,
  `Price` decimal(8,2) DEFAULT NULL,
  `SPrice` decimal(8,2) DEFAULT NULL,
  `MerModel` char(40) DEFAULT NULL,
  `Picture` varchar(100) DEFAULT NULL,
  `MerDesc` text,
  `Manufacturer` char(60) DEFAULT NULL,
  `LeaveFactoryDate` datetime DEFAULT NULL,
  `Special` int(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of merchandise
-- ----------------------------
INSERT INTO `merchandise` VALUES ('1', '4', '有机蒿子秆', '4.00', '3.00', 'c1', '/Picture/1.jpg', '有机蒿子秆描述', '友农蔬菜厂', '2017-04-21 00:00:00', '1');
INSERT INTO `merchandise` VALUES ('2', '2', '生态香蕉西葫芦', '6.00', '0.00', 'c2', '/Picture/2.jpg', '生态香蕉西葫芦', '友农蔬菜厂', '2017-04-21 00:00:00', '0');
INSERT INTO `merchandise` VALUES ('3', '4', '鲜蟹味菇 150g/份---鲜菌类保鲜期..', '5.00', '4.00', 'c3', '/Picture/3.jpg', '非常好吃的蔬菜', '友农蔬菜厂', '2012-04-09 00:00:00', '1');
INSERT INTO `merchandise` VALUES ('4', '5', '鲜蟹味菇黑白组合 300g/盒---鲜菌..', '4.00', '3.00', 'c3', '/Picture/4.jpg', '非常好吃的蔬菜', '友农蔬菜厂', '2017-04-15 00:00:00', '0');
INSERT INTO `merchandise` VALUES ('5', '1', '鲜金针菇---鲜菌类保鲜期短 建议当天食..\r\n', '3.00', '0.00', 'c5', '/Picture/5.jpg', '非常好吃的蔬菜', '友农蔬菜厂', '2017-04-15 00:00:00', '0');
INSERT INTO `merchandise` VALUES ('6', '1', '生态茼蒿', '6.00', '0.00', 'e1', '/Picture/6.jpg', '非常好吃的蔬菜', '友农蔬菜厂', '2017-05-10 00:00:00', '0');
INSERT INTO `merchandise` VALUES ('7', '2', '生态西葫芦', '6.00', '5.00', 'e2', '/Picture/7.jpg', '非常好吃的蔬菜', '友农蔬菜厂', '2012-04-12 00:00:00', '1');
INSERT INTO `merchandise` VALUES ('8', '1', '鲜海鲜菇---鲜菌类保鲜期短 建议当天食..', '5.00', '3.00', 'n1', '/Picture/8.jpg', '非常好吃的蔬菜', '友农蔬菜厂', '2012-04-12 00:00:00', '1');
INSERT INTO `merchandise` VALUES ('9', '1', '有机青椒', '5.00', '0.00', 'n2', '/Picture/9.jpg', '非常好吃的蔬菜', '友农蔬菜厂', '2017-04-08 00:00:00', '0');
INSERT INTO `merchandise` VALUES ('10', '1', '有机散叶生菜--建议当日食用', '17.00', '0.00', 'm4', '/Picture/10.jpg', '非常好吃的蔬菜', '友农蔬菜厂', '2017-03-20 00:00:00', '0');
INSERT INTO `merchandise` VALUES ('11', '2', '有机油麦菜---建议当日食用', '5.00', '4.00', 'm9', '/Picture/11.jpg', '非常好吃的蔬菜', '友农蔬菜厂', '2012-03-16 00:00:00', '1');
INSERT INTO `merchandise` VALUES ('12', '3', '有机圆白菜', '6.00', '4.00', 'n6', '/Picture/12.jpg', '非常好吃的蔬菜', '友农蔬菜厂', '2017-04-09 00:00:00', '1');
INSERT INTO `merchandise` VALUES ('13', '3', '生态芹菜', '3.00', '0.00', 'n5', '/Picture/13.jpg', '非常好吃的蔬菜', '友农蔬菜厂', '2017-04-09 00:00:00', '0');
INSERT INTO `merchandise` VALUES ('14', '3', '有机黄瓜', '6.00', '0.00', 'm5', '/Picture/14.jpg', '非常好吃的蔬菜', '友农蔬菜厂', '2017-04-09 00:00:00', '0');
INSERT INTO `merchandise` VALUES ('15', '1', '有机白萝卜', '9.00', '8.00', 'm8', '/Picture/16.jpg', '非常好吃的蔬菜', '友农蔬菜厂', '2017-01-14 00:00:00', '1');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `Member` int(4) NOT NULL,
  `Cart` int(4) NOT NULL,
  `OrderNO` char(20) DEFAULT NULL,
  `OrderDate` datetime DEFAULT NULL,
  `OrderStatus` int(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('2', '8', '2', '1240296879656', '2017-04-21 14:54:39', '1');
INSERT INTO `orders` VALUES ('3', '3', '3', '1240297000546', '2017-04-21 14:56:40', '2');
INSERT INTO `orders` VALUES ('4', '1', '5', '1240299012625', '2017-04-21 15:30:12', '3');
INSERT INTO `orders` VALUES ('5', '2', '6', '1240299319328', '2017-04-21 15:35:19', '1');
INSERT INTO `orders` VALUES ('6', '11', '8', '1363938292176', '2017-03-22 15:44:52', '3');
