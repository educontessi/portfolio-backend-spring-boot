CREATE TABLE `bairro` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(100) NOT NULL,
	`cidade_id` BIGINT(20) NOT NULL,
	`data_criacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	`ultima_atualizacao` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	INDEX `FK_bairro_cidade` (`cidade_id`),
	CONSTRAINT `FK_bairro_cidade` FOREIGN KEY (`cidade_id`) REFERENCES `cidade` (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;


INSERT INTO `portfolio`.`bairro` (`nome`, `cidade_id`) VALUES ('Mato Alto', 4330);
INSERT INTO `portfolio`.`bairro` (`nome`, `cidade_id`) VALUES ('Cidade Alta', 4330);
INSERT INTO `portfolio`.`bairro` (`nome`, `cidade_id`) VALUES ('Centro', 4330);
INSERT INTO `portfolio`.`bairro` (`nome`, `cidade_id`) VALUES ('Caverazinho', 4330);
INSERT INTO `portfolio`.`bairro` (`nome`, `cidade_id`) VALUES ('Urussanguinha', 4330);