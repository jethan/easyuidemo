/*
MySQL Data Transfer
Source Host: localhost
Source Database: userinfo
Date: 2015/4/1 13:59:12
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fName` varchar(255) DEFAULT NULL,
  `lName` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `deleted` boolean DEFAULT false,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `userinfo` VALUES ('3', 'fname1', 'lname1', 'male','1990-08-05','peking','(000)000-0000', 'name1@gmail.com',false);
INSERT INTO `userinfo` VALUES ('4', 'fname2', 'lname2', 'male','1990-08-05','peking','(000)000-0000', 'name2@gmail.com',false);
INSERT INTO `userinfo` VALUES ('5', 'fname3', 'lname3', 'male','1990-08-05','peking','(000)000-0000', 'name3@gmail.com',false);
INSERT INTO `userinfo` VALUES ('7', 'fname4', 'lname4', 'male','1990-08-05','peking','(000)000-0000', 'name4@gmail.com',false);
INSERT INTO `userinfo` VALUES ('8', 'fname5', 'lname5', 'male','1990-08-05','peking','(000)000-0000', 'name5@gmail.com',false);
INSERT INTO `userinfo` VALUES ('9', 'fname6', 'lname6', 'male','1990-08-05','peking','(000)000-0000', 'name6@gmail.com',false);
INSERT INTO `userinfo` VALUES ('10', 'fname7', 'lname7','male','1990-08-05','peking','(000)000-0000', 'name7@gmail.com',false);
INSERT INTO `userinfo` VALUES ('11', 'fname8', 'lname8', 'male','1990-08-05','peking','(000)000-0000', 'name8@gmail.com',false);
INSERT INTO `userinfo` VALUES ('12', 'fname9', 'lname9', 'male','1990-08-05','peking','(000)000-0000', 'name9@gmail.com',false);
INSERT INTO `userinfo` VALUES ('13', 'fname10', 'lname10', 'male','1990-08-05','peking','(000)000-0000', 'name10@gmail.com',false);


/* sql for oracle. */
CREATE TABLE userinfo(
id Number(11) NOT NULL PRIMARY KEY,
fName VARCHAR(25) Not NULL,
lName varchar(255) Not NULL,
gender varchar(255) DEFAULT NULL,
birthday Date DEFAULT NULL,
address varchar(255) DEFAULT NULL,
telephone varchar(255) DEFAULT NULL,
email varchar(255) DEFAULT NULL,
deleted char(1) DEFAULT 0 check (deleted in ('0','1'))
);

CREATE SEQUENCE user_sequences
            INCREMENT BY 1  
            START WITH 1399       
            NOMAXVALUE        
            NOCYCLE              
            CACHE 10;
/* records for oracle. */
INSERT INTO userinfo VALUES (231, 'fname1', 'lname1', 'male',to_date('1987-11-14','yyyy-mm-dd'),'peking','(000)000-0000', 'name1@gmail.com','0');
INSERT INTO userinfo VALUES (45, 'fname2', 'lname2', 'male',to_date('1987-11-14','yyyy-mm-dd'),'peking','(000)000-0000', 'name2@gmail.com','1');
