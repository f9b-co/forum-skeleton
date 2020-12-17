DROP DATABASE IF EXISTS `forum_skeleton`;

CREATE DATABASE `forum_skeleton`;

USE `forum_skeleton`;

CREATE TABLE `technologies` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `technology_name` varchar(50) NOT NULL,
  `technology_rating` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_name` (`technology_name`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `questions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `question_author` varchar(50) NOT NULL,
  `question_phrase` varchar(255) NOT NULL,
  `question_datetime` datetime(6) NOT NULL,
  `question_text` varchar(1000) NOT NULL,
  `technology_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_author_datetime` (`question_author`,`question_datetime`),
  KEY `FK_IDX_technology_id` (`technology_id`),
  CONSTRAINT `FK_technology_id` FOREIGN KEY (`technology_id`) REFERENCES `technologies` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `answers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `answer_datetime` datetime(6) NOT NULL,
  `answer_author` varchar(50) NOT NULL,
  `answer_text` varchar(1000) NOT NULL,
  `question_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_author_datetime` (`answer_author`,`answer_datetime`),
  KEY `FK_IDX_question_id` (`question_id`),
  CONSTRAINT `FK_question_id` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
