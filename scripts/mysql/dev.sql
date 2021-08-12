SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

CREATE TABLE IF NOT EXISTS `authority` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `description` text CHARACTER SET utf16 COLLATE utf16_bin,
  `time_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

CREATE TABLE `security_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `time_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

CREATE TABLE IF NOT EXISTS `group_authority` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_group` int NOT NULL,
  `id_authority` int NOT NULL,
  `time_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `group_authority_ibfk_1` FOREIGN KEY (`id_authority`) REFERENCES `authority` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `group_authority_ibfk_2` FOREIGN KEY (`id_group`) REFERENCES `security_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

CREATE TABLE IF NOT EXISTS `user_security_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `email` varchar(64) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `password_hash` varchar(64) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `enabled` bit(1) NOT NULL,
  `account_expiration_date` date DEFAULT NULL,
  `credentials_expiration_date` date DEFAULT NULL,
  `time_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

CREATE TABLE IF NOT EXISTS `user_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `surname` varchar(64) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `birthdate` date DEFAULT NULL,
  `address` varchar(128) CHARACTER SET utf16 COLLATE utf16_bin DEFAULT NULL,
  `time_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_info_ibfk_2` FOREIGN KEY (`id`) REFERENCES `user_security_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

CREATE TABLE IF NOT EXISTS `group_members` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_group` int NOT NULL,
  `id_user` int NOT NULL,
  `time_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `group_members_ibfk_1` FOREIGN KEY (`id_group`) REFERENCES `security_group` (`id`),
  CONSTRAINT `group_members_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user_security_details` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

CREATE USER 'dev_user'@'localhost' IDENTIFIED BY 'dev_user' ; 
CREATE USER 'dev_user'@'%' IDENTIFIED BY 'dev_user' ; 

GRANT SELECT, INSERT, UPDATE, DELETE ON dev.* to 'dev_user'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON dev.* to 'dev_user'@'%';

INSERT INTO `authority` (`id`, `name`, `description`) VALUES
(1,	'READ_PROFILES',	'Authority to be able to read other users profiles.'),
(2,	'READ_GROUPS',	'Authority used to see all groups.'),
(3,	'READ_PRIVILEGES ',	'Authority to see all authorities.'),
(4,	'WRITE_PROFILES',	'Authority to be able to write and update any user profile.'),
(5,	'WRITE_GROUPS',	'Authority used to create and update groups.'),
(6,	'WRITE_PRIVILEGES',	'Authority used to create new privileges.');

INSERT INTO `security_group` (`id`, `name`) VALUES
(1,	'Admins'),
(2,	'Managers'),
(3,	'Users');

INSERT INTO `group_authority` (`id_group`, `id_authority`) VALUES
(1,	1),
(2,	1),
(1,	2),
(2,	2),
(1,	3),
(2,	3),
(1,	4),
(1,	5),
(1,	6);

INSERT INTO `user_security_details` (`id`, `username`, `email`, `password_hash`, `enabled`, `account_expiration_date`, `credentials_expiration_date`) VALUES
(1,	'dricko',	'dominik.ricko@nth.ch',	'$2a$10$RzsfOZiGr/P2sRQPM/QQ6.nCHOXDaFuPbR46gMZPMutOGek2OCOIW',	CONV('1', 2, 10) + 0,	'9999-12-31',	'9999-12-31'),
(2,	'admin',	'admin@admin.com',	'$2a$10$2CMhIQlDK4PgtMYuDUTCAuy2nGNFNn7LakACqtSkOzvClrpQS6b52',	CONV('1', 2, 10) + 0,	'2021-08-21',	'2021-08-21'),
(3,	'user',	'user@user.com',	'$2a$10$35lyzg7IUQsTiKmSVWjqo.TxEY7avBd9qJpu1zP9GicpZbYVqeXUO',	CONV('1', 2, 10) + 0,	'2021-08-21',	'2021-08-21'),
(4,	'manager',	'manager@manager.com',	'$2a$10$HupdHJ1zIDO8UbUw22I21uVMdLhLwi9g1OEMKYw3e/kwrOLtPbps2',	CONV('1', 2, 10) + 0,	'2021-08-21',	'2021-08-21');

INSERT INTO `user_info` (`id`, `name`, `surname`, `birthdate`, `address`) VALUES
(1,	'Dominik',	'Ričko',	'1998-08-28',	'Ruđera Boškovića, 21, Petrinja 44250'),
(2,	'Admin',	'Administrator',	'2021-07-05',	NULL);

INSERT INTO `group_members` (`id_group`, `id_user`) VALUES
(1,	1),
(1,	2),
(3,	3),
(2,	4);