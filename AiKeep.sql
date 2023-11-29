DROP DATABASE IF EXISTS ai_keep;
CREATE DATABASE ai_keep CHARACTER SET utf8 COLLATE utf8_general_ci;
use ai_keep;

CREATE TABLE `user` (
    `id` bigint(20) AUTO_INCREMENT NOT NULL,
    `nickname` varchar(255) DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `username` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `age` float NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `body_data` (
     `id` bigint(20)  AUTO_INCREMENT NOT NULL,
     `user_id` bigint(20)  NOT NULL,
     `weight` double NOT NULL,
     `height` double NOT NULL,
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `training_plan` (
     `id` bigint(20) AUTO_INCREMENT NOT NULL,
     `description` varchar(255) DEFAULT NULL,
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

