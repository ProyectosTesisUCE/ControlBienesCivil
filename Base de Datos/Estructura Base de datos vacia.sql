-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.6.26-log - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para appbienes
CREATE DATABASE IF NOT EXISTS `appbienes` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `appbienes`;

-- Volcando estructura para tabla appbienes.banners
CREATE TABLE IF NOT EXISTS `banners` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `archivo` varchar(255) DEFAULT NULL,
  `estatus` varchar(255) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla appbienes.bienes
CREATE TABLE IF NOT EXISTS `bienes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alta` varchar(255) DEFAULT NULL,
  `anterior` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `control` varchar(255) DEFAULT NULL,
  `costo` double DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_ingreso` datetime DEFAULT NULL,
  `garantia` datetime DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  `serie` varchar(255) DEFAULT NULL,
  `vida_util` int(11) NOT NULL,
  `id_detalle` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq137l3yi4oevo4q1upvtn9l6l` (`id_detalle`),
  CONSTRAINT `FKq137l3yi4oevo4q1upvtn9l6l` FOREIGN KEY (`id_detalle`) REFERENCES `detalles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla appbienes.bienes_estaciones
CREATE TABLE IF NOT EXISTS `bienes_estaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `actualizacion` datetime DEFAULT NULL,
  `registro` datetime DEFAULT NULL,
  `id_bien` int(11) DEFAULT NULL,
  `id_estacion` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmh1shgu1hvu4mmkpywsnkndbh` (`id_bien`),
  KEY `FKp5tuw1jbvxrxndwdi4q1b668s` (`id_estacion`),
  CONSTRAINT `FKmh1shgu1hvu4mmkpywsnkndbh` FOREIGN KEY (`id_bien`) REFERENCES `bienes` (`id`),
  CONSTRAINT `FKp5tuw1jbvxrxndwdi4q1b668s` FOREIGN KEY (`id_estacion`) REFERENCES `estaciones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla appbienes.detalles
CREATE TABLE IF NOT EXISTS `detalles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `asignado` varchar(255) DEFAULT NULL,
  `causionado` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `estatus` varchar(255) DEFAULT NULL,
  `guarda_almacen` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla appbienes.estaciones
CREATE TABLE IF NOT EXISTS `estaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imagen` varchar(255) DEFAULT NULL,
  `lugar` varchar(255) DEFAULT NULL,
  `ubicacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla appbienes.noticias
CREATE TABLE IF NOT EXISTS `noticias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detalle` varchar(255) DEFAULT NULL,
  `estatus` varchar(255) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla appbienes.perfiles
CREATE TABLE IF NOT EXISTS `perfiles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cuenta` varchar(255) DEFAULT NULL,
  `perfil` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla appbienes.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activo` int(11) NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `cuenta` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
usuariosusuarios