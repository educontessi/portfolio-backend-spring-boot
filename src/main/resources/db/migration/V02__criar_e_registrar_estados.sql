CREATE TABLE `estado` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(100) NOT NULL,
	`sigla` VARCHAR(10) NOT NULL,
	`pais_id` BIGINT(20) NOT NULL,
	`data_criacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	`ultima_atualizacao` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	INDEX `FK_estado_pais` (`pais_id`),
	CONSTRAINT `FK_estado_pais` FOREIGN KEY (`pais_id`) REFERENCES `pais` (`id`)
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;

INSERT INTO `portfolio`.`estado` (`nome`, `sigla`, `pais_id`) VALUES ('Santa Catarina', 'SC', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `sigla`, `pais_id`) VALUES ('Rio Grande do Sul', 'RS', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `sigla`, `pais_id`) VALUES ('Paraná', 'PR', '1');