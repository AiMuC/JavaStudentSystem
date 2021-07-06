/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.26 : Database - studentdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`studentdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `studentdb`;

/*Table structure for table `students` */

DROP TABLE IF EXISTS `students`;

CREATE TABLE `students` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生唯一编号',
  `stuid` bigint(20) NOT NULL COMMENT '学号',
  `password` varchar(20) NOT NULL COMMENT '学生登入密码',
  `SID` varchar(20) NOT NULL COMMENT '学生身份证号',
  `sex` varchar(1) NOT NULL COMMENT '学生性别',
  `stuname` varchar(20) NOT NULL COMMENT '学生姓名',
  `age` int(3) NOT NULL COMMENT '学生年龄',
  PRIMARY KEY (`id`,`stuid`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `students` */

insert  into `students`(`id`,`stuid`,`password`,`SID`,`sex`,`stuname`,`age`) values (5,10005,'123456','350124195701570465','男','王七',18),(6,10006,'123456','350124195701570466','男','王九',18),(4,10004,'123456','350124195701570464','女','李武',18),(3,10003,'123456','350124195701570463','女','吧啊',18),(2,10002,'123456','350124195701570462','男','阿布比',16),(1,10001,'123456','350124195701570461','男','阿布',16);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `userid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL COMMENT '用户密码',
  `usertype` int(2) NOT NULL DEFAULT '0' COMMENT '用户类型',
  `l_ock` int(1) NOT NULL DEFAULT '1' COMMENT '用户状态',
  PRIMARY KEY (`userid`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`userid`,`username`,`password`,`usertype`,`l_ock`) values (1,'admin','password',1,0),(2,'user','password',0,0),(30,'lockuser','password',0,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
