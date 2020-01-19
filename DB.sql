-- MySQL dump 10.13  Distrib 5.5.30, for Win32 (x86)
--
-- Host: localhost    Database: schoolsm
-- ------------------------------------------------------
-- Server version	5.5.30

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
-- Table structure for table `t_bulletins`
--

DROP TABLE IF EXISTS `t_bulletins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_bulletins` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `idEleve` int(4) NOT NULL,
  `nom` text,
  `prenom` text,
  `genre` text,
  `date` text,
  `decision` text,
  `moyenneGenerale` double DEFAULT NULL,
  `effectif` int(4) DEFAULT NULL,
  `rang` int(4) DEFAULT NULL,
  `classe` text,
  PRIMARY KEY (`id`),
  KEY `idEleve` (`idEleve`),
  CONSTRAINT `t_bulletins_ibfk_1` FOREIGN KEY (`idEleve`) REFERENCES `t_eleves` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_bulletins`
--

LOCK TABLES `t_bulletins` WRITE;
/*!40000 ALTER TABLE `t_bulletins` DISABLE KEYS */;
INSERT INTO `t_bulletins` VALUES (1,1,'KABORE','Eric','homme','2019-07-22','Semestre inValidé',4.2727272727272725,2,2,'master'),(2,2,'Ludovic','SEMASSA','Femme','2019-07-22','Semestre inValidé',6,2,2,'MasterII'),(3,3,'NANA','Boukari','homme','2019-07-22','Semestre inValidé',6.909090909090909,2,1,'master'),(4,4,'SANFO','Mohamed','homme','2019-07-22','Semestre inValidé',7,2,1,'MasterII');
/*!40000 ALTER TABLE `t_bulletins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_bulletins_notes`
--

DROP TABLE IF EXISTS `t_bulletins_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_bulletins_notes` (
  `Id` int(4) NOT NULL AUTO_INCREMENT,
  `IdEleve` int(4) NOT NULL,
  `IdBulletin` int(4) NOT NULL,
  `note` double DEFAULT NULL,
  `coef` int(4) DEFAULT NULL,
  `notePondere` double DEFAULT NULL,
  `libelle` text,
  PRIMARY KEY (`Id`),
  KEY `IdEleve` (`IdEleve`),
  KEY `IdBulletin` (`IdBulletin`),
  CONSTRAINT `t_bulletins_notes_ibfk_1` FOREIGN KEY (`IdEleve`) REFERENCES `t_eleves` (`Id`),
  CONSTRAINT `t_bulletins_notes_ibfk_2` FOREIGN KEY (`IdBulletin`) REFERENCES `t_bulletins` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_bulletins_notes`
--

LOCK TABLES `t_bulletins_notes` WRITE;
/*!40000 ALTER TABLE `t_bulletins_notes` DISABLE KEYS */;
INSERT INTO `t_bulletins_notes` VALUES (1,1,1,4,5,20,'Java'),(2,1,1,4.5,6,27,'SVT'),(4,2,2,7,6,42,'C++'),(5,2,2,4,3,12,'MATH'),(7,3,3,5,5,25,'Java'),(8,3,3,8.5,6,51,'SVT'),(10,4,4,6,6,36,'C++'),(11,4,4,9,3,27,'MATH');
/*!40000 ALTER TABLE `t_bulletins_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_candidatures`
--

DROP TABLE IF EXISTS `t_candidatures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_candidatures` (
  `Id` int(4) NOT NULL AUTO_INCREMENT,
  `idEns` int(4) DEFAULT NULL,
  `nom` text NOT NULL,
  `prenom` text NOT NULL,
  `email` text NOT NULL,
  `telephone` text NOT NULL,
  `genre` text NOT NULL,
  `path` text,
  `birthday` text NOT NULL,
  `cv` text,
  `state` text,
  `commentaires` text,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_candidatures`
--

LOCK TABLES `t_candidatures` WRITE;
/*!40000 ALTER TABLE `t_candidatures` DISABLE KEYS */;
INSERT INTO `t_candidatures` VALUES (1,3,'SAL','Abdoulaye','Homme','575755','Homme','C:\\Users\\Eric\\Documents\\b4c2407af2e2f0f2a986b179957647c9.jpg','44-55-1992','C:\\Users\\Eric\\Downloads\\CV_KABORE_Eric.pdf','Rejetée',NULL),(2,3,'Hamidou','Toure','Homme','8686868','Homme',NULL,'77-55-2016',NULL,'Retenue',NULL);
/*!40000 ALTER TABLE `t_candidatures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_classes`
--

DROP TABLE IF EXISTS `t_classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_classes` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `libelle` text,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_classes`
--

LOCK TABLES `t_classes` WRITE;
/*!40000 ALTER TABLE `t_classes` DISABLE KEYS */;
INSERT INTO `t_classes` VALUES (1,'master',''),(2,'MasterII','');
/*!40000 ALTER TABLE `t_classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_cours`
--

DROP TABLE IF EXISTS `t_cours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cours` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `IdModule` int(4) NOT NULL,
  `idClasse` int(4) DEFAULT NULL,
  `description` text,
  `dateHeure` text,
  `duree` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_cours`
--

LOCK TABLES `t_cours` WRITE;
/*!40000 ALTER TABLE `t_cours` DISABLE KEYS */;
INSERT INTO `t_cours` VALUES (23,2,2,'czzz','Mon Jul 15 14:41:58 WAT 2019',60),(24,0,0,NULL,NULL,0),(25,0,0,NULL,NULL,0),(26,3,2,'nnnn','Mon Jul 15 14:42:08 WAT 2019',60),(27,0,0,NULL,NULL,0),(28,0,0,NULL,NULL,0),(29,0,0,NULL,NULL,0),(30,1,1,'aaaaa','Mon Jul 15 14:41:43 WAT 2019',60),(31,1,1,'eeee','Mon Jul 15 14:42:29 WAT 2019',60),(32,0,0,NULL,NULL,0),(33,0,0,NULL,NULL,0),(34,0,0,NULL,NULL,0);
/*!40000 ALTER TABLE `t_cours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_devoirs`
--

DROP TABLE IF EXISTS `t_devoirs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_devoirs` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `IdModule` int(4) NOT NULL,
  `idClasse` int(4) DEFAULT NULL,
  `statut` text,
  `description` text,
  `dateHeure` text,
  `duree` int(4) DEFAULT NULL,
  `coef` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_devoirs`
--

LOCK TABLES `t_devoirs` WRITE;
/*!40000 ALTER TABLE `t_devoirs` DISABLE KEYS */;
INSERT INTO `t_devoirs` VALUES (2,3,1,'pas encore effectué','zzzeee','Sat Jul 20 02:02:58 WAT 2019',60,6),(3,1,1,'pas encore effectue','zz','Mon Jul 15 14:40:27 WAT 2019',60,NULL),(4,4,2,'pas encore effectue','1111','Mon Jul 15 14:40:33 WAT 2019',60,NULL),(5,2,2,'pas encore effectué','fdfd','Sat Jul 20 02:02:51 WAT 2019',60,6),(6,4,2,'pas encore effectué','ere','Sat Jul 20 02:02:45 WAT 2019',60,3),(7,1,1,'pas encore effectue','azaz','Tue Jul 16 21:46:05 WAT 2019',60,5),(8,3,1,'pas encore effectue','deuxieme dev','Mon Jul 22 08:43:39 WAT 2019',60,6);
/*!40000 ALTER TABLE `t_devoirs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_diplomes`
--

DROP TABLE IF EXISTS `t_diplomes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_diplomes` (
  `Id` int(4) NOT NULL AUTO_INCREMENT,
  `IdCandidature` int(4) DEFAULT NULL,
  `url` text NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `IdCandidature_idx` (`IdCandidature`),
  CONSTRAINT `IdCandidature` FOREIGN KEY (`IdCandidature`) REFERENCES `t_candidatures` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_diplomes`
--

LOCK TABLES `t_diplomes` WRITE;
/*!40000 ALTER TABLE `t_diplomes` DISABLE KEYS */;
INSERT INTO `t_diplomes` VALUES (1,1,'C:\\Users\\Eric\\Documents\\Certificate Of Completion - FRE.pdf'),(2,1,'C:\\Users\\Eric\\Desktop\\examenDBA.pdf');
/*!40000 ALTER TABLE `t_diplomes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_eleves`
--

DROP TABLE IF EXISTS `t_eleves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_eleves` (
  `Id` int(4) NOT NULL AUTO_INCREMENT,
  `idClasse` int(4) DEFAULT NULL,
  `nom` text NOT NULL,
  `prenom` text NOT NULL,
  `genre` text NOT NULL,
  `telephone` text NOT NULL,
  `login` text NOT NULL,
  `password` text NOT NULL,
  `email` text,
  `path` text,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_eleves`
--

LOCK TABLES `t_eleves` WRITE;
/*!40000 ALTER TABLE `t_eleves` DISABLE KEYS */;
INSERT INTO `t_eleves` VALUES (1,1,'KABORE','Eric','homme','77777777','eric','passer','emain@email.com',NULL),(2,2,'Ludovic','SEMASSA','Femme','77 868 665','ludo','ludo','ludovic@email.com',NULL),(3,1,'NANA','Boukari','homme','454424','boukary','passer','boukary@mail.com',NULL),(4,2,'SANFO','Mohamed','homme','454424','momo','passer','boukary@mail.com',NULL);
/*!40000 ALTER TABLE `t_eleves` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_enseignants`
--

DROP TABLE IF EXISTS `t_enseignants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_enseignants` (
  `Id` int(4) NOT NULL AUTO_INCREMENT,
  `nom` text NOT NULL,
  `prenom` text NOT NULL,
  `genre` text NOT NULL,
  `path` text,
  `telephone` text NOT NULL,
  `login` text NOT NULL,
  `password` text NOT NULL,
  `email` text NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_enseignants`
--

LOCK TABLES `t_enseignants` WRITE;
/*!40000 ALTER TABLE `t_enseignants` DISABLE KEYS */;
INSERT INTO `t_enseignants` VALUES (3,'Cisse','Aîcha','Femme',NULL,'77666565','aicha','passer','aicha@gmail.com'),(4,'SENE','adama','Femme',NULL,'3561516','adama','passer','adama@gmail.com');
/*!40000 ALTER TABLE `t_enseignants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_enseignants_classes`
--

DROP TABLE IF EXISTS `t_enseignants_classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_enseignants_classes` (
  `Id` int(4) NOT NULL AUTO_INCREMENT,
  `IdEnseignant` int(4) NOT NULL,
  `IdClasse` int(4) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_enseignants_classes`
--

LOCK TABLES `t_enseignants_classes` WRITE;
/*!40000 ALTER TABLE `t_enseignants_classes` DISABLE KEYS */;
INSERT INTO `t_enseignants_classes` VALUES (21,1,2),(22,1,1),(23,2,1),(24,2,2),(26,4,2),(27,3,1),(28,3,2);
/*!40000 ALTER TABLE `t_enseignants_classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_enseignants_classes_modules`
--

DROP TABLE IF EXISTS `t_enseignants_classes_modules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_enseignants_classes_modules` (
  `Id` int(4) NOT NULL AUTO_INCREMENT,
  `IdEns` int(4) DEFAULT NULL,
  `IdClasse` int(4) DEFAULT NULL,
  `IdModule` int(4) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UNICITE` (`IdClasse`,`IdModule`),
  KEY `IdEns` (`IdEns`),
  KEY `IdModule` (`IdModule`),
  CONSTRAINT `t_enseignants_classes_modules_ibfk_1` FOREIGN KEY (`IdEns`) REFERENCES `t_enseignants` (`Id`),
  CONSTRAINT `t_enseignants_classes_modules_ibfk_2` FOREIGN KEY (`IdClasse`) REFERENCES `t_classes` (`id`),
  CONSTRAINT `t_enseignants_classes_modules_ibfk_3` FOREIGN KEY (`IdModule`) REFERENCES `t_modules` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_enseignants_classes_modules`
--

LOCK TABLES `t_enseignants_classes_modules` WRITE;
/*!40000 ALTER TABLE `t_enseignants_classes_modules` DISABLE KEYS */;
INSERT INTO `t_enseignants_classes_modules` VALUES (1,3,1,1),(2,4,2,1);
/*!40000 ALTER TABLE `t_enseignants_classes_modules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_enseignants_cours`
--

DROP TABLE IF EXISTS `t_enseignants_cours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_enseignants_cours` (
  `Id` int(4) NOT NULL AUTO_INCREMENT,
  `IdEnseignant` int(4) NOT NULL,
  `IdCours` int(4) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_enseignants_cours`
--

LOCK TABLES `t_enseignants_cours` WRITE;
/*!40000 ALTER TABLE `t_enseignants_cours` DISABLE KEYS */;
INSERT INTO `t_enseignants_cours` VALUES (71,3,50),(72,3,51),(73,3,52),(74,3,53),(75,3,54),(76,3,55),(77,3,56),(78,3,57),(79,3,58),(80,3,59),(81,3,60),(82,3,61),(83,3,62),(84,3,-1),(85,3,-1),(86,3,-1),(87,3,-1),(88,3,-1),(89,3,-1),(90,3,69),(91,3,-1),(92,3,71),(93,3,-1),(94,3,73),(95,3,74),(96,3,-1),(97,3,-1),(98,3,77),(99,3,78),(100,3,79),(101,3,23),(102,3,0),(103,3,0),(104,3,26),(105,4,0),(106,4,0),(107,3,0),(108,3,30),(109,3,31),(110,4,0),(111,4,0),(112,4,0);
/*!40000 ALTER TABLE `t_enseignants_cours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_enseignants_devoirs`
--

DROP TABLE IF EXISTS `t_enseignants_devoirs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_enseignants_devoirs` (
  `Id` int(4) NOT NULL AUTO_INCREMENT,
  `IdEnseignant` int(4) NOT NULL,
  `IdDevoirs` int(4) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_enseignants_devoirs`
--

LOCK TABLES `t_enseignants_devoirs` WRITE;
/*!40000 ALTER TABLE `t_enseignants_devoirs` DISABLE KEYS */;
INSERT INTO `t_enseignants_devoirs` VALUES (53,3,23),(54,3,24),(55,3,25),(56,3,1),(57,3,2),(58,4,3),(59,4,4),(60,3,5),(61,3,6),(62,3,7),(63,3,8);
/*!40000 ALTER TABLE `t_enseignants_devoirs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_gestionnaires`
--

DROP TABLE IF EXISTS `t_gestionnaires`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_gestionnaires` (
  `Id` int(4) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `adresse` varchar(45) DEFAULT NULL,
  `date_naissance` varchar(45) NOT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_gestionnaires`
--

LOCK TABLES `t_gestionnaires` WRITE;
/*!40000 ALTER TABLE `t_gestionnaires` DISABLE KEYS */;
INSERT INTO `t_gestionnaires` VALUES (29,'Hane','Abdoul Aziz','Sacre-Coeur3','','781696498','','abdoul','passer'),(30,'HANE','MariŠme Aicha','Sacr‚-Coeur 3','19/03/2008','773475267','hanemariemeaicha@gmail.com','marieme','mrichou'),(31,'dsdsd','sdsdsd','sdsd','sdsd','sdsds','dsdsd','sdsds','');
/*!40000 ALTER TABLE `t_gestionnaires` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_modules`
--

DROP TABLE IF EXISTS `t_modules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_modules` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `coefficient` int(4) DEFAULT NULL,
  `libelle` text,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_modules`
--

LOCK TABLES `t_modules` WRITE;
/*!40000 ALTER TABLE `t_modules` DISABLE KEYS */;
INSERT INTO `t_modules` VALUES (1,5,'Java','Programmation oriente objet'),(2,6,'C++',''),(3,6,'SVT',''),(4,3,'MATH','');
/*!40000 ALTER TABLE `t_modules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_note`
--

DROP TABLE IF EXISTS `t_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_note` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `idDevoirs` int(4) NOT NULL,
  `note` double DEFAULT NULL,
  `coef` int(4) DEFAULT NULL,
  `idEleve` int(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idDevoirs` (`idDevoirs`),
  KEY `idEleve` (`idEleve`),
  CONSTRAINT `t_note_ibfk_1` FOREIGN KEY (`idDevoirs`) REFERENCES `t_devoirs` (`id`),
  CONSTRAINT `t_note_ibfk_2` FOREIGN KEY (`idEleve`) REFERENCES `t_eleves` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_note`
--

LOCK TABLES `t_note` WRITE;
/*!40000 ALTER TABLE `t_note` DISABLE KEYS */;
INSERT INTO `t_note` VALUES (11,5,7,6,2),(12,5,6,6,4),(13,6,4,3,2),(14,6,9,3,4),(15,7,4,5,1),(16,7,5,5,3),(17,2,4,6,1),(18,2,9,6,3),(19,8,5,6,1),(20,8,8,6,3);
/*!40000 ALTER TABLE `t_note` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-23 21:34:05
