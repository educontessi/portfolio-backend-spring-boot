package io.github.educontessi.core.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Classe de configuração do Swagger
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String SNAPSHOT = "1.0-SNAPSHOT";
	private static final String HTTP_WWW_APACHE_ORG_LICENSES_LICENSE_2_0_HTML = "Usage of this code is forbidden without the author's consent";
	private static final String APACHE_2_0 = "Apache 2.0";

	@Bean
	public Docket api() {
		// http://localhost:9000/api/portfolio/swagger-ui.html

		// @formatter:off
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiEndPointsInfo());
		// @formatter:on
	}

	private ApiInfo apiEndPointsInfo() {
		// @formatter:off
		return new ApiInfoBuilder().title("Portfólio back-end com Spring Boot")
				.description("Projeto usado para colocar em prática meus conhecimentos nas tecnologias: "+ 
						"|Spring Framework" + 
						"|Spring Boot" + 
						"|Spring Data" + 
						"|Spring Test" + 
						"|Junit5" + 
						"|Flyway" + 
						"|REST" + 
						"|Swagger" + 
						"|Mysql" + 
						"|")
				.contact(new Contact("Eduardo Possamai Contessi", "http://educontessi.github.io/", "contessi@outlook.com")).license(APACHE_2_0)
				.licenseUrl(HTTP_WWW_APACHE_ORG_LICENSES_LICENSE_2_0_HTML).version(SNAPSHOT).build();
		// @formatter:on
	}

}
