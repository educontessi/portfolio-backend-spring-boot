CREATE TABLE `pessoa` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`status` VARCHAR(50) NOT NULL,
	`tipo_pessoa` VARCHAR(50) NOT NULL,
	`nome_razao` VARCHAR(100) NOT NULL,
	`data_cadastro` DATE NOT NULL,
	`cpf_cnpj` VARCHAR(20) NULL DEFAULT NULL,
	`data_nascimento` DATE NULL DEFAULT NULL,
	`rg_ie` VARCHAR(50) NULL DEFAULT NULL,
	`cidade_id` BIGINT(20) NULL DEFAULT NULL,
	`bairro_id` BIGINT(20) NULL DEFAULT NULL,
	`rua_id` BIGINT(20) NULL DEFAULT NULL,
	`cep` VARCHAR(50) NULL DEFAULT NULL,
	`numero` VARCHAR(50) NULL DEFAULT NULL,
	`complemento` VARCHAR(100) NULL DEFAULT NULL,
	`proximidade` VARCHAR(100) NULL DEFAULT NULL,
	`numero_contato_principal` VARCHAR(50) NULL DEFAULT NULL,
	`obs_contato_principal` VARCHAR(100) NULL DEFAULT NULL,
	`numero_contato_alternativo` VARCHAR(50) NULL DEFAULT NULL,
	`obs_contato_alternativo` VARCHAR(100) NULL DEFAULT NULL,
	`email_principal` VARCHAR(100) NULL DEFAULT NULL,
	`obs_email_principal` VARCHAR(100) NULL DEFAULT NULL,
	`email_alternativo` VARCHAR(100) NULL DEFAULT NULL,
	`obs_email_alternativo` VARCHAR(100) NULL DEFAULT NULL,
	`observacao` TEXT NULL,
	`sexo` VARCHAR(50) NULL DEFAULT NULL,
	`create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`changed` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
	`deleted` TINYINT(4) NOT NULL DEFAULT '0',
	`delete_date` TIMESTAMP NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_pessoa_cidade` (`cidade_id`),
	INDEX `FK_pessoa_bairro` (`bairro_id`),
	INDEX `FK_pessoa_rua` (`rua_id`),
	CONSTRAINT `FK_pessoa_bairro` FOREIGN KEY (`bairro_id`) REFERENCES `bairro` (`id`),
	CONSTRAINT `FK_pessoa_cidade` FOREIGN KEY (`cidade_id`) REFERENCES `cidade` (`id`),
	CONSTRAINT `FK_pessoa_rua` FOREIGN KEY (`rua_id`) REFERENCES `rua` (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;


INSERT INTO `pessoa` (`id`, `status`, `tipo_pessoa`, `nome_razao`, `data_cadastro`, `cpf_cnpj`, `data_nascimento`, `rg_ie`, `cidade_id`, `bairro_id`, `rua_id`, `cep`, `numero`, `complemento`, `proximidade`, `numero_contato_principal`, `numero_contato_alternativo`, `email_principal`, `observacao`, `sexo`) VALUES (1, 'ATIVO', 'FISICA', 'Eduardo Contessi', '2019-06-24', '24553420775', '2019-06-24', '1234', 4330, 1, 1, '888888888', '888', 'casa', 'mercado', '48999999999', '48999999999', 'teste@teste.com', 'obs', 'MASCULINO');
INSERT INTO `pessoa` (`id`, `status`, `tipo_pessoa`, `nome_razao`, `data_cadastro`, `cpf_cnpj`, `data_nascimento`, `rg_ie`, `cidade_id`, `bairro_id`, `rua_id`, `cep`, `numero`, `complemento`, `proximidade`, `numero_contato_principal`, `numero_contato_alternativo`, `email_principal`, `observacao`, `sexo`) VALUES (2, 'ATIVO', 'JURIDICA', 'Contato Internet', '2019-06-24', '08719579000159', '2019-06-24', '1234', 4330, 2, 1, '888888888', '888', 'casa', 'mercado', '48999999999', '48999999999', 'teste@teste.com', 'obs', 'FEMININO');
INSERT INTO `pessoa` (`id`, `status`, `tipo_pessoa`, `nome_razao`, `data_cadastro`, `cpf_cnpj`, `data_nascimento`, `rg_ie`, `cidade_id`, `bairro_id`, `rua_id`, `cep`, `numero`, `complemento`, `proximidade`, `numero_contato_principal`, `numero_contato_alternativo`, `email_principal`, `observacao`, `sexo`) VALUES (3, 'ATIVO', 'JURIDICA', 'Prefeitura Araranguá', '2019-06-24', '82911249000113', '2019-06-24', '1234', 4330, 2, 1, '888888888', '888', 'casa', 'mercado', '48999999999', '48999999999', 'teste@teste.com', 'obs', null);


CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `pessoa_view` AS SELECT * FROM pessoa WHERE deleted = false ;