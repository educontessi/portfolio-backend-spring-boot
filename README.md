# Portfólio back-end com Spring Boot

Projeto usado para colocar em prática meus conhecimentos nas tecnologias:

- Spring Framework
- Spring Boot
- Spring Data
- Spring Test
- Junit5
- Flyway
- REST
- Swagger
- Mysql

## Configurando o projeto

1) git clone https://github.com/eduContessi/portfolio-backend-spring-boot.git

2) Importe o projeto em sua IDE

3) Altere o usuario e senha para que o projeto possa acessar o banco mysql. Atual: `user=usuario, password=usuario`

3) Acesse a pasta do projeto: `cd {{seuWorkspace}}/portfolio-backend-spring-boot`

4) Inicie a aplicação: `mvnw spring-boot:run`

5) Acesse: http://localhost:9000/api/portfolio/swagger-ui.html para visualizar os endpoints

## Rodando os testes unitários

1) Acesse a pasta do projeto: `cd {{seuWorkspace}}/portfolio-backend-spring-boot`

2) Rode o comando: `mvnw test`
