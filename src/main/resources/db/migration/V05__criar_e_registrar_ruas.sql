CREATE TABLE `rua` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(100) NOT NULL,
	`cidade_id` BIGINT(20) NOT NULL,
	`data_criacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	`ultima_atualizacao` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	INDEX `FK_rua_cidade` (`cidade_id`),
	CONSTRAINT `FK_rua_cidade` FOREIGN KEY (`cidade_id`) REFERENCES `cidade` (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;

INSERT INTO `portfolio`.`rua` (`nome`, `cidade_id`) VALUES ('Rua A', 4330);
INSERT INTO `portfolio`.`rua` (`nome`, `cidade_id`) VALUES ('Rua B', 4330);
INSERT INTO `portfolio`.`rua` (`nome`, `cidade_id`) VALUES ('Rua C', 4330);
INSERT INTO `portfolio`.`rua` (`nome`, `cidade_id`) VALUES ('Rua D', 4330);
INSERT INTO `portfolio`.`rua` (`nome`, `cidade_id`) VALUES ('Rua E', 4330);
INSERT INTO `portfolio`.`rua` (`nome`, `cidade_id`) VALUES ('Rua F', 4330);
INSERT INTO `portfolio`.`rua` (`nome`, `cidade_id`) VALUES ('Rua G', 4330);
INSERT INTO `portfolio`.`rua` (`nome`, `cidade_id`) VALUES ('Rua H', 4330);