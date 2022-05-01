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
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (1,'Catenacciu'),(5,'Catenacciu'),(9,'Catenacciu'),(13,'Partie de Petanque a Zoza ');
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `chat_users`
--

LOCK TABLES `chat_users` WRITE;
/*!40000 ALTER TABLE `chat_users` DISABLE KEYS */;
INSERT INTO `chat_users` VALUES (1,6),(5,6),(9,6),(13,2);
/*!40000 ALTER TABLE `chat_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (18,'Quelle beau panorama ',NULL,NULL,3,1),(19,'Mince je voulais avoir la premiere publication !!  ',NULL,NULL,2,1),(20,'C\'était l\'été dernier ',NULL,NULL,3,2),(21,'J\'ai pu faire la traversé des village du Taravu cette année ',NULL,NULL,4,2);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `comment_like_user`
--

LOCK TABLES `comment_like_user` WRITE;
/*!40000 ALTER TABLE `comment_like_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_like_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (3,' Pour la fêtes de Pâques la commune du Taravu organise une marche de village en commencant pas le village de Ste Marie de Siché.','2022-04-30 16:29:04','2022-04-14 22:00:00','2022-04-16 22:00:00','Catenacciu','Mairie Azilone',9),(4,'Partie de petanque organisé place de la mairie debute vers 14 Heures. ','2022-05-01 14:56:15','2022-05-07 22:00:00','2022-04-30 22:00:00','Partie de Petanque a Zoza ','sebastien974',13);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `event_list_user`
--

LOCK TABLES `event_list_user` WRITE;
/*!40000 ALTER TABLE `event_list_user` DISABLE KEYS */;
INSERT INTO `event_list_user` VALUES (3,6),(4,2);
/*!40000 ALTER TABLE `event_list_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES (4,'Catenacciu.jpg','https://testp12.s3.eu-west-3.amazonaws.com/image/Catenacciu.jpg',3,NULL),(5,'paque.jpg','https://testp12.s3.eu-west-3.amazonaws.com/image/paque.jpg',3,NULL),(6,'panorama.jpg','https://testp12.s3.eu-west-3.amazonaws.com/image/panorama.jpg',NULL,3),(7,'porto.jpg','https://testp12.s3.eu-west-3.amazonaws.com/image/porto.jpg',NULL,3),(8,'Catenacciu.jpg','https://testp12.s3.eu-west-3.amazonaws.com/image/Catenacciu.jpg',NULL,4),(9,'paque.jpg','https://testp12.s3.eu-west-3.amazonaws.com/image/paque.jpg',NULL,4),(10,'promenade.jpg','https://testp12.s3.eu-west-3.amazonaws.com/image/promenade.jpg',NULL,4);
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (22);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `marker`
--

LOCK TABLES `marker` WRITE;
/*!40000 ALTER TABLE `marker` DISABLE KEYS */;
INSERT INTO `marker` VALUES (6,NULL,41.8717,8.96415,3,NULL),(7,NULL,41.9074,8.82201,3,NULL),(8,NULL,41.8527,9.02045,3,NULL),(10,NULL,42.226,8.6476,NULL,3),(11,NULL,42.1304,8.59816,NULL,3),(12,NULL,41.7299,8.96071,4,NULL),(14,NULL,42.0244,8.98818,NULL,4),(15,NULL,41.91,8.73,NULL,4),(16,NULL,41.9345,8.94973,NULL,4),(17,NULL,42.0407,8.70803,NULL,4);
/*!40000 ALTER TABLE `marker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `publication`
--

LOCK TABLES `publication` WRITE;
/*!40000 ALTER TABLE `publication` DISABLE KEYS */;
INSERT INTO `publication` VALUES (2,'Premiere Publication du site Corsebook!!!',1,'2022-05-01 14:26:53',6),(3,'photo de mon dernier passage a Porto ',0,'2022-05-01 14:54:36',2),(4,'Image Souvenir du Catenacciu, et la traversé des villages ',1,'2022-05-01 15:01:07',1);
/*!40000 ALTER TABLE `publication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `publication_like_user`
--

LOCK TABLES `publication_like_user` WRITE;
/*!40000 ALTER TABLE `publication_like_user` DISABLE KEYS */;
INSERT INTO `publication_like_user` VALUES (4,2),(2,2);
/*!40000 ALTER TABLE `publication_like_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'angela123@gmail.com','$2a$10$jHA0MD5.bbqmMVHFiTa.re8Obu61sGZguAlCuBN2P4yeSP0JmrAwK','$2a$10$jHA0MD5.bbqmMVHFiTa.re8Obu61sGZguAlCuBN2P4yeSP0JmrAwK','angela123',NULL),(2,'sebastien974@gmail.com','$2a$10$IdD2Z/UhnePuGXA4h/BnbukfcLKT4lyXQgN55xVHTwVGQHGqQUbFG','$2a$10$IdD2Z/UhnePuGXA4h/BnbukfcLKT4lyXQgN55xVHTwVGQHGqQUbFG','sebastien974',NULL),(3,'alex2A@gmail.com','$2a$10$sNdLqdo9En7WfdYZAjVvlucDX/jAoNM1j.FNx0ZOdtaTiS3/VWYWC','$2a$10$sNdLqdo9En7WfdYZAjVvlucDX/jAoNM1j.FNx0ZOdtaTiS3/VWYWC','alex2A',NULL),(4,'tomy2B@gmail.com','$2a$10$8sx0py6EGMhFvwRW8GU9xu78DPEzVcmAnQoy3hQ8UurdTj1lQ5T.a','$2a$10$8sx0py6EGMhFvwRW8GU9xu78DPEzVcmAnQoy3hQ8UurdTj1lQ5T.a','tomy2B',NULL),(5,'laetitiaS2A@gmail.com','$2a$10$HcmJbtC/E734fZBDcpasvei5spwm4te72RxF3eX15tOBBub520PZ6','$2a$10$HcmJbtC/E734fZBDcpasvei5spwm4te72RxF3eX15tOBBub520PZ6','laetitiaS2A',NULL),(6,'AziloneA@gmail.com','$2a$10$y2M09jaVRFN.ihQJlsqRYu1Zms9i6FD.TDmcPf.7OpBKKM3qAf3N6','$2a$10$y2M09jaVRFN.ihQJlsqRYu1Zms9i6FD.TDmcPf.7OpBKKM3qAf3N6','Mairie Azilone',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_list_contact`
--

LOCK TABLES `user_list_contact` WRITE;
/*!40000 ALTER TABLE `user_list_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_list_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_list_event`
--

LOCK TABLES `user_list_event` WRITE;
/*!40000 ALTER TABLE `user_list_event` DISABLE KEYS */;
INSERT INTO `user_list_event` VALUES (6,3),(2,4);
/*!40000 ALTER TABLE `user_list_event` ENABLE KEYS */;
UNLOCK TABLES;

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

-- Dump completed on 2022-05-01 17:19:31
