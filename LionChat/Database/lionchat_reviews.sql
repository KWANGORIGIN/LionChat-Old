-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: lionchat
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `id` int NOT NULL AUTO_INCREMENT,
  `WifiAssistanceIntentRating` int DEFAULT '0',
  `CampusEventsIntentRating` int DEFAULT '0',
  `ErieInfoIntentRating` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (1,5,0,0),(2,5,0,0),(3,5,0,0),(4,5,0,0),(5,0,5,0),(6,0,0,5),(7,0,0,5),(8,0,0,5),(9,0,0,5),(10,0,0,5),(11,0,5,0),(12,0,5,0),(13,0,5,0),(14,0,5,0),(15,0,5,0),(16,0,5,0),(17,0,1,0),(18,0,1,0),(19,2,0,0),(20,2,0,0),(21,2,0,0),(22,2,0,0),(23,2,0,0),(24,2,0,0),(25,0,0,4),(26,0,0,4),(27,0,0,4),(28,0,0,4),(29,0,0,4),(30,0,0,4),(31,0,0,4),(32,0,0,1),(33,0,0,1),(34,0,0,1),(35,0,0,1),(36,0,1,0),(37,0,1,0),(38,0,1,0),(39,0,1,0),(40,0,3,0),(41,0,3,0),(42,0,3,0),(43,0,3,0),(44,0,3,0),(45,5,0,0),(46,5,0,0),(47,5,0,0),(48,5,0,0),(49,5,0,0),(50,5,0,0),(51,5,0,0),(52,5,0,0),(53,5,0,0),(54,5,0,0),(55,5,0,0),(56,5,0,0),(57,5,0,0),(58,5,0,0),(59,5,0,0),(60,5,0,0),(61,0,2,0),(62,0,2,0),(63,0,2,0),(64,0,2,0),(65,0,2,0),(66,0,0,5),(67,4,0,0),(68,4,0,0),(69,4,0,0),(70,4,0,0),(71,4,0,0),(72,4,0,0),(73,4,0,0),(74,4,0,0),(75,4,0,0),(76,4,0,0),(77,4,0,0),(78,4,0,0),(79,4,0,0),(80,4,0,0),(81,4,0,0),(82,4,0,0),(83,4,0,0),(84,4,0,0),(85,0,5,0),(86,0,5,0),(87,0,5,0),(88,0,5,0),(89,0,5,0),(90,0,5,0),(91,0,5,0),(92,0,5,0),(93,0,5,0),(94,0,5,0),(95,0,5,0),(96,0,5,0),(97,0,5,0),(98,0,5,0),(99,0,5,0),(100,0,5,0),(101,0,5,0),(102,0,5,0),(103,0,5,0),(104,0,5,0),(105,0,5,0),(106,0,5,0),(107,0,5,0),(108,0,5,0),(109,0,5,0),(110,0,5,0),(111,0,5,0),(112,0,5,0),(113,0,5,0),(114,0,5,0),(115,0,5,0),(116,0,5,0),(117,0,5,0),(118,0,5,0),(119,0,5,0),(120,0,5,0),(121,0,5,0),(122,0,5,0),(123,0,5,0),(124,0,5,0),(125,0,5,0),(126,0,5,0),(127,0,5,0),(128,0,5,0),(129,0,5,0),(130,0,5,0),(131,0,5,0),(132,0,5,0),(133,0,5,0),(134,0,5,0),(135,0,5,0),(136,0,5,0),(137,0,5,0),(138,0,5,0),(139,0,5,0),(140,0,5,0),(141,0,5,0),(142,0,5,0),(143,0,5,0),(144,0,5,0),(145,0,5,0),(146,0,5,0),(147,0,5,0),(148,0,5,0),(149,0,5,0),(150,0,5,0),(151,0,5,0),(152,0,5,0),(153,0,5,0),(154,0,5,0),(155,0,5,0),(156,0,5,0),(157,0,5,0),(158,0,5,0),(159,0,5,0),(160,0,5,0),(161,0,5,0),(162,0,5,0),(163,0,5,0),(164,0,5,0),(165,0,5,0),(166,0,5,0),(167,0,5,0),(168,0,5,0),(169,0,5,0),(170,0,5,0),(171,0,5,0),(172,0,5,0),(173,0,5,0),(174,0,5,0),(175,0,5,0),(176,0,5,0),(177,0,5,0),(178,0,5,0),(179,0,5,0),(180,0,5,0),(181,0,5,0),(182,0,5,0),(183,0,5,0),(184,0,5,0),(185,0,0,4),(186,5,0,0),(187,0,3,0),(188,5,0,0),(189,5,0,0),(190,0,5,0),(191,0,5,0),(192,0,5,0),(193,0,5,0),(194,0,5,0),(195,0,5,0),(196,0,5,0),(197,0,0,5),(198,0,0,5),(199,5,0,0),(200,5,0,0),(201,5,0,0),(202,4,0,0),(203,0,5,0);
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-13 22:59:33
