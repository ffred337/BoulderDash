-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: boulderdash
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `maps`
--

DROP TABLE IF EXISTS `maps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maps` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `content` longtext NOT NULL,
  PRIMARY KEY (`id`,`name`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DELIMITER $$
--
-- Procédures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `getLevel` (IN `p_id` INT)  READS SQL DATA
    SQL SECURITY INVOKER
SELECT content FROM maps WHERE id = p_id$$
DELIMITER ;

--
-- Dumping data for table `maps`
--

LOCK TABLES `maps` WRITE;
/*!40000 ALTER TABLE `maps` DISABLE KEYS */;
INSERT INTO `maps` VALUES (1,'map1','W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W\nW P T T G G T T T T T T T T T T T T T T T T T W T T T T T W\nW T T T T T T T T T T T T T R R R R T T T T T D T E T T T W\nW T T T D D T T T T T T T T T T T T D T T T T T T T T T T W\nW T T T T T T T T E T T T T T E T T T T T W W W W W W W W W\nW T T T T T T T T T T G T T D T T T D T T T T T T E T T T W\nW T R T T T T T R T T T T T T T T T T T T T T R T T T T T W\nW T G T G T T T G T T T T G G G G E T T T T T T T T T T T W\nW T T T E T T T T T T E T T T T T T T T T T T T R T T T T W\nW T T T T T T T D T D T T T T T T T T T T T T T T T T T T W\nW T T E T T E T T T T T T T D T T T G T T T T T T T D T T W\nW W W W W W W T T E T T T T T T T T T T T T T T T T T T T W\nW T T D T T D T T T T T T T T T T E T T T T T E T T T T T W\nW T T T T T T T T T T G G T G T T T G T T T T T T T T X T W\nW T T T T T T T T E T T T T T T T T T T T T T T T T T T T W\nW W W W W W W W W W W W W W W W W W W W W W W W W W W W W W'),(2,'map2','W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W\nW P T T G G T T T W T T T T T T T T T T T T T W T T T T D W\nW T T T T T T T T W T T T T R R R R T T T T T W T E T T T W\nW T T T D D T T T W T T T T T T T T D T T T T W T T T T T W\nW T T T T T T T T E T T T T T E T T T T T G G G G T W W W W\nW T T T T T T T T W T G T T D T T T D T T T T T T E T T T W\nW T R T T T T T R W T T T T T T T T T T T T T R T T T T T W\nW T G T G T T T G T T T T G G G G E T T T T T T T T T T T W\nW T T T E T T T T T T E T T T T T T T T T W T T R T T T T W\nW T T T T T T T D T D T T T T T T T T T T W T T T T T T T W\nW T T E T T E T T T T T T T D T T T G T T W T T T T D T T W\nW W W W W W W T T E T T T T T T T T T T T W T T T T T T T W\nW T T D T T W T T T T T T T T T T E T T T W T E T T T T T W\nW X T T T T W T T T T G G T G T T T G T T W T T T T T E T W\nW T T T T T T T T E T T T T T T T T T T T W T T T T T T T W\nW W W W W W W W W W W W W W W W W W W W W W W W W W W W W W'),(3,'map3','W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W\nW D T T G G T T T W T T T T T T T T T X T W T W T T T T D W\nW T T T T T T T T W T T T T R R R R T T T W T W T E T T T W\nW T T T D D T T T W T R T T T T T T D T T W T W T T T T T W\nW T T T T T T T T W T T T T G E T T T T T W G G G T W W W W\nW T T T T T T T T W T G T T D T T T D T T W T T T E T T T W\nW T R T T T T T R W T T T T T T T T T T T W T R T T T T T W\nW T G T G T T T G T T T T G G G G E T T T T T T T T T T T W\nW T T T E T T T T T T E T T T T T T T T T R T T R T T T T W\nW T T T T T T T D W D T T T T T T T T T T W T T T T T T T W\nW T T E T T E T T W T T T T D T T T G T T W E T T T D T T W\nW W W W W W W T T W T T T T T T T T T T T W T T T G T T T W\nW T T D T T W T T W T T T T T G T E T T T W E E T T T T T W\nW D T T T T W T T W T G G T G T T T G T T W T T T T T E T W\nW T T T T T T T T W T T T T T T T T T T T W T P T T T T T W\nW W W W W W W W W W W W W W W W W W W W W W W W W W W W W W'),(4,'map4','W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W\nW P T T T T T T T T T W T D T E T T W T T T T T T T T T D W\nW T T T T T T T T T T W T T T T T T W T E T T T T T T T T W\nW T T T E T T T T T T W T T T T T T W T T T T T T T T T T W\nW T T T T T T T T T T W T E T T E T W T T T T T T T T T T W\nW W W W W W W W T T T W T T T T T T W T T T W W W W W W W W\nW D T T T T E T T T T T T T T T T T T T E T T T T T T T D W\nW W W W W W W W T T T R T T T D T T T T T T W W W W W W W W\nW D T T T T T T T T T T T T T T T T T T T T T T T T T T D W\nW W W W W W W W T T T W T T T T T T W T T T W W W W W W W W\nW T E T T T E T T T T W T T T T T E W T T T T R G T T T T W\nW T T T T T T T T T T W T T T T T T W T T T T T E T T T T W\nW T T T T T T T E T T W T E T T E T W T T T T T T T T T T W\nW T T T T T T T T T T W T T T T T T W T T E T T E T T T T W\nW D T T E T T T T T T W T T T T D T W T T T T T T T T T D W\nW W W W W W W W W W W W W W W W W W W W W W W W W W W W W W'),(5,'map5','W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W\nW E T T T T T T T W W W W W W W W W W W W T T T T X T T D W\nW T T T T T T T T T T W T T T T T T W T E T T T T T T T T W\nW T T T E T T T T T T W T T T T T T W T T T T T T T T T T W\nW W W T T T T T T T T W T E T T E T W T T T T T T T T W W W\nW W W W W W W W T T T W T T T T T T W T T T W W W W W W W W\nW D T T T T E T T T T T T T T T T T T T E T T T T T T T D W\nW W W W W W W W T T T R T T T D T T T T T T W W W W W W W W\nW D T T T T T T T T T T T T P T T T T T T T T T T D T T D W\nW W W W W W W W T T T W T T T T T T W T T T W W W W W W W W\nW W W T T T E T T T T W T T T T T E W T T T T R G T T W W W\nW T T T T T T T T T T W T T T T T T W T T T T T E T T T T W\nW T D T T T T T E T T W T E T T E T W T T T T T T T T T D W\nW T T T T T D T T T T W T T T T T T W T T E T T E T T T T W\nW W W T E T T T T W W W W W W W W W W W W T T T T T T W W W\nW W W W W W W W W W W W W W W W W W W W W W W W W W W W W W');
/*!40000 ALTER TABLE `maps` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-09 14:11:32
