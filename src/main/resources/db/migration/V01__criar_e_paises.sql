CREATE TABLE `pais` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(100) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;

INSERT INTO pais (nome) values ('Brasil');
INSERT INTO pais (nome) values ('Argentina');
INSERT INTO pais (nome) values ('Uruguai');
INSERT INTO pais (nome) values ('Paraguai');
INSERT INTO pais (nome) values ('Chile');