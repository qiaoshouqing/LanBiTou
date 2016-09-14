CREATE DATABASE  IF NOT EXISTS `lanbitou` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `lanbitou`;
-- MySQL dump 10.13  Distrib 5.7.9, for linux-glibc2.5 (x86_64)
--
-- Host: localhost    Database: lanbitou
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bills`
--

DROP TABLE IF EXISTS `bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `type` varchar(10) NOT NULL,
  `money` double NOT NULL,
  `folder` varchar(20) NOT NULL,
  `remark` varchar(300) NOT NULL,
  `bill_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_bills_1_idx` (`folder`,`uid`),
  KEY `bills_ibfk_1_idx` (`uid`),
  CONSTRAINT `bills_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bills`
--

LOCK TABLES `bills` WRITE;
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;
INSERT INTO `bills` VALUES (116,1,'约定',1234,'你说什么？吼吼','是一首歌。哈哈','2016-05-25 16:00:00'),(118,1,'库',68,'你说什么？吼吼','理解','2016-05-25 16:00:00'),(123,1,'有位朋友',-52,'你说什么？吼吼','还记得吗！','2016-05-25 16:00:00'),(124,1,'哈喽',52,'你说什么？吼吼','啦啦','2016-05-25 16:00:00'),(147,1,'0',0,'吉他的和弦2','0','2016-06-04 13:08:58'),(150,1,'0',0,'勿忘添加','0','2016-06-04 13:19:28'),(151,1,'呦吼',154,'你说什么？吼吼','啦啦','2016-06-06 16:00:00'),(152,1,'吃',455,'吉他的和弦2','吃了','2016-06-07 16:00:00'),(154,1,'买',-54,'吉他的和弦2','一个吉他。','2016-06-07 16:00:00'),(155,1,'买衣服',-565,'你说什么？吼吼','是个裤子','2016-06-07 16:00:00'),(156,1,'书',-23,'你说什么？吼吼','没有人给他写信的上校','2016-06-07 16:00:00'),(157,1,'哈哈',86,'你说什么？吼吼','偷','2016-06-01 16:00:00'),(158,1,'12',-85,'你说什么？吼吼','她','2016-06-07 16:00:00'),(159,1,'11',-11,'你说什么？吼吼','11','2016-06-07 16:00:00'),(162,1,'啦',55,'勿忘添加','看了看','2016-06-07 16:00:00'),(163,1,'你就',26,'勿忘添加','看来','2016-06-07 16:00:00'),(164,1,'哈哈',56,'吉他的和弦2','啦啦','2016-06-07 16:00:00'),(166,1,'我看看',55,'你说什么？吼吼','来','2016-06-01 16:00:00'),(167,1,'看来',85,'你说什么？吼吼','可怜','2016-06-07 16:00:00'),(168,1,'那就',55,'你说什么？吼吼','咯','2016-06-07 16:00:00'),(170,1,'？？',936,'你说什么？吼吼','头','2016-06-07 16:00:00'),(171,1,'哈哈',96,'勿忘添加','看来','2016-06-07 16:00:00'),(172,1,'在头',65,'勿忘添加','看来','2016-06-07 16:00:00'),(173,1,'吃法',55,'勿忘添加','可怜','2016-06-07 16:00:00');
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-12 11:37:16
