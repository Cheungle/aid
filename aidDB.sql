-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: aid
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `Comment_ID` int(11) NOT NULL,
  `Comment_Content` varchar(50) NOT NULL,
  `Comment_PublishTime` date NOT NULL,
  `Comment_Source_fk` char(12) NOT NULL,
  `Comment_ThemeID_fk` int(11) NOT NULL,
  `Comment_State` int(11) NOT NULL,
  `Comment_PreCmmtID_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`Comment_ID`),
  UNIQUE KEY `Comment_ID_UNIQUE` (`Comment_ID`),
  KEY `Comment_Source_fk` (`Comment_Source_fk`),
  KEY `Comment_PreCmmtID_fk_idx` (`Comment_PreCmmtID_fk`),
  KEY `Comment_ThemeID_fk_idx` (`Comment_ThemeID_fk`),
  CONSTRAINT `Comment_PreCmmtID_fk` FOREIGN KEY (`Comment_PreCmmtID_fk`) REFERENCES `reviewedcomment` (`RC_ID_fk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Comment_Source_fk` FOREIGN KEY (`Comment_Source_fk`) REFERENCES `user` (`User_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Comment_ThemeID_fk` FOREIGN KEY (`Comment_ThemeID_fk`) REFERENCES `theme` (`Theme_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'今天确诊人数下降了','2020-05-03','15186861111',2,1,NULL),(2,'真的！','2020-05-03','15186862222',2,1,1);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `completedtask`
--

DROP TABLE IF EXISTS `completedtask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `completedtask` (
  `CT_ID_fk` int(11) NOT NULL,
  `CT_CompletedTime` date NOT NULL,
  PRIMARY KEY (`CT_ID_fk`),
  UNIQUE KEY `CT_ID_UNIQUE` (`CT_ID_fk`),
  CONSTRAINT `CT_ID_fk` FOREIGN KEY (`CT_ID_fk`) REFERENCES `receivedtask` (`RCT_ID_fk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `completedtask`
--

LOCK TABLES `completedtask` WRITE;
/*!40000 ALTER TABLE `completedtask` DISABLE KEYS */;
INSERT INTO `completedtask` VALUES (2,'2020-05-08');
/*!40000 ALTER TABLE `completedtask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data`
--

DROP TABLE IF EXISTS `data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data` (
  `Data_ID` int(11) NOT NULL,
  `Data_Place` varchar(10) NOT NULL,
  `Data_Data` int(11) NOT NULL,
  `Data_Time` date NOT NULL,
  `Data_Source` varchar(20) NOT NULL,
  PRIMARY KEY (`Data_ID`),
  UNIQUE KEY `Data_ID_UNIQUE` (`Data_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data`
--

LOCK TABLES `data` WRITE;
/*!40000 ALTER TABLE `data` DISABLE KEYS */;
INSERT INTO `data` VALUES (1,'上海',26,'2020-05-01','国家卫健委');
/*!40000 ALTER TABLE `data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `information`
--

DROP TABLE IF EXISTS `information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `information` (
  `Info_ID` int(11) NOT NULL,
  `Info_Title` varchar(45) NOT NULL,
  `Info_Content` varchar(200) NOT NULL,
  `Info_Time` date NOT NULL,
  `Info_Source` varchar(20) NOT NULL,
  PRIMARY KEY (`Info_ID`),
  UNIQUE KEY `Info_ID_UNIQUE` (`Info_ID`),
  UNIQUE KEY `Info_Title_UNIQUE` (`Info_Title`),
  UNIQUE KEY `Info_Content_UNIQUE` (`Info_Content`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `information`
--

LOCK TABLES `information` WRITE;
/*!40000 ALTER TABLE `information` DISABLE KEYS */;
INSERT INTO `information` VALUES (1,'31省区市新增7例境外输入病例','5月25日0—24时，31个省（自治区、直辖市）和新疆生产建设兵团报告新增确诊病例7例，均为境外输入病例（内蒙古5例，上海1例，福建1例）；无新增死亡病例；无新增疑似病例。','2020-05-26','央视新闻');
/*!40000 ALTER TABLE `information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `Manager_ID` char(13) NOT NULL,
  `Manager_Password` varchar(20) NOT NULL,
  PRIMARY KEY (`Manager_ID`),
  UNIQUE KEY `Manager_ID_UNIQUE` (`Manager_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES ('15186861111M','123');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `Message_ID` int(11) NOT NULL,
  `Message_UserID_fk` char(12) NOT NULL,
  `Message_Time` date NOT NULL,
  `Message_WindowID_fk` int(11) NOT NULL,
  `Message_State1` int(11) NOT NULL,
  `Message_State2` int(11) NOT NULL,
  `Message_Content` varchar(50) NOT NULL,
  PRIMARY KEY (`Message_ID`),
  UNIQUE KEY `Message_ID_UNIQUE` (`Message_ID`),
  KEY `Message_WindowID_fk` (`Message_WindowID_fk`),
  KEY `Message_UserID_fk` (`Message_UserID_fk`),
  CONSTRAINT `Message_UserID_fk` FOREIGN KEY (`Message_UserID_fk`) REFERENCES `user` (`User_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Message_WindowID_fk` FOREIGN KEY (`Message_WindowID_fk`) REFERENCES `messagewindow` (`MW_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'15186861111','2020-05-02',1,1,1,'请问防护服还有吗'),(2,'15186862222','2020-05-02',1,1,1,'您好，还有的');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messagewindow`
--

DROP TABLE IF EXISTS `messagewindow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messagewindow` (
  `MW_ID` int(11) NOT NULL,
  `MW_UserID1_fk` char(12) NOT NULL,
  `MW_UserID2_fk` char(12) NOT NULL,
  `MW_Time` date NOT NULL,
  `MW_TaskID_fk` int(11) NOT NULL,
  PRIMARY KEY (`MW_ID`),
  UNIQUE KEY `MW_ID_UNIQUE` (`MW_ID`),
  UNIQUE KEY `MW_TaskID_fk_UNIQUE` (`MW_TaskID_fk`),
  KEY `MW_UserID1_fk` (`MW_UserID1_fk`),
  KEY `MW_UserID2_fk` (`MW_UserID2_fk`),
  CONSTRAINT `MW_TaskID_fk` FOREIGN KEY (`MW_TaskID_fk`) REFERENCES `task` (`Task_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `MW_UserID1_fk` FOREIGN KEY (`MW_UserID1_fk`) REFERENCES `user` (`User_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `MW_UserID2_fk` FOREIGN KEY (`MW_UserID2_fk`) REFERENCES `user` (`User_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messagewindow`
--

LOCK TABLES `messagewindow` WRITE;
/*!40000 ALTER TABLE `messagewindow` DISABLE KEYS */;
INSERT INTO `messagewindow` VALUES (1,'15186861111','15186862222','2020-05-01',2);
/*!40000 ALTER TABLE `messagewindow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receivedtask`
--

DROP TABLE IF EXISTS `receivedtask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receivedtask` (
  `RCT_ID_fk` int(11) NOT NULL,
  `RCT_ReceiverID_fk` char(12) NOT NULL,
  `RCT_ReceivedTime` date NOT NULL,
  UNIQUE KEY `RCT_ID_fk_UNIQUE` (`RCT_ID_fk`),
  KEY `RCT_ReceiverID_fk` (`RCT_ReceiverID_fk`),
  CONSTRAINT `RCT_ReceiverID_fk` FOREIGN KEY (`RCT_ReceiverID_fk`) REFERENCES `user` (`User_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receivedtask`
--

LOCK TABLES `receivedtask` WRITE;
/*!40000 ALTER TABLE `receivedtask` DISABLE KEYS */;
INSERT INTO `receivedtask` VALUES (2,'15186862222','2020-05-20'),(3,'15186861111','2020-05-20');
/*!40000 ALTER TABLE `receivedtask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviewedcomment`
--

DROP TABLE IF EXISTS `reviewedcomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviewedcomment` (
  `RC_ID_fk` int(11) NOT NULL,
  `RC_ManagerID_fk` char(13) NOT NULL,
  `RC_ReviewTime` date NOT NULL,
  `RC_State` int(11) NOT NULL,
  PRIMARY KEY (`RC_ID_fk`),
  UNIQUE KEY `RC_ID_fk_UNIQUE` (`RC_ID_fk`),
  KEY `RC_ManagerID_fk` (`RC_ManagerID_fk`),
  CONSTRAINT `RC_ID_fk` FOREIGN KEY (`RC_ID_fk`) REFERENCES `comment` (`Comment_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `RC_ManagerID_fk` FOREIGN KEY (`RC_ManagerID_fk`) REFERENCES `manager` (`Manager_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviewedcomment`
--

LOCK TABLES `reviewedcomment` WRITE;
/*!40000 ALTER TABLE `reviewedcomment` DISABLE KEYS */;
INSERT INTO `reviewedcomment` VALUES (1,'15186861111M','2020-05-03',1);
/*!40000 ALTER TABLE `reviewedcomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviewedtask`
--

DROP TABLE IF EXISTS `reviewedtask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviewedtask` (
  `RVT_ID_fk` int(11) NOT NULL,
  `RVT_ManagerID_fk` char(13) NOT NULL,
  `RVT_ReviewTime` date NOT NULL,
  `RVT_State` int(11) NOT NULL,
  PRIMARY KEY (`RVT_ID_fk`),
  UNIQUE KEY `RVT_ID_fk_UNIQUE` (`RVT_ID_fk`),
  KEY `RVT_ManagerID_fk` (`RVT_ManagerID_fk`),
  CONSTRAINT `RVT_ID_fk` FOREIGN KEY (`RVT_ID_fk`) REFERENCES `task` (`Task_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `RVT_ManagerID_fk` FOREIGN KEY (`RVT_ManagerID_fk`) REFERENCES `manager` (`Manager_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviewedtask`
--

LOCK TABLES `reviewedtask` WRITE;
/*!40000 ALTER TABLE `reviewedtask` DISABLE KEYS */;
INSERT INTO `reviewedtask` VALUES (1,'15186861111M','2020-05-28',1),(2,'15186861111M','2020-05-01',1),(3,'15186861111M','2020-05-01',1),(4,'15186861111M','2020-05-01',2);
/*!40000 ALTER TABLE `reviewedtask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `Task_ID` int(11) NOT NULL,
  `Task_CreatorID_fk` char(12) NOT NULL,
  `Task_Content` varchar(100) NOT NULL,
  `Task_Time` date DEFAULT NULL,
  `Task_Type` int(11) NOT NULL,
  `Task_Place` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Task_ID`),
  UNIQUE KEY `Task_ID_UNIQUE` (`Task_ID`),
  KEY `Task_CreatorID_fk` (`Task_CreatorID_fk`),
  CONSTRAINT `Task_CreatorID_fk` FOREIGN KEY (`Task_CreatorID_fk`) REFERENCES `user` (`User_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,'15186861111','口罩20个，防护服10个','2020-05-27',1,'上海'),(2,'15186861111','防护服20个','2020-05-01',1,'北京'),(3,'15186862222','防护服20个','2020-04-28',2,'北京'),(4,'15186862222','口罩10个','2020-04-04',2,'北京'),(5,'15186862222','志愿者2位','2020-05-05',1,'北京');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theme`
--

DROP TABLE IF EXISTS `theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `theme` (
  `Theme_ID` int(11) NOT NULL,
  `Theme_Content` varchar(100) NOT NULL,
  `Theme_Time` date NOT NULL,
  `Theme_ManagerID_fk` char(13) NOT NULL,
  PRIMARY KEY (`Theme_ID`),
  UNIQUE KEY `Theme_ID_UNIQUE` (`Theme_ID`),
  KEY `Theme_ManagerID_fk` (`Theme_ManagerID_fk`),
  CONSTRAINT `Theme_ManagerID_fk` FOREIGN KEY (`Theme_ManagerID_fk`) REFERENCES `manager` (`Manager_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theme`
--

LOCK TABLES `theme` WRITE;
/*!40000 ALTER TABLE `theme` DISABLE KEYS */;
INSERT INTO `theme` VALUES (1,'新冠病毒','2020-05-01','15186861111M'),(2,'今日确诊','2020-05-03','15186861111M');
/*!40000 ALTER TABLE `theme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `User_ID` char(12) NOT NULL,
  `User_Pwd` varchar(15) NOT NULL,
  `User_Sex` tinyint(1) NOT NULL,
  `User_Name` varchar(20) NOT NULL,
  `User_Head` varchar(20) DEFAULT NULL,
  `User_Address` varchar(50) DEFAULT NULL,
  `User_Age` int(11) DEFAULT NULL,
  `User_Identity` varchar(18) DEFAULT NULL,
  `User_RealName` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`User_ID`),
  UNIQUE KEY `User_ID_UNIQUE` (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('15186861111','123456',1,'LU','image','上海',20,NULL,NULL),('15186862222','123456',0,'Li','image','北京',30,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'aid'
--

--
-- Dumping routines for database 'aid'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-28  3:12:06
