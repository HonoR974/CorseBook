
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (1,'Catenacciu'),(5,'Catenacciu'),(9,'Catenacciu'),(13,'Partie de Petanque a Zoza ');
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_users`
--

DROP TABLE IF EXISTS `chat_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_users` (
  `chats_id` bigint NOT NULL,
  `users_id` bigint NOT NULL,
  KEY `FKorvljukoxcj3j8l0vryq2sme5` (`users_id`),
  KEY `FK2u22ypd249k2f48bkaou6v1kd` (`chats_id`),
  CONSTRAINT `FK2u22ypd249k2f48bkaou6v1kd` FOREIGN KEY (`chats_id`) REFERENCES `chat` (`id`),
  CONSTRAINT `FKorvljukoxcj3j8l0vryq2sme5` FOREIGN KEY (`users_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_users`
--

LOCK TABLES `chat_users` WRITE;
/*!40000 ALTER TABLE `chat_users` DISABLE KEYS */;
INSERT INTO `chat_users` VALUES (1,6),(5,6),(9,6),(13,2);
/*!40000 ALTER TABLE `chat_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint NOT NULL,
  `contenu` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `event_id` bigint DEFAULT NULL,
  `publication_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhr48nopy5aorw0ta1ii704tpu` (`event_id`),
  KEY `FK2h0n8h4v7itad1qmlwbvkfqgf` (`publication_id`),
  KEY `FK8kcum44fvpupyw6f5baccx25c` (`user_id`),
  CONSTRAINT `FK2h0n8h4v7itad1qmlwbvkfqgf` FOREIGN KEY (`publication_id`) REFERENCES `publication` (`id`),
  CONSTRAINT `FK8kcum44fvpupyw6f5baccx25c` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKhr48nopy5aorw0ta1ii704tpu` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_like_user`
--

DROP TABLE IF EXISTS `comment_like_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_like_user` (
  `comments_liked_id` bigint NOT NULL,
  `like_user_id` bigint NOT NULL,
  KEY `FKoavuu3v7da0op1v1qolm42w9n` (`like_user_id`),
  KEY `FKh0vw6g2q56krvvbop66yuqjle` (`comments_liked_id`),
  CONSTRAINT `FKh0vw6g2q56krvvbop66yuqjle` FOREIGN KEY (`comments_liked_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `FKoavuu3v7da0op1v1qolm42w9n` FOREIGN KEY (`like_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_like_user`
--

LOCK TABLES `comment_like_user` WRITE;
/*!40000 ALTER TABLE `comment_like_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_like_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contenu` varchar(255) DEFAULT NULL,
  `date_create` datetime DEFAULT NULL,
  `date_debut` datetime DEFAULT NULL,
  `date_fin` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `name_creator` varchar(255) DEFAULT NULL,
  `chat_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKy946htpod7xolsrrk37dldmw` (`chat_id`),
  CONSTRAINT `FKy946htpod7xolsrrk37dldmw` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (3,' Pour la fêtes de Pâques la commune du Taravu organise une marche de village en commencant pas le village de Ste Marie de Siché.','2022-04-30 16:29:04','2022-04-14 22:00:00','2022-04-16 22:00:00','Catenacciu','Mairie Azilone',9),(4,'Partie de petanque organisé place de la mairie debute vers 14 Heures. ','2022-05-01 14:56:15','2022-05-07 22:00:00','2022-04-30 22:00:00','Partie de Petanque a Zoza ','sebastien974',13);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_list_user`
--

DROP TABLE IF EXISTS `event_list_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event_list_user` (
  `event_id` bigint NOT NULL,
  `list_user_id` bigint NOT NULL,
  KEY `FKnqu1dua1rj7rpgbdxc791m8xb` (`list_user_id`),
  KEY `FKr2xc3t8igj5kwn8mp1j85c3cu` (`event_id`),
  CONSTRAINT `FKnqu1dua1rj7rpgbdxc791m8xb` FOREIGN KEY (`list_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKr2xc3t8igj5kwn8mp1j85c3cu` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_list_user`
--

LOCK TABLES `event_list_user` WRITE;
/*!40000 ALTER TABLE `event_list_user` DISABLE KEYS */;
INSERT INTO `event_list_user` VALUES (3,6),(4,2);
/*!40000 ALTER TABLE `event_list_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `event_id` bigint DEFAULT NULL,
  `publication_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8wmrqwmt68do2u3oq1ps5ewui` (`event_id`),
  KEY `FKjghce639sa72nfq7t60welrl7` (`publication_id`),
  CONSTRAINT `FK8wmrqwmt68do2u3oq1ps5ewui` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  CONSTRAINT `FKjghce639sa72nfq7t60welrl7` FOREIGN KEY (`publication_id`) REFERENCES `publication` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES (4,'Catenacciu.jpg','https://testp12.s3.eu-west-3.amazonaws.com/image/Catenacciu.jpg',3,NULL),(5,'paque.jpg','https://testp12.s3.eu-west-3.amazonaws.com/image/paque.jpg',3,NULL),(6,'panorama.jpg','https://testp12.s3.eu-west-3.amazonaws.com/image/panorama.jpg',NULL,3),(7,'porto.jpg','https://testp12.s3.eu-west-3.amazonaws.com/image/porto.jpg',NULL,3),(8,'Catenacciu.jpg','https://testp12.s3.eu-west-3.amazonaws.com/image/Catenacciu.jpg',NULL,4),(9,'paque.jpg','https://testp12.s3.eu-west-3.amazonaws.com/image/paque.jpg',NULL,4),(10,'promenade.jpg','https://testp12.s3.eu-west-3.amazonaws.com/image/promenade.jpg',NULL,4);
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (18);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marker`
--

DROP TABLE IF EXISTS `marker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marker` (
  `id` bigint NOT NULL,
  `label` varchar(255) DEFAULT NULL,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL,
  `event_id` bigint DEFAULT NULL,
  `publication_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmmv7f79wcmtyikb46k8wnjva8` (`event_id`),
  KEY `FKebv8iu7a6pv65bf3j2ccr68ss` (`publication_id`),
  CONSTRAINT `FKebv8iu7a6pv65bf3j2ccr68ss` FOREIGN KEY (`publication_id`) REFERENCES `publication` (`id`),
  CONSTRAINT `FKmmv7f79wcmtyikb46k8wnjva8` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marker`
--

LOCK TABLES `marker` WRITE;
/*!40000 ALTER TABLE `marker` DISABLE KEYS */;
INSERT INTO `marker` VALUES (6,NULL,41.8717,8.96415,3,NULL),(7,NULL,41.9074,8.82201,3,NULL),(8,NULL,41.8527,9.02045,3,NULL),(10,NULL,42.226,8.6476,NULL,3),(11,NULL,42.1304,8.59816,NULL,3),(12,NULL,41.7299,8.96071,4,NULL),(14,NULL,42.0244,8.98818,NULL,4),(15,NULL,41.91,8.73,NULL,4),(16,NULL,41.9345,8.94973,NULL,4),(17,NULL,42.0407,8.70803,NULL,4);
/*!40000 ALTER TABLE `marker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` bigint NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `chat_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmejd0ykokrbuekwwgd5a5xt8a` (`chat_id`),
  CONSTRAINT `FKmejd0ykokrbuekwwgd5a5xt8a` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publication`
--

DROP TABLE IF EXISTS `publication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publication` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contenu` varchar(255) DEFAULT NULL,
  `count_like` bigint NOT NULL,
  `date_create` datetime DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq2ei3a07e3ln96uel4alss2u7` (`user_id`),
  CONSTRAINT `FKq2ei3a07e3ln96uel4alss2u7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publication`
--

LOCK TABLES `publication` WRITE;
/*!40000 ALTER TABLE `publication` DISABLE KEYS */;
INSERT INTO `publication` VALUES (2,'Premiere Publication du site Corsebook!!!',0,'2022-05-01 14:26:53',6),(3,'photo de mon dernier passage a Porto ',0,'2022-05-01 14:54:36',2),(4,'Image Souvenir du Catenacciu, et la traversé des villages ',0,'2022-05-01 15:01:07',1);
/*!40000 ALTER TABLE `publication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publication_like_user`
--

DROP TABLE IF EXISTS `publication_like_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publication_like_user` (
  `publications_liked_id` bigint NOT NULL,
  `like_user_id` bigint NOT NULL,
  KEY `FKpiwge79xh96dvl2aau8fw0n4o` (`like_user_id`),
  KEY `FK235t3nw3im6n9rp96p9iooxoy` (`publications_liked_id`),
  CONSTRAINT `FK235t3nw3im6n9rp96p9iooxoy` FOREIGN KEY (`publications_liked_id`) REFERENCES `publication` (`id`),
  CONSTRAINT `FKpiwge79xh96dvl2aau8fw0n4o` FOREIGN KEY (`like_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publication_like_user`
--

LOCK TABLES `publication_like_user` WRITE;
/*!40000 ALTER TABLE `publication_like_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `publication_like_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `matching_password` varchar(255) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `file_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKddgdiuxalcbqngifyd2l28h71` (`file_id`),
  CONSTRAINT `FKddgdiuxalcbqngifyd2l28h71` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'angela123@gmail.com','$2a$10$jHA0MD5.bbqmMVHFiTa.re8Obu61sGZguAlCuBN2P4yeSP0JmrAwK','$2a$10$jHA0MD5.bbqmMVHFiTa.re8Obu61sGZguAlCuBN2P4yeSP0JmrAwK','angela123',NULL),(2,'sebastien974@gmail.com','$2a$10$IdD2Z/UhnePuGXA4h/BnbukfcLKT4lyXQgN55xVHTwVGQHGqQUbFG','$2a$10$IdD2Z/UhnePuGXA4h/BnbukfcLKT4lyXQgN55xVHTwVGQHGqQUbFG','sebastien974',NULL),(3,'alex2A@gmail.com','$2a$10$sNdLqdo9En7WfdYZAjVvlucDX/jAoNM1j.FNx0ZOdtaTiS3/VWYWC','$2a$10$sNdLqdo9En7WfdYZAjVvlucDX/jAoNM1j.FNx0ZOdtaTiS3/VWYWC','alex2A',NULL),(4,'tomy2B@gmail.com','$2a$10$8sx0py6EGMhFvwRW8GU9xu78DPEzVcmAnQoy3hQ8UurdTj1lQ5T.a','$2a$10$8sx0py6EGMhFvwRW8GU9xu78DPEzVcmAnQoy3hQ8UurdTj1lQ5T.a','tomy2B',NULL),(5,'laetitiaS2A@gmail.com','$2a$10$HcmJbtC/E734fZBDcpasvei5spwm4te72RxF3eX15tOBBub520PZ6','$2a$10$HcmJbtC/E734fZBDcpasvei5spwm4te72RxF3eX15tOBBub520PZ6','laetitiaS2A',NULL),(6,'AziloneA@gmail.com','$2a$10$y2M09jaVRFN.ihQJlsqRYu1Zms9i6FD.TDmcPf.7OpBKKM3qAf3N6','$2a$10$y2M09jaVRFN.ihQJlsqRYu1Zms9i6FD.TDmcPf.7OpBKKM3qAf3N6','Mairie Azilone',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_list_contact`
--

DROP TABLE IF EXISTS `user_list_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_list_contact` (
  `user_id` bigint NOT NULL,
  `list_contact_id` bigint NOT NULL,
  KEY `FKjxkm68phpji7ni1fsuf35wcu5` (`list_contact_id`),
  KEY `FK6ao0d3wp4q8vrbac3th8x3gcs` (`user_id`),
  CONSTRAINT `FK6ao0d3wp4q8vrbac3th8x3gcs` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKjxkm68phpji7ni1fsuf35wcu5` FOREIGN KEY (`list_contact_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_list_contact`
--

LOCK TABLES `user_list_contact` WRITE;
/*!40000 ALTER TABLE `user_list_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_list_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_list_event`
--

DROP TABLE IF EXISTS `user_list_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_list_event` (
  `user_id` bigint NOT NULL,
  `list_event_id` bigint NOT NULL,
  KEY `FKapm39atrnkxke47c9jl397qrb` (`list_event_id`),
  KEY `FKgum2nkftpbpihba4qf8b6wstv` (`user_id`),
  CONSTRAINT `FKapm39atrnkxke47c9jl397qrb` FOREIGN KEY (`list_event_id`) REFERENCES `event` (`id`),
  CONSTRAINT `FKgum2nkftpbpihba4qf8b6wstv` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_list_event`
--

LOCK TABLES `user_list_event` WRITE;
/*!40000 ALTER TABLE `user_list_event` DISABLE KEYS */;
INSERT INTO `user_list_event` VALUES (6,3),(2,4);
/*!40000 ALTER TABLE `user_list_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_list_invitation`
--

DROP TABLE IF EXISTS `user_list_invitation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_list_invitation` (
  `user_id` bigint NOT NULL,
  `list_invitation_id` bigint NOT NULL,
  KEY `FK28gkbk6d1jig05m5t52oo1mqj` (`list_invitation_id`),
  KEY `FKlg0cdv6tm3jpbdfdas1cqtbt4` (`user_id`),
  CONSTRAINT `FK28gkbk6d1jig05m5t52oo1mqj` FOREIGN KEY (`list_invitation_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKlg0cdv6tm3jpbdfdas1cqtbt4` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_list_invitation`
--

LOCK TABLES `user_list_invitation` WRITE;
/*!40000 ALTER TABLE `user_list_invitation` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_list_invitation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-01 17:02:28
