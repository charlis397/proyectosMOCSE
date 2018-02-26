-- MySQL dump 10.13  Distrib 5.7.11, for Win64 (x86_64)
--
-- Host: localhost    Database: banco
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `ahorro`
--

DROP TABLE IF EXISTS `ahorro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ahorro` (
  `id_horro` int(11) NOT NULL,
  `cuenta_num_cuenta` int(11) NOT NULL,
  `cuenta_usuario_id` int(11) NOT NULL,
  `saldo` int(11) NOT NULL,
  PRIMARY KEY (`id_horro`,`cuenta_num_cuenta`,`cuenta_usuario_id`),
  KEY `fk_ahorro_cuenta1_idx` (`cuenta_num_cuenta`,`cuenta_usuario_id`),
  CONSTRAINT `fk_ahorro_cuenta1` FOREIGN KEY (`cuenta_num_cuenta`, `cuenta_usuario_id`) REFERENCES `cuenta` (`num_cuenta`, `usuario_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ahorro`
--

LOCK TABLES `ahorro` WRITE;
/*!40000 ALTER TABLE `ahorro` DISABLE KEYS */;
/*!40000 ALTER TABLE `ahorro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credito`
--

DROP TABLE IF EXISTS `credito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credito` (
  `id_credito` int(11) NOT NULL,
  `cuenta_num_cuenta` int(11) NOT NULL,
  `cuenta_usuario_id` int(11) NOT NULL,
  `saldo_cred` int(11) NOT NULL,
  `anualidad` int(11) NOT NULL,
  `interes` int(11) NOT NULL,
  `fecha_corte` int(11) NOT NULL,
  PRIMARY KEY (`id_credito`,`cuenta_num_cuenta`,`cuenta_usuario_id`),
  KEY `fk_credito_cuenta1_idx` (`cuenta_num_cuenta`,`cuenta_usuario_id`),
  CONSTRAINT `fk_credito_cuenta1` FOREIGN KEY (`cuenta_num_cuenta`, `cuenta_usuario_id`) REFERENCES `cuenta` (`num_cuenta`, `usuario_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credito`
--

LOCK TABLES `credito` WRITE;
/*!40000 ALTER TABLE `credito` DISABLE KEYS */;
/*!40000 ALTER TABLE `credito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuenta` (
  `num_cuenta` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `NIP` int(11) NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  PRIMARY KEY (`num_cuenta`,`usuario_id`),
  UNIQUE KEY `NIP_UNIQUE` (`NIP`),
  KEY `fk_cuenta_usuario_idx` (`usuario_id`),
  CONSTRAINT `fk_cuenta_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta`
--

LOCK TABLES `cuenta` WRITE;
/*!40000 ALTER TABLE `cuenta` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos`
--

DROP TABLE IF EXISTS `pagos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagos` (
  `cuenta_num_cuenta` int(11) NOT NULL,
  `cuenta_usuario_id` int(11) NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `monto` int(11) DEFAULT NULL,
  `concepto` varchar(255) DEFAULT NULL,
  `estatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`cuenta_num_cuenta`,`cuenta_usuario_id`),
  CONSTRAINT `fk_table1_cuenta1` FOREIGN KEY (`cuenta_num_cuenta`, `cuenta_usuario_id`) REFERENCES `cuenta` (`num_cuenta`, `usuario_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos`
--

LOCK TABLES `pagos` WRITE;
/*!40000 ALTER TABLE `pagos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transferencias`
--

DROP TABLE IF EXISTS `transferencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transferencias` (
  `ahorro_id_horro` int(11) NOT NULL,
  `ahorro_cuenta_num_cuenta` int(11) NOT NULL,
  `ahorro_cuenta_usuario_id` int(11) NOT NULL,
  `cuenta_num_cuenta` int(11) NOT NULL,
  `cuenta_usuario_id` int(11) NOT NULL,
  `monto` int(11) NOT NULL,
  `fecha_hora_tr` datetime NOT NULL,
  `concepto` varchar(255) NOT NULL,
  `estatus` int(11) NOT NULL,
  PRIMARY KEY (`ahorro_id_horro`,`ahorro_cuenta_num_cuenta`,`ahorro_cuenta_usuario_id`,`cuenta_num_cuenta`,`cuenta_usuario_id`),
  KEY `fk_transferencias_ahorro1_idx` (`ahorro_id_horro`,`ahorro_cuenta_num_cuenta`,`ahorro_cuenta_usuario_id`),
  KEY `fk_transferencias_cuenta1` (`cuenta_num_cuenta`,`cuenta_usuario_id`),
  CONSTRAINT `fk_transferencias_ahorro1` FOREIGN KEY (`ahorro_id_horro`, `ahorro_cuenta_num_cuenta`, `ahorro_cuenta_usuario_id`) REFERENCES `ahorro` (`id_horro`, `cuenta_num_cuenta`, `cuenta_usuario_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_transferencias_cuenta1` FOREIGN KEY (`cuenta_num_cuenta`, `cuenta_usuario_id`) REFERENCES `cuenta` (`num_cuenta`, `usuario_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transferencias`
--

LOCK TABLES `transferencias` WRITE;
/*!40000 ALTER TABLE `transferencias` DISABLE KEYS */;
/*!40000 ALTER TABLE `transferencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido_paterno` varchar(45) NOT NULL,
  `apellido_materno` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `email` varchar(45) NOT NULL,
  `sucursal` int(11) NOT NULL,
  `contrasena` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `contrasena_UNIQUE` (`contrasena`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Carlos Eduardo','Caballero','Huesca','Dr.Balmis #115 int 22','55180647','carlos@hotmail.com',1,'PMH...ce'),(2,'Carla Maria','Garcia','Martinez','Tochapa 2da Cerrada','5591392444','carla@hotmail.com',1,'Morado'),(3,'Brando','Martinez','Garcia','Metro Politecnico','55180354','brando@gmail.com',1,'123456789'),(4,'','Apellido Paterno','Apellido Materno','Dirección','Telefono','Correo Electronico',1,'Contraseña'),(6,'Nombre(s)','Apellido Paterno','Apellido Materno','    ','Telefono','',1,''),(13,'   ','  ','                                  ','                                             ','','@',1,'5');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-29  0:15:58
