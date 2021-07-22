CREATE DATABASE dev;
CREATE DATABASE prod;

CREATE USER 'dev_user'@'localhost' IDENTIFIED BY 'dev_user' ; 
CREATE USER 'prod_user'@'localhost' IDENTIFIED by 'prod_user';
CREATE USER 'dev_user'@'%' IDENTIFIED BY 'dev_user' ; 
CREATE USER 'prod_user'@'%' IDENTIFIED by 'prod_user';

GRANT SELECT, INSERT, UPDATE, DELETE ON dev.* to 'dev_user'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON prod.* to 'prod_user'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON dev.* to 'dev_user'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE ON prod.* to 'prod_user'@'%';

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

CREATE TABLE IF NOT EXISTS `authority` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `description` longtext CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;


CREATE TABLE IF NOT EXISTS `group_authority` (
  `id_group` bigint NOT NULL,
  `id_authority` bigint NOT NULL,
  PRIMARY KEY (`id_authority`,`id_group`),
  KEY `id_group` (`id_group`),
  CONSTRAINT `group_authority_ibfk_1` FOREIGN KEY (`id_authority`) REFERENCES `authority` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `group_authority_ibfk_2` FOREIGN KEY (`id_group`) REFERENCES `security_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;


CREATE TABLE IF NOT EXISTS `group_members` (
  `id_group` bigint NOT NULL,
  `id_user` bigint NOT NULL,
  PRIMARY KEY (`id_user`,`id_group`),
  KEY `id_group` (`id_group`),
  CONSTRAINT `group_members_ibfk_1` FOREIGN KEY (`id_group`) REFERENCES `security_group` (`id`),
  CONSTRAINT `group_members_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user_security_details` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;


CREATE TABLE IF NOT EXISTS `security_group` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;


CREATE TABLE IF NOT EXISTS `user_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf16_bin NOT NULL,
  `surname` varchar(255) COLLATE utf16_bin NOT NULL,
  `birthdate` date NOT NULL,
  `address` varchar(255) COLLATE utf16_bin NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_info_ibfk_2` FOREIGN KEY (`id`) REFERENCES `user_security_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

CREATE TABLE IF NOT EXISTS `user_security_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `email` varchar(255) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `password_hash` varchar(255) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `enabled` bit(1) NOT NULL,
  `account_expiration_date` date DEFAULT NULL,
  `credentials_expiration_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;