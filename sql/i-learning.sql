SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `i_learning`
--

DROP DATABASE IF EXISTS i_learning;
CREATE DATABASE i_learning;

USE i_learning;

-- i_student
CREATE TABLE i_student
(
    `id`   INT(5) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '学生ID',
    `name` VARCHAR(10)     NOT NULL COMMENT '学生姓名',
    `age`  TINYINT(4)             NOT NULL COMMENT '学生年龄',
    `sex`  TINYINT(4)           NOT NULL COMMENT '学生性别'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;