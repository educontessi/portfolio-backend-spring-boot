CREATE TABLE `pessoa` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`status` VARCHAR(50) NOT NULL DEFAULT '',
	`tipo_pessoa` VARCHAR(50) NOT NULL DEFAULT '',
	`nome_razao` VARCHAR(100) NOT NULL,
	`data_cadastro` DATE NOT NULL,
	`cpf_cnpj` VARCHAR(20) NOT NULL,
	`data_nascimento` DATE NOT NULL,
	`rg_ie` VARCHAR(50) NULL DEFAULT NULL,
	`cidade_id` BIGINT(20) NULL DEFAULT NULL,
	`bairro_id` BIGINT(20) NULL DEFAULT NULL,
	`rua_id` BIGINT(20) NULL DEFAULT NULL,
	`cep` VARCHAR(50) NULL DEFAULT NULL,
	`numero` VARCHAR(50) NULL DEFAULT NULL,
	`complemento` VARCHAR(100) NULL DEFAULT NULL,
	`proximidade` VARCHAR(100) NULL DEFAULT NULL,
	`telefone` VARCHAR(50) NULL DEFAULT NULL,
	`celular` VARCHAR(50) NULL DEFAULT NULL,
	`email` VARCHAR(100) NULL DEFAULT NULL,
	`observacao` TEXT NULL,
	`sexo` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `cpf_cnpj` (`cpf_cnpj`),
	INDEX `FK_pessoa_cidade` (`cidade_id`),
	INDEX `FK_pessoa_bairro` (`bairro_id`),
	INDEX `FK_pessoa_rua` (`rua_id`),
	CONSTRAINT `FK_pessoa_bairro` FOREIGN KEY (`bairro_id`) REFERENCES `bairro` (`id`),
	CONSTRAINT `FK_pessoa_cidade` FOREIGN KEY (`cidade_id`) REFERENCES `cidade` (`id`),
	CONSTRAINT `FK_pessoa_rua` FOREIGN KEY (`rua_id`) REFERENCES `rua` (`id`)
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;


INSERT INTO `portfolio`.`pessoa` (`id`, `status`, `tipo_pessoa`, `nome_razao`, `data_cadastro`, `cpf_cnpj`, `data_nascimento`, `rg_ie`, `cidade_id`, `bairro_id`, `rua_id`, `cep`, `numero`, `complemento`, `proximidade`, `telefone`, `celular`, `email`, `observacao`, `sexo`) VALUES (1, 'ATIVO', 'FISICA', 'Pessoa 01', '2019-06-24', '24553420775', '2019-06-24', '1234', 1, 2, 1, '888888888', '888', 'casa', 'mercado', '48999999999', '48999999999', 'teste@teste.com', 'obs', 'MASCULINO');
INSERT INTO `portfolio`.`pessoa` (`id`, `status`, `tipo_pessoa`, `nome_razao`, `data_cadastro`, `cpf_cnpj`, `data_nascimento`, `rg_ie`, `cidade_id`, `bairro_id`, `rua_id`, `cep`, `numero`, `complemento`, `proximidade`, `telefone`, `celular`, `email`, `observacao`, `sexo`) VALUES (2, 'INATIVO', 'FISICA', 'Pessoa 02', '2019-06-24', '36614740490', '2019-06-24', '1234', 1, 2, 1, '888888888', '888', 'casa', 'mercado', '48999999999', '48999999999', 'teste@teste.com', 'obs', 'FEMININO');