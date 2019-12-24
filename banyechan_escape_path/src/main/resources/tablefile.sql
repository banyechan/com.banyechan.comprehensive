-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.28 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.2.0.4947
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 escape_routes_db 的数据库结构
CREATE DATABASE IF NOT EXISTS `escape_routes_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `escape_routes_db`;


-- 导出  表 escape_routes_db.edge 结构
CREATE TABLE IF NOT EXISTS `edge` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `point_id` int(11) NOT NULL DEFAULT '0' COMMENT '起点定位点id',
  `end_point_id` int(11) NOT NULL DEFAULT '0' COMMENT '相邻定位点id',
  `distance` double NOT NULL DEFAULT '0' COMMENT '两定位点之间的距离',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='各定位之间的距离';

-- 正在导出表  escape_routes_db.edge 的数据：~21 rows (大约)
DELETE FROM `edge`;
/*!40000 ALTER TABLE `edge` DISABLE KEYS */;
INSERT INTO `edge` (`id`, `point_id`, `end_point_id`, `distance`, `create_time`, `update_time`) VALUES
	(1, 1, 2, 3, '2019-12-17 13:34:38', '2019-12-17 13:34:38'),
	(2, 1, 4, 2, '2019-12-17 13:34:51', '2019-12-17 13:34:51'),
	(3, 1, 5, 5, '2019-12-17 13:35:12', '2019-12-17 13:35:12'),
	(4, 2, 3, 2, '2019-12-17 13:35:36', '2019-12-17 13:35:36'),
	(5, 2, 5, 1, '2019-12-17 13:35:57', '2019-12-17 13:35:57'),
	(6, 2, 1, 3, '2019-12-17 13:36:31', '2019-12-17 13:36:31'),
	(7, 3, 2, 2, '2019-12-17 13:36:41', '2019-12-17 13:36:41'),
	(8, 3, 5, 4, '2019-12-17 13:36:57', '2019-12-17 13:36:57'),
	(9, 3, 7, 4, '2019-12-17 13:37:26', '2019-12-17 13:37:26'),
	(10, 4, 1, 2, '2019-12-17 13:37:47', '2019-12-17 13:37:47'),
	(11, 4, 5, 3, '2019-12-17 13:38:02', '2019-12-17 13:38:02'),
	(12, 4, 6, 5, '2019-12-17 13:38:24', '2019-12-17 13:38:24'),
	(13, 5, 3, 4, '2019-12-17 13:38:43', '2019-12-17 13:38:43'),
	(14, 5, 2, 1, '2019-12-17 13:38:51', '2019-12-17 13:38:55'),
	(15, 5, 1, 5, '2019-12-17 13:39:15', '2019-12-17 13:39:26'),
	(16, 5, 4, 3, '2019-12-17 13:39:40', '2019-12-17 13:39:57'),
	(17, 5, 6, 1, '2019-12-17 13:39:51', '2019-12-17 13:39:51'),
	(18, 6, 5, 1, '2019-12-17 13:40:24', '2019-12-17 13:40:24'),
	(19, 6, 4, 5, '2019-12-17 13:41:38', '2019-12-17 13:41:38'),
	(20, 6, 7, 3, '2019-12-17 13:41:47', '2019-12-17 13:41:47'),
	(21, 7, 3, 4, '2019-12-17 13:42:01', '2019-12-17 13:42:01'),
	(22, 7, 6, 3, '2019-12-17 13:42:18', '2019-12-17 13:42:18');
/*!40000 ALTER TABLE `edge` ENABLE KEYS */;


-- 导出  表 escape_routes_db.vertex 结构
CREATE TABLE IF NOT EXISTS `vertex` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `point` varchar(50) NOT NULL DEFAULT '0' COMMENT '定位点名称',
  `is_exit` tinyint(4) NOT NULL DEFAULT '1' COMMENT '否为逃生口(0:否；1:是，默认为1)'' A',
  `tunnel_id` int(11) NOT NULL DEFAULT '0' COMMENT '管廊id',
  `store_id` int(11) NOT NULL DEFAULT '0' COMMENT '管舱id',
  `area_id` int(11) NOT NULL DEFAULT '0' COMMENT '区id',
  `longitude` decimal(11,8) NOT NULL DEFAULT '0.00000000' COMMENT '经度',
  `latitude` decimal(11,8) NOT NULL DEFAULT '0.00000000' COMMENT '纬度',
  `state` int(11) NOT NULL DEFAULT '1' COMMENT '状态(0:废除；1:正常，默认为1)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='路线定位点';

-- 正在导出表  escape_routes_db.vertex 的数据：~7 rows (大约)
DELETE FROM `vertex`;
/*!40000 ALTER TABLE `vertex` DISABLE KEYS */;
INSERT INTO `vertex` (`id`, `point`, `is_exit`, `tunnel_id`, `store_id`, `area_id`, `longitude`, `latitude`, `state`, `create_time`, `update_time`) VALUES
	(1, '1', 1, 0, 0, 0, 112.50019793, 37.71059485, 1, '2019-12-17 13:33:23', '2019-12-23 15:16:55'),
	(2, '2', 1, 0, 0, 0, 112.51371972, 37.70667565, 1, '2019-12-17 13:33:56', '2019-12-23 15:16:08'),
	(3, '3', 1, 0, 0, 0, 112.51590948, 37.70604096, 1, '2019-12-17 13:34:00', '2019-12-23 15:16:32'),
	(4, '4', 1, 0, 0, 0, 112.50184026, 37.71011883, 1, '2019-12-17 13:34:04', '2019-12-23 15:17:17'),
	(5, '5', 1, 0, 0, 0, 0.00000000, 0.00000000, 1, '2019-12-17 13:34:08', '2019-12-17 13:34:08'),
	(6, '6', 1, 0, 0, 0, 0.00000000, 0.00000000, 1, '2019-12-17 13:34:12', '2019-12-17 13:34:12'),
	(7, '7', 1, 0, 0, 0, 0.00000000, 0.00000000, 1, '2019-12-17 13:34:17', '2019-12-17 13:34:17');
/*!40000 ALTER TABLE `vertex` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
