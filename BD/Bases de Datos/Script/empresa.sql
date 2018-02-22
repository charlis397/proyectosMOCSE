-- MySQL dump 10.13  Distrib 5.1.31, for Win32 (ia32)
--
-- Host: localhost    Database: empresa
-- ------------------------------------------------------
-- Server version	5.1.31-community

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
-- Table structure for table `act`
--

DROP TABLE IF EXISTS `act`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `act` (
  `ACTNO` int(11) NOT NULL,
  `ACTKWD` varchar(45) NOT NULL,
  `ACTDESC` varchar(45) NOT NULL,
  PRIMARY KEY (`ACTNO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `act`
--

LOCK TABLES `act` WRITE;
/*!40000 ALTER TABLE `act` DISABLE KEYS */;
INSERT INTO `act` VALUES (10,'MANAGE','MANAGE/ADVISE'),(20,'ECOST','ESTIMATE COST'),(30,'DEFINE','DEFINE SPECS'),(40,'LEADPR','LEAD PROGRAM/DESIGN'),(50,'SPECS','WRITE SPECS'),(60,'LOGIC','DESCRIBE LOGIC'),(70,'CODE','CODE PROGRAMS'),(80,'TEST','TEST PROGRAMS'),(90,'ADMQS','ADM QUERY SYSTEM'),(100,'TEACH','TEACH CLASSES'),(110,'COURSE','DEVELOP COURSES'),(120,'STAFF','PERS AND STAFFING'),(130,'OPERAT','OPER COMPUTER SYS'),(140,'MAINT','MAINT SOFTWARE SYS'),(150,'ADMSYS','ADM OPERATING SYS'),(160,'ADMDB','ADM DATA BASES'),(170,'ADMDC','ADM DATA COMM'),(180,'DOC','DOCUMENT');
/*!40000 ALTER TABLE `act` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `department` (
  `DEPTNO` varchar(3) NOT NULL,
  `DEPTNAME` varchar(36) NOT NULL,
  `MGRNO` varchar(6) DEFAULT NULL,
  `ADMRDEPT` varchar(3) NOT NULL,
  `LOCATION` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`DEPTNO`),
  KEY `FK_department_1` (`MGRNO`),
  CONSTRAINT `FK_department_1` FOREIGN KEY (`MGRNO`) REFERENCES `employee` (`EMPNO`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('A00','SPIFFY COMPUTER SERVICE DIV.','000010','A00',NULL),('B01','PLANNING','000020','A00',NULL),('C01','INFORMATION CENTER','000030','A00',NULL),('D01','DEVELOPMENT CENTER',NULL,'A00',NULL),('D11','MANUFACTURING SYSTEMS','000060','D01',NULL),('D21','ADMINISTRATION SYSTEMS','000070','D01',NULL),('E01','SUPPORT SERVICES','000050','A00',NULL),('E11','OPERATIONS','000090','E01',NULL),('E21','SOFTWARE SUPPORT','000100','E01',NULL),('F22','BRANCH OFFICE F2',NULL,'E01',NULL),('G22','BRANCH OFFICE G2',NULL,'E01',NULL),('H22','BRANCH OFFICE H2',NULL,'E01',NULL),('I22','BRANCH OFFICE I2',NULL,'E01',NULL),('J22','BRANCH OFFICE J2',NULL,'E01',NULL);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `employee` (
  `EMPNO` varchar(6) NOT NULL,
  `FIRSTNME` varchar(12) NOT NULL,
  `MIDINIT` varchar(1) DEFAULT NULL,
  `LASTNAME` varchar(15) NOT NULL,
  `WORKDEPT` varchar(3) DEFAULT NULL,
  `PHONENO` varchar(4) DEFAULT NULL,
  `HIREDATE` date DEFAULT NULL,
  `JOB` varchar(8) DEFAULT NULL,
  `EDLEVEL` int(10) unsigned NOT NULL,
  `SEX` varchar(1) DEFAULT NULL,
  `BIRTHDATE` date DEFAULT NULL,
  `SALARY` double DEFAULT NULL,
  `BONUS` double DEFAULT NULL,
  `COMM` double DEFAULT NULL,
  PRIMARY KEY (`EMPNO`),
  KEY `FK_employee_1` (`WORKDEPT`),
  CONSTRAINT `FK_employee_1` FOREIGN KEY (`WORKDEPT`) REFERENCES `department` (`DEPTNO`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('000010','CHRISTINE','I','HAAS','A00','3978','1995-01-01','PRES',18,'F','1963-08-24',152750,1000,4220),('000020','MICHAEL','L','THOMPSON','B01','3476','2003-10-10','MANAGER',18,'M','1978-02-02',94250,800,3300),('000030','SALLY','A','KWAN','C01','4738','2005-04-05','MANAGER',20,'F','1971-05-11',98250,800,3060),('000050','JOHN','B','GEYER','E01','6789','1979-08-17','MANAGER',16,'M','1955-09-15',80175,800,3214),('000060','IRVING','F','STERN','D11','6423','2003-09-14','MANAGER',16,'M','1975-07-07',72250,500,2580),('000070','EVA','D','PULASKI','D21','7831','2005-09-30','MANAGER',16,'F','2003-05-26',96170,700,2893),('000090','EILEEN','W','HENDERSON','E11','5498','2000-08-15','MANAGER',16,'F','1971-05-15',89750,600,2380),('000100','THEODORE','Q','SPENSER','E21','0972','2000-06-19','MANAGER',14,'M','1980-12-18',86150,500,2092),('000110','VINCENZO','G','LUCCHESSI','A00','3490','1988-05-16','SALESREP',19,'M','1959-11-05',66500,900,3720),('000120','SEAN',NULL,'O CONNELL','A00','2167','1993-12-05','CLERK',14,'M','1972-10-18',49250,600,2340),('000130','DELORES','M','QUINTANA','C01','4578','2001-07-28','ANALYST',16,'F','1955-09-15',73800,500,1904),('000140','HEATHER','A','NICHOLLS','C01','1793','2006-12-15','ANALYST',18,'F','1976-01-19',68420,600,2274),('000150','BRUCE',NULL,'ADAMSON','D11','4510','2002-02-12','DESIGNER',16,'M','1977-05-17',55280,500,2022),('000160','ELIZABETH','R','PIANKA','D11','3782','2006-10-11','DESIGNER',17,'F','1980-04-12',62250,400,1780),('000170','MASATOSHI','J','YOSHIMURA','D11','2890','1999-09-15','DESIGNER',16,'M','1981-01-05',44680,500,1974),('000180','MARILYN','S','SCOUTTEN','D11','1682','2003-07-07','DESIGNER',17,'F','1979-02-21',51340,500,1707),('000190','JAMES','H','WALKER','D11','2986','2004-07-26','DESIGNER',16,'M','1982-06-25',50450,400,1636),('000200','DAVID',NULL,'BROWN','D11','4501','2002-03-03','DESIGNER',16,'M','1971-05-29',57740,600,2217),('000210','WILLIAM','T','JONES','D11','0942','1998-04-11','DESIGNER',17,'M','2003-02-23',68270,400,1462),('000220','JENNIFER','K','LUTZ','D11','0672','1998-08-29','DESIGNER',18,'F','1978-03-19',49840,600,2387),('000230','JAMES','J','JEFFERSON','D21','2094','1996-11-21','CLERK',14,'M','1980-05-30',42180,400,1774),('000240','SALVATORE','M','MARINO','D21','3780','2004-12-05','CLERK',17,'M','2002-03-31',48760,600,2301),('000250','DANIEL','S','SMITH','D21','0961','1999-10-30','CLERK',15,'M','1969-11-12',49180,400,1534),('000260','SYBIL','P','JOHNSON','D21','8953','2005-09-11','CLERK',16,'F','1976-10-05',47250,300,1380),('000270','MARIA','L','PEREZ','D21','9001','2006-09-30','CLERK',15,'F','2003-05-26',37380,500,2190),('000280','ETHEL','R','SCHNEIDER','E11','8997','1997-03-24','OPERATOR',17,'F','1976-03-28',36250,500,2100),('000290','JOHN','R','PARKER','E11','4502','2006-05-30','OPERATOR',12,'M','1985-07-09',35340,300,1227),('000300','PHILIP','X','SMITH','E11','2095','2002-06-19','OPERATOR',14,'M','1976-10-27',37750,400,1420),('000310','MAUDE','F','SETRIGHT','E11','3332','1994-09-12','OPERATOR',12,'F','1961-04-21',35900,300,1272),('000320','RAMLAL','V','MEHTA','E21','9990','1995-07-07','FIELDREP',16,'M','1962-08-11',39950,400,1596),('000330','WING',NULL,'LEE','E21','2103','2006-02-23','FIELDREP',14,'M','1971-07-18',45370,500,2030),('000340','JASON','R','GOUNOT','E21','5698','1977-05-05','FIELDREP',16,'M','1956-05-17',43840,500,1907),('200010','DIAN','J','HEMMINGER','A00','3978','1995-01-01','SALESREP',18,'F','1973-08-14',46500,1000,4220),('200120','GREG',NULL,'ORLANDO','A00','2167','2002-05-05','CLERK',14,'M','1972-10-18',39250,600,2340),('200140','KIM','N','NATZ','C01','1793','2006-12-15','ANALYST',18,'F','1976-01-19',68420,600,2274),('200170','KIYOSHI',NULL,'YAMAMOTO','D11','2890','2005-09-15','DESIGNER',16,'M','1981-01-05',64680,500,1974),('200220','REBA','K','JOHN','D11','0672','2005-08-29','DESIGNER',18,'F','1978-03-19',69840,600,2387),('200240','ROBERT','M','MONTEVERDE','D21','3780','2004-12-05','CLERK',17,'M','1984-03-31',37760,600,2301),('200280','EILEEN','R','SCHWARTZ','E11','8997','1997-03-24','OPERATOR',17,'F','1966-03-28',46250,500,2100),('200310','MICHELLE','F','SPRINGER','E11','3332','1994-09-12','OPERATOR',12,'F','1961-04-21',35900,300,1272),('200330','HELENA',NULL,'WONG','E21','2103','2006-02-23','FIELDREP',14,'F','1971-07-18',35370,500,2030),('200340','ROY','R','ALONZO','E21','5698','1997-07-05','FIELDREP',16,'M','1956-05-17',31840,500,1907);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empprojact`
--

DROP TABLE IF EXISTS `empprojact`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `empprojact` (
  `EMPNO` varchar(6) NOT NULL,
  `PROJNO` varchar(6) NOT NULL,
  `ACTNO` int(11) NOT NULL,
  `EMPTIME` double DEFAULT NULL,
  `EMSTDATE` date DEFAULT NULL,
  `EMENDATE` date DEFAULT NULL,
  KEY `FK_empprojact_1` (`ACTNO`),
  KEY `FK_empprojact_2` (`EMPNO`),
  KEY `FK_empprojact_3` (`PROJNO`),
  CONSTRAINT `FK_empprojact_1` FOREIGN KEY (`ACTNO`) REFERENCES `act` (`ACTNO`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_empprojact_2` FOREIGN KEY (`EMPNO`) REFERENCES `employee` (`EMPNO`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_empprojact_3` FOREIGN KEY (`PROJNO`) REFERENCES `project` (`PROJNO`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `empprojact`
--

LOCK TABLES `empprojact` WRITE;
/*!40000 ALTER TABLE `empprojact` DISABLE KEYS */;
INSERT INTO `empprojact` VALUES ('000010','AD3100',10,0.5,'2002-01-01','2002-07-01'),('000070','AD3110',10,1,'2002-01-01','2003-02-01'),('000230','AD3111',60,1,'2002-01-01','2002-03-15'),('000230','AD3111',60,0.5,'2002-03-15','2002-04-15'),('000230','AD3111',70,0.5,'2002-03-15','2002-10-15'),('000230','AD3111',80,0.5,'2002-04-15','2002-10-15'),('000230','AD3111',180,0.5,'2002-10-15','2003-01-01'),('000240','AD3111',70,1,'2002-02-15','2002-09-15'),('000240','AD3111',80,1,'2002-09-15','2003-01-01'),('000250','AD3112',60,1,'2002-01-01','2002-02-01'),('000250','AD3112',60,0.5,'2002-02-01','2002-03-15'),('000250','AD3112',60,1,'2003-01-01','2003-02-01'),('000250','AD3112',70,0.5,'2002-02-01','2002-03-15'),('000250','AD3112',70,1,'2002-03-15','2002-08-15'),('000250','AD3112',70,0.25,'2002-08-15','2002-10-15'),('000250','AD3112',80,0.25,'2002-08-15','2002-10-15'),('000250','AD3112',80,0.5,'2002-10-15','2002-12-01'),('000250','AD3112',180,0.5,'2002-08-15','2003-01-01'),('000260','AD3113',70,0.5,'2002-06-15','2002-07-01'),('000260','AD3113',70,1,'2002-07-01','2003-02-01'),('000260','AD3113',80,1,'2002-01-01','2002-03-01'),('000260','AD3113',80,0.5,'2002-03-01','2002-04-15'),('000260','AD3113',180,0.5,'2002-03-01','2002-04-15'),('000260','AD3113',180,1,'2002-04-15','2002-06-01'),('000260','AD3113',180,1,'2002-06-01','2002-07-01'),('000270','AD3113',60,0.5,'2002-03-01','2002-04-01'),('000270','AD3113',60,1,'2002-04-01','2002-09-01'),('000270','AD3113',60,0.25,'2002-09-01','2002-10-15'),('000270','AD3113',70,0.75,'2002-09-01','2002-10-15'),('000270','AD3113',70,1,'2002-10-15','2003-02-01'),('000270','AD3113',80,1,'2002-01-01','2002-03-01'),('000270','AD3113',80,0.5,'2002-03-01','2002-04-01'),('000030','IF1000',10,0.5,'2002-06-01','2003-01-01'),('000130','IF1000',90,1,'2002-10-01','2003-01-01'),('000130','IF1000',100,0.5,'2002-10-01','2003-01-01'),('000140','IF1000',90,0.5,'2002-10-01','2003-01-01'),('000030','IF2000',10,0.5,'2002-01-01','2003-01-01'),('000140','IF2000',100,1,'2002-01-01','2002-03-01'),('000140','IF2000',100,0.5,'2002-03-01','2002-07-01'),('000140','IF2000',110,0.5,'2002-03-01','2002-07-01'),('000140','IF2000',110,0.5,'2002-10-01','2003-01-01'),('000010','MA2100',10,0.5,'2002-01-01','2002-11-01'),('000110','MA2100',20,1,'2002-01-01','2003-03-01'),('000010','MA2110',10,1,'2002-01-01','2003-02-01'),('000200','MA2111',50,1,'2002-01-01','2002-06-15'),('000200','MA2111',60,1,'2002-06-15','2003-02-01'),('000220','MA2111',40,1,'2002-01-01','2003-02-01'),('000150','MA2112',60,1,'2002-01-01','2002-07-15'),('000150','MA2112',180,1,'2002-07-15','2003-02-01'),('000170','MA2112',60,1,'2002-01-01','2003-06-01'),('000170','MA2112',70,1,'2002-06-01','2003-02-01'),('000190','MA2112',70,1,'2002-01-01','2002-10-01'),('000190','MA2112',80,1,'2002-10-01','2003-10-01'),('000160','MA2113',60,1,'2002-07-15','2003-02-01'),('000170','MA2113',80,1,'2002-01-01','2003-02-01'),('000180','MA2113',70,1,'2002-04-01','2002-06-15'),('000210','MA2113',80,0.5,'2002-10-01','2003-02-01'),('000210','MA2113',180,0.5,'2002-10-01','2003-02-01'),('000050','OP1000',10,0.25,'2002-01-01','2003-02-01'),('000090','OP1010',10,1,'2002-01-01','2003-02-01'),('000280','OP1010',130,1,'2002-01-01','2003-02-01'),('000290','OP1010',130,1,'2002-01-01','2003-02-01'),('000300','OP1010',130,1,'2002-01-01','2003-02-01'),('000310','OP1010',130,1,'2002-01-01','2003-02-01'),('000050','OP2010',10,0.75,'2002-01-01','2003-02-01'),('000100','OP2010',10,1,'2002-01-01','2003-02-01'),('000320','OP2011',140,0.75,'2002-01-01','2003-02-01'),('000320','OP2011',150,0.25,'2002-01-01','2003-02-01'),('000330','OP2012',140,0.25,'2002-01-01','2003-02-01'),('000330','OP2012',160,0.75,'2002-01-01','2003-02-01'),('000340','OP2013',140,0.5,'2002-01-01','2003-02-01'),('000340','OP2013',170,0.5,'2002-01-01','2003-02-01'),('000020','PL2100',30,1,'2002-01-01','2002-09-15');
/*!40000 ALTER TABLE `empprojact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `project` (
  `PROJNO` varchar(6) NOT NULL,
  `PROJNAME` varchar(24) NOT NULL,
  `DEPTNO` varchar(3) NOT NULL,
  `RESPEMP` varchar(6) NOT NULL,
  `PRSTAFF` double DEFAULT NULL,
  `PRSTDATE` date DEFAULT NULL,
  `PRENDATE` date DEFAULT NULL,
  `MAJPROJ` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`PROJNO`),
  KEY `FK_project_1` (`DEPTNO`),
  KEY `FK_project_2` (`RESPEMP`),
  CONSTRAINT `FK_project_2` FOREIGN KEY (`RESPEMP`) REFERENCES `employee` (`EMPNO`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_project_1` FOREIGN KEY (`DEPTNO`) REFERENCES `department` (`DEPTNO`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES ('AD3100','ADMIN SERVICES','D01','000010',6.5,'2002-01-01','2003-02-01',NULL),('AD3110','GENERAL ADMIN SYSTEMS','D21','000070',6,'2002-01-01','2003-02-01','AD3100'),('AD3111','PAYROLL PROGRAMMING','D21','000230',2,'2002-01-01','2003-02-01','AD3110'),('AD3112','PERSONNEL PROGRAMMING','D21','000250',1,'2002-01-01','2003-02-01','AD3110'),('AD3113','ACCOUNT PROGRAMMING','D21','000270',2,'2002-01-01','2003-02-01','AD3110'),('IF1000','QUERY SERVICES','C01','000030',2,'2002-01-01','2003-02-01',NULL),('IF2000','USER EDUCATION','C01','000030',1,'2002-01-01','2003-02-01',NULL),('MA2100','WELD LINE AUTOMATION','D01','000010',12,'2002-01-01','2003-02-01',NULL),('MA2110','W L PROGRAMMING','D11','000060',9,'2002-01-01','2003-02-01','MA2100'),('MA2111','W L PROGRAM DESIGN','D11','000220',2,'2002-01-01','1982-12-01','MA2110'),('MA2112','W L ROBOT DESIGN','D11','000150',3,'2002-01-01','1982-12-01','MA2110'),('MA2113','W L PROD CONT PROGS','D11','000160',3,'2002-02-15','1982-12-01','MA2110'),('OP1000','OPERATION SUPPORT','E01','000050',6,'2002-01-01','2003-02-01',NULL),('OP1010','OPERATION','E11','000090',5,'2002-01-01','2003-02-01','OP1000'),('OP2000','GEN SYSTEMS SERVICES','E01','000050',5,'2002-01-01','2003-02-01',NULL),('OP2010','SYSTEMS SUPPORT','E21','000100',4,'2002-01-01','2003-02-01','OP2000'),('OP2011','SCP SYSTEMS SUPPORT','E21','000320',1,'2002-01-01','2003-02-01','OP2010'),('OP2012','APPLICATIONS SUPPORT','E21','000330',1,'2002-01-01','2003-02-01','OP2010'),('OP2013','DB/DC SUPPORT','E21','000340',1,'2002-01-01','2003-02-01','OP2010'),('PL2100','WELD LINE PLANNING','B01','000020',1,'2002-01-01','2002-09-15','MA2100');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `sales` (
  `SALES_DATE` date DEFAULT NULL,
  `SALES_PERSON` varchar(15) DEFAULT NULL,
  `REGION` varchar(15) DEFAULT NULL,
  `SALES` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES ('2005-12-31','LUCCHESSI','Ontario-South',1),('2005-12-31','LEE','Ontario-South',3),('2005-12-31','LEE','Quebec',1),('2005-12-31','LEE','Manitoba',2),('2005-12-31','GOUNOT','Quebec',1),('2006-03-29','LUCCHESSI','Ontario-South',3),('2006-03-29','LUCCHESSI','Quebec',1),('2006-03-29','LEE','Ontario-South',2),('1996-03-29','LEE','Ontario-North',2),('2006-03-29','LEE','Quebec',3),('2006-03-29','LEE','Manitoba',5),('2006-03-29','GOUNOT','Ontario-South',3),('2006-03-29','GOUNOT','Quebec',1),('2006-03-29','GOUNOT','Manitoba',7),('2006-03-30','LUCCHESSI','Ontario-South',1),('2006-03-30','LUCCHESSI','Quebec',2),('2006-03-30','LUCCHESSI','Manitoba',1),('2006-03-30','LEE','Ontario-South',7),('2006-03-30','LEE','Ontario-North',3),('2006-03-30','LEE','Quebec',7),('2006-03-30','LEE','Manitoba',4),('2006-03-30','GOUNOT','Ontario-South',2),('2006-03-30','GOUNOT','Quebec',18),('2006-03-31','GOUNOT','Manitoba',1),('2006-03-31','LUCCHESSI','Manitoba',1),('2006-03-31','LEE','Ontario-South',14),('2006-03-31','LEE','Ontario-North',3),('2006-03-31','LEE','Quebec',7),('2006-03-31','LEE','Manitoba',3),('2006-03-31','GOUNOT','Ontario-South',2),('2006-03-31','GOUNOT','Quebec',1),('2006-04-01','LUCCHESSI','Ontario-South',3),('2006-04-01','LUCCHESSI','Manitoba',1),('2006-04-01','LEE','Ontario-South',8),('2006-04-01','LEE','Ontario-North',NULL),('2006-04-01','LEE','Quebec',8),('2006-04-01','LEE','Manitoba',9),('2006-04-01','GOUNOT','Ontario-South',3),('2006-04-01','GOUNOT','Ontario-North',1),('2006-04-01','GOUNOT','Quebec',3),('2006-04-01','GOUNOT','Manitoba',7);
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `staff` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(9) DEFAULT NULL,
  `DEPT` int(10) unsigned DEFAULT NULL,
  `JOB` varchar(5) DEFAULT NULL,
  ` YEARS` int(11) DEFAULT NULL,
  `SALARY` double DEFAULT NULL,
  `COMM` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (10,'Sanders',20,'Mgr',7,98357.5,NULL),(20,'Pernal',20,'Sales',8,78171.25,612.45),(30,'Marenghi',38,'Mgr',5,77506.75,NULL),(40,'O Brien',38,'Sales',6,78006,846.55),(50,'Hanes',15,'Mgr',10,80659.8,NULL),(60,'Quigley',38,'Sales',NULL,66808.3,650.25),(70,'Rothman',15,'Sales',7,76502.83,1152),(80,'James',20,'Clerk',NULL,43504.6,128.2),(90,'Koonitz',42,'Sales',6,38001.75,1386.7),(100,'Plotz',42,'Mgr',7,78352.8,NULL),(110,'Ngan',15,'Clerk',5,42508.2,206.6),(120,'Naughton',38,'Clerk',NULL,42954.75,180),(130,'Yamaguchi',42,'Clerk',6,40505.9,75.6),(140,'Fraye',51,'Mgr',6,91150,NULL),(150,'Williams',51,'Sales',6,79456.5,637.65),(160,'Molinare',10,'Mgr',7,82959.2,NULL),(170,'Kermisch',15,'Clerk',4,42258.5,110.1),(180,'Abrahams',38,'Clerk',3,37009.75,236.5),(190,'Sneider',20,'Clerk',8,34252.75,126.5),(200,'Scoutten',42,'Clerk',NULL,41508.6,84.2),(210,'Lu',10,'Mgr',10,90010,NULL),(220,'Smith',51,'Sales',7,87654.5,992.8),(230,'Lundquist',51,'Clerk',3,83369.8,189.65),(240,'Daniels',10,'Mgr',5,79260.25,NULL),(250,'Wheeler',51,'Clerk',6,74460,513.3),(260,'Jones',10,'Mgr',12,81234,NULL),(270,'Lea',66,'Mgr',9,88555.5,NULL),(280,'Wilson',66,'Sales',9,78674.5,811.5),(290,'Quill',84,'Mgr',10,89818,NULL),(300,'Davis',84,'Sales',5,65454.5,806.1),(310,'Graham',66,'Sales',13,71000,200.3),(320,'Gonzales',66,'Sales',4,76858.2,844),(330,'Burke',66,'Clerk',1,49988,55.5),(340,'Edwards',84,'Sales',7,67844,1285),(350,'Gafney',84,'Clerk',5,43030.5,188);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-06-06 20:48:35
