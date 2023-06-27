/*
Navicat MySQL Data Transfer

Source Server         : 仌锅
Source Server Version : 50734
Source Host           : localhost:3306
Source Database       : qq聊天系统

Target Server Type    : MYSQL
Target Server Version : 50734
File Encoding         : 65001

Date: 2021-07-01 10:56:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `frididof111111`
-- ----------------------------
DROP TABLE IF EXISTS `frididof111111`;
CREATE TABLE `frididof111111` (
  `idOfFriends` int(11) NOT NULL,
  PRIMARY KEY (`idOfFriends`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of frididof111111
-- ----------------------------
INSERT INTO `frididof111111` VALUES ('222222');
INSERT INTO `frididof111111` VALUES ('333333');

-- ----------------------------
-- Table structure for `frididof222222`
-- ----------------------------
DROP TABLE IF EXISTS `frididof222222`;
CREATE TABLE `frididof222222` (
  `idOfFriends` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of frididof222222
-- ----------------------------
INSERT INTO `frididof222222` VALUES ('111111');
INSERT INTO `frididof222222` VALUES ('333333');

-- ----------------------------
-- Table structure for `frididof333333`
-- ----------------------------
DROP TABLE IF EXISTS `frididof333333`;
CREATE TABLE `frididof333333` (
  `idOfFriends` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of frididof333333
-- ----------------------------
INSERT INTO `frididof333333` VALUES ('111111');
INSERT INTO `frididof333333` VALUES ('222222');

-- ----------------------------
-- Table structure for `frididof444444`
-- ----------------------------
DROP TABLE IF EXISTS `frididof444444`;
CREATE TABLE `frididof444444` (
  `idOfFriends` int(11) NOT NULL,
  PRIMARY KEY (`idOfFriends`),
  UNIQUE KEY `idOfFriends` (`idOfFriends`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of frididof444444
-- ----------------------------

-- ----------------------------
-- Table structure for `messages`
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
  `Message` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of messages
-- ----------------------------
INSERT INTO `messages` VALUES (0xACED00057372001C66696E616C546573742E636F6E74726F6C6C65722E4D65737361676543F129DDD1D3060502000549000466726F6D490002746F490004747970654C000466696C6574000E4C6A6176612F696F2F46696C653B4C0004696E666F7400124C6A6176612F6C616E672F537472696E673B78700001B2070003640E0000000270740009313234323134323134);
INSERT INTO `messages` VALUES (0xACED00057372001C66696E616C546573742E636F6E74726F6C6C65722E4D65737361676543F129DDD1D3060502000549000466726F6D490002746F490004747970654C000466696C6574000E4C6A6176612F696F2F46696C653B4C0004696E666F7400124C6A6176612F6C616E672F537472696E673B78700003640E0001B207000000027074000CE59183E59183E59183E59183);
INSERT INTO `messages` VALUES (0xACED00057372001C66696E616C546573742E636F6E74726F6C6C65722E4D65737361676543F129DDD1D3060502000549000466726F6D490002746F490004747970654C000466696C6574000E4C6A6176612F696F2F46696C653B4C0004696E666F7400124C6A6176612F6C616E672F537472696E673B78700003640E0001B2070000000270740004636E6D0A);
INSERT INTO `messages` VALUES (0xACED00057372001C66696E616C546573742E636F6E74726F6C6C65722E4D65737361676543F129DDD1D3060502000549000466726F6D490002746F490004747970654C000466696C6574000E4C6A6176612F696F2F46696C653B4C0004696E666F7400124C6A6176612F6C616E672F537472696E673B78700001B2070003640E0000000270740006E683B3E595B8);

-- ----------------------------
-- Table structure for `newsof111111`
-- ----------------------------
DROP TABLE IF EXISTS `newsof111111`;
CREATE TABLE `newsof111111` (
  `Id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of newsof111111
-- ----------------------------
INSERT INTO `newsof111111` VALUES ('444444');

-- ----------------------------
-- Table structure for `newsof222222`
-- ----------------------------
DROP TABLE IF EXISTS `newsof222222`;
CREATE TABLE `newsof222222` (
  `Id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of newsof222222
-- ----------------------------

-- ----------------------------
-- Table structure for `newsof333333`
-- ----------------------------
DROP TABLE IF EXISTS `newsof333333`;
CREATE TABLE `newsof333333` (
  `Id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of newsof333333
-- ----------------------------

-- ----------------------------
-- Table structure for `newsof444444`
-- ----------------------------
DROP TABLE IF EXISTS `newsof444444`;
CREATE TABLE `newsof444444` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of newsof444444
-- ----------------------------

-- ----------------------------
-- Table structure for `personal`
-- ----------------------------
DROP TABLE IF EXISTS `personal`;
CREATE TABLE `personal` (
  `id` int(10) NOT NULL,
  `qqName` varchar(255) NOT NULL,
  `passWord` varchar(255) NOT NULL,
  `headShot` blob,
  `signature` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of personal
-- ----------------------------
INSERT INTO `personal` VALUES ('111111', '廖飞', '111111', null, '此人很懒，什么都没写。');
INSERT INTO `personal` VALUES ('222222', '黄飞鸿', '222222', null, '此人很懒，什么都没写。');
INSERT INTO `personal` VALUES ('333333', '渣渣辉', '333333', null, '此人很懒，什么都没写。');
INSERT INTO `personal` VALUES ('444444', '廖满分', '444444', null, '此人很懒，什么都没写');
