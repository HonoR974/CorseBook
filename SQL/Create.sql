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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-01 17:19:14
