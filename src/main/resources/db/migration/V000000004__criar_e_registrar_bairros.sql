CREATE TABLE `bairro` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(100) NOT NULL,
	`cidade_id` BIGINT(20) NOT NULL,
	`create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`changed` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
	`deleted` TINYINT(4) NOT NULL DEFAULT '0',
	`delete_date` TIMESTAMP NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_bairro_cidade` (`cidade_id`),
	CONSTRAINT `FK_bairro_cidade` FOREIGN KEY (`cidade_id`) REFERENCES `cidade` (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;


INSERT INTO `bairro` (`nome`, `cidade_id`) VALUES ('Mato Alto', 4330);
INSERT INTO `bairro` (`nome`, `cidade_id`) VALUES ('Cidade Alta', 4330);
INSERT INTO `bairro` (`nome`, `cidade_id`) VALUES ('Centro', 4330);
INSERT INTO `bairro` (`nome`, `cidade_id`) VALUES ('Caverazinho', 4330);
INSERT INTO `bairro` (`nome`, `cidade_id`) VALUES ('Urussanguinha', 4330);


CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `bairro_view` AS SELECT * FROM bairro WHERE deleted = false ;