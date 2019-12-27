CREATE TABLE `pais` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(100) NOT NULL,
	`sigla` VARCHAR(10) NOT NULL,
	`bacen` VARCHAR(10) NOT NULL,
	`create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`changed` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
	`deleted` TINYINT(4) NOT NULL DEFAULT '0',
	`delete_date` TIMESTAMP NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;

INSERT INTO `portfolio`.`pais` (`nome`, `sigla`, `bacen`) VALUES ('Brasil', 'BR', '1058');
INSERT INTO `portfolio`.`pais` (`nome`, `sigla`, `bacen`) VALUES ('Uruguai', 'UY', '8451');
INSERT INTO `portfolio`.`pais` (`nome`, `sigla`, `bacen`) VALUES ('Argentina', 'AR', '0639');
INSERT INTO `portfolio`.`pais` (`nome`, `sigla`, `bacen`) VALUES ('Paraguai', 'PY', '5860');
INSERT INTO `portfolio`.`pais` (`nome`, `sigla`, `bacen`) VALUES ('Chile', 'CL', '1589');
INSERT INTO `portfolio`.`pais` (`nome`, `sigla`, `bacen`) VALUES ('Bolívia', 'BO', '0973');
INSERT INTO `portfolio`.`pais` (`nome`, `sigla`, `bacen`) VALUES ('Perú', 'PE', '5894');