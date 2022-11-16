-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (x86_64)
--
-- Host: localhost    Database: store_ms_db
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `productid` int NOT NULL,
  `accountid` int NOT NULL,
  `quantity` int DEFAULT NULL,
  `totalprice` decimal(10,2) DEFAULT NULL,
  `discountedprice` decimal(10,2) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `createdby` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,3,1,2,10.00,5.00,'2022-11-14 06:44:13','system'),(2,2,1,2,20.00,15.00,'2022-11-14 06:27:52','system'),(3,3,1,2,40.00,33.00,'2022-11-14 06:28:17','system'),(4,4,1,3,36.00,12.00,'2022-11-14 06:28:49','system'),(6,2,2,1,1000.00,995.00,'2022-11-14 06:29:41','system'),(7,3,2,8,80.00,10.00,'2022-11-14 06:30:04','system'),(8,4,2,1,10.00,10.00,'2022-11-14 06:30:26','system'),(9,3,3,4,40.00,10.00,'2022-11-14 09:36:08','system'),(10,2,3,1,90.00,90.00,'2022-11-14 06:31:03','system'),(11,3,3,8,560.00,70.00,'2022-11-14 06:31:27','system'),(12,4,3,2,10.00,5.00,'2022-11-14 06:31:53','system'),(13,4,4,1,10.00,10.00,'2022-11-14 09:37:04','system'),(14,2,4,4,20.00,5.00,'2022-11-14 06:32:42','system'),(15,3,4,5,500.00,100.00,'2022-11-14 06:33:07','system'),(16,4,4,1,12.99,12.99,'2022-11-14 06:33:33','system');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-15 23:57:04
