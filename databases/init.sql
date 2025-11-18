/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19  Distrib 10.11.11-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: hydrosys
-- ------------------------------------------------------
-- Server version	10.11.11-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK35t4wyxqrevf09uwx9e9p6o75` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES
(1,'inflamables'),
(4,'sano'),
(2,'Toxico');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_carrito`
--

DROP TABLE IF EXISTS `detalle_carrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_carrito` (
  `cantidad_producto` int(11) NOT NULL,
  `precio_unitario` decimal(38,2) NOT NULL,
  `subtotal` decimal(38,2) NOT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_producto` bigint(20) NOT NULL,
  `id_usuario` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiyq7r8lkadeluhit9grgvtn2c` (`id_producto`),
  KEY `FKmohpytmy3ij2u04sjdv2m0kdm` (`id_usuario`),
  CONSTRAINT `FKiyq7r8lkadeluhit9grgvtn2c` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKmohpytmy3ij2u04sjdv2m0kdm` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_carrito`
--

LOCK TABLES `detalle_carrito` WRITE;
/*!40000 ALTER TABLE `detalle_carrito` DISABLE KEYS */;
INSERT INTO `detalle_carrito` VALUES
(100,10000.00,1000000.00,'2025-10-08 17:28:22.309166',3,1,1);
/*!40000 ALTER TABLE `detalle_carrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_pedido`
--

DROP TABLE IF EXISTS `detalle_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_pedido` (
  `cantidad_producto` int(11) NOT NULL,
  `precio_unitario` decimal(38,2) NOT NULL,
  `subtotal` decimal(38,2) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_pedido` bigint(20) NOT NULL,
  `id_producto` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7n9hdifr08joboojejveby1vr` (`id_pedido`),
  KEY `FKjfm9pk0w2eag8tx8lu6pbego6` (`id_producto`),
  CONSTRAINT `FK7n9hdifr08joboojejveby1vr` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id`),
  CONSTRAINT `FKjfm9pk0w2eag8tx8lu6pbego6` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_pedido`
--

LOCK TABLES `detalle_pedido` WRITE;
/*!40000 ALTER TABLE `detalle_pedido` DISABLE KEYS */;
INSERT INTO `detalle_pedido` VALUES
(100,10000.00,1000000.00,1,1,1),
(100,10000.00,1000000.00,2,1,1);
/*!40000 ALTER TABLE `detalle_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `direccion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_usuario` bigint(20) NOT NULL,
  `ciudad` varchar(255) NOT NULL,
  `departamento` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `pais` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj093ukytc4qt239ujg4a6n8tp` (`id_usuario`),
  CONSTRAINT `FKj093ukytc4qt239ujg4a6n8tp` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
INSERT INTO `direccion` VALUES
(1,1,'Bucaramanga','Santander','Calle 123 #45-67','Colombia');
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lote`
--

DROP TABLE IF EXISTS `lote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `lote` (
  `fecha_recibo` datetime(6) NOT NULL,
  `fecha_vencimiento` datetime(6) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `proveedor` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lote`
--

LOCK TABLES `lote` WRITE;
/*!40000 ALTER TABLE `lote` DISABLE KEYS */;
INSERT INTO `lote` VALUES
('2025-10-08 16:19:55.475275','2025-12-31 18:59:59.000000',1,'Proveedor ABC S.A.'),
('2025-10-08 16:23:17.798989','2025-12-31 18:59:59.000000',2,'Proveedor ABC S.A.'),
('2025-10-15 08:37:41.673664','2025-12-31 18:59:59.000000',3,'Proveedor ABC S.A.');
/*!40000 ALTER TABLE `lote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metodo_pago`
--

DROP TABLE IF EXISTS `metodo_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `metodo_pago` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre_pago` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodo_pago`
--

LOCK TABLES `metodo_pago` WRITE;
/*!40000 ALTER TABLE `metodo_pago` DISABLE KEYS */;
INSERT INTO `metodo_pago` VALUES
(1,'paypal'),
(2,'Efectivo');
/*!40000 ALTER TABLE `metodo_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `total_pedido` decimal(38,2) NOT NULL,
  `fecha_pedido` datetime(6) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_pago` bigint(20) NOT NULL,
  `id_usuario` bigint(20) NOT NULL,
  `direccion_envio` varchar(255) NOT NULL,
  `estado_pedido` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgg6866cwudpfh68xjgnmijy3g` (`id_pago`),
  KEY `FK9ggns8wgi350asplcie001cv4` (`id_usuario`),
  CONSTRAINT `FK9ggns8wgi350asplcie001cv4` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKgg6866cwudpfh68xjgnmijy3g` FOREIGN KEY (`id_pago`) REFERENCES `metodo_pago` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES
(1250.50,'2025-10-08 16:46:54.592091',1,1,1,'Calle 45 #12-34, Bucaramanga, Santander','PENDIENTE');
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `precio` decimal(38,2) NOT NULL,
  `stock` int(11) NOT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_categoria` bigint(20) NOT NULL,
  `id_lote` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9nyueixdsgbycfhf7allg8su` (`id_categoria`),
  KEY `FKq64sud5bf1k8itb5o20wvi18i` (`id_lote`),
  CONSTRAINT `FK9nyueixdsgbycfhf7allg8su` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`),
  CONSTRAINT `FKq64sud5bf1k8itb5o20wvi18i` FOREIGN KEY (`id_lote`) REFERENCES `lote` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES
(25.00,100,'2025-10-08 16:33:28.393568',1,1,1,'Descripción del producto','cilindrow','https://picsum.photos/300/200'),
(25.00,100,'2025-10-09 14:28:03.760603',2,1,1,'Descripción del producto','cilindro','htpp://hdasdkjasdkjaskjdasd'),
(225.00,2,'2025-10-09 15:40:49.657980',3,2,1,'producto','pimpina','htpp://hdasdkjasdkjaskjdasd'),
(25.00,100,'2025-10-14 18:13:56.201074',4,1,1,'Descripción del producto','cilindro',NULL),
(12.00,12,'2025-10-15 09:31:33.442378',5,1,1,'wsasd','Crema hidrata','https://picsum.photos/300/200');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK43kr6s7bts1wqfv43f7jd87kp` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES
(1,'ADMIN'),
(2,'CLIENTE');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rol_id` bigint(20) DEFAULT NULL,
  `apellido` varchar(255) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK2mlfr087gb1ce55f2j87o74t` (`correo`),
  KEY `FKshkwj12wg6vkm6iuwhvcfpct8` (`rol_id`),
  CONSTRAINT `FKshkwj12wg6vkm6iuwhvcfpct8` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES
(1,1,'Sistema','admin@miapp.com','adm','$2a$10$FCcbUh22wxX76RGgAbmi6.ubr77q81pNd8VaRcPkTic.QbfKhPZJ.',NULL),
(2,1,'pepe','rich3ar@d123','Ricardo','$2a$10$lSnELFrcqrGZOfaehK5z3u4lV.tzCdw7It015fKXlBnKZ5C6JwKnK',NULL),
(3,2,'Perez','cliente@correo.com','Richard','$2a$10$uY/6VZafzcitoGlRYoBQL.yZU8eqtNWfNZ4EZJ/IYzUSXpsYdO2wm',NULL),
(4,2,'Polet','cliente2@correo.com','Santiago','$2a$10$BiUxn1DvOcAyrwjsSRrECOY3E8RYP7a6kUmqZkznxZAfmwZ2v7.k6',NULL),
(5,1,'pepe','rich3ar@d1223','Ricardo','$2a$10$D264XMVtvfsjbMGJ/EJDSeUWezKyy0oJvncvirKYwjUE2NPoB61Pe',NULL);
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

-- Dump completed on 2025-11-18 15:16:20
