-- Create database
CREATE DATABASE IF NOT EXISTS paymybuddy;

-- Use database
USE paymybuddy;

-- Dropping all tables first
DROP TABLE IF EXISTS paymybuddy.user_contacts;
DROP TABLE IF EXISTS paymybuddy.account;
DROP TABLE IF EXISTS paymybuddy.transaction;
DROP TABLE IF EXISTS paymybuddy.user;

-- paymybuddy.user definition
CREATE TABLE `user`(
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `birthdate` date NOT NULL,
  `solde` decimal(4,2) DEFAULT '0.00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- paymybuddy.account definition
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int,
  `amount` decimal(4,2) NOT NULL DEFAULT '0.00',
  `iban` varchar(20),
  `bic` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_unite` (`user_id`),
  UNIQUE KEY `iban` (`iban`),
  UNIQUE KEY `bic` (`bic`),
  KEY `account_FK` (`user_id`),
  CONSTRAINT `account_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) 

-- paymybuddy.transaction definition
CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` float NOT NULL DEFAULT '0',
  `transaction_type` varchar(500) NOT NULL,
  `receiver_id` int NOT NULL,
  `sender_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `transaction_FK_1` (`receiver_id`),
  KEY `transaction_FK_2` (`sender_id`),
  CONSTRAINT `transaction_FK_1` FOREIGN KEY (`receiver_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `transaction_FK_2` FOREIGN KEY (`sender_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) 

-- paymybuddy.user_contacts definition
CREATE TABLE `user_contacts` (
  `user_contacts_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `contacts_id` int NOT NULL,
  PRIMARY KEY (`user_contacts_id`),
  UNIQUE KEY `user_unite` (`user_id`,`contacts_id`),
  KEY `user_unite_FK_1` (`user_id`),
  KEY `user_unite_FK_2` (`contacts_id`),
  CONSTRAINT `user_beneficiaries_FK_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_beneficiaries_FK_2` FOREIGN KEY (`contacts_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) 
