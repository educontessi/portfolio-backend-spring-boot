CREATE TABLE `rua` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`cidade_id` BIGINT(20) NOT NULL,
	`nome` VARCHAR(100) NOT NULL,
	`create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`changed` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
	`deleted` TINYINT(4) NOT NULL DEFAULT '0',
	`delete_date` TIMESTAMP NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_rua_cidade` (`cidade_id`),
	CONSTRAINT `FK_rua_cidade` FOREIGN KEY (`cidade_id`) REFERENCES `cidade` (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;

INSERT INTO `rua` (`nome`, `cidade_id`) VALUES ('Rua A', 4330);
INSERT INTO `rua` (`nome`, `cidade_id`) VALUES ('Rua B', 4330);
INSERT INTO `rua` (`nome`, `cidade_id`) VALUES ('Rua C', 4330);
INSERT INTO `rua` (`nome`, `cidade_id`) VALUES ('Rua D', 4330);
INSERT INTO `rua` (`nome`, `cidade_id`) VALUES ('Rua E', 4330);
INSERT INTO `rua` (`nome`, `cidade_id`) VALUES ('Rua F', 4330);
INSERT INTO `rua` (`nome`, `cidade_id`) VALUES ('Rua G', 4330);
INSERT INTO `rua` (`nome`, `cidade_id`) VALUES ('Rua H', 4330);


CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `rua_view` AS SELECT * FROM rua WHERE deleted = false ;