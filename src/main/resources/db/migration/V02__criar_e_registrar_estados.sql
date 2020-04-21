CREATE TABLE `estado` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(100) NOT NULL,
	`uf` VARCHAR(10) NOT NULL,
	`pais_id` BIGINT(20) NOT NULL,
	`data_criacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	`ultima_atualizacao` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	INDEX `FK_estado_pais` (`pais_id`),
	CONSTRAINT `FK_estado_pais` FOREIGN KEY (`pais_id`) REFERENCES `pais` (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;


INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Acre', 'AC', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Alagoas', 'AL', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Amapá', 'AP', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Amazonas', 'AM', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Bahia', 'BA', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Ceará', 'CE', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Distrito Federal', 'DF', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Espírito Santo', 'ES', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Goiás', 'GO', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Maranhão', 'MA', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Mato Grosso', 'MT', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Mato Grosso do Sul', 'MS', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Minas Gerais', 'MG', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Pará', 'PA', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Paraíba', 'PB', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Paraná', 'PR', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Pernambuco', 'PE', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Piauí', 'PI', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Rio de Janeiro', 'RJ', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Rio Grande do Norte', 'RN', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Rio Grande do Sul', 'RS', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Rondônia', 'RO', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Roraima', 'RR', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Santa Catarina', 'SC', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('São Paulo', 'SP', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Sergipe', 'SE', '1');
INSERT INTO `portfolio`.`estado` (`nome`, `uf`, `pais_id`) VALUES ('Tocantins', 'TO', '1');
