package io.github.educontessi;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.github.educontessi.api.config.swagger.SwaggerConfig;

/**
 * Main Application
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "io.github.educontessi" })
@ComponentScan(basePackageClasses = { PortfolioBackendSpringBootApplication.class, SwaggerConfig.class })
public class PortfolioBackendSpringBootApplication {

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("TimeZone"));
	}

	public static void main(String[] args) {
		// mvnw spring-boot:run
		SpringApplication.run(PortfolioBackendSpringBootApplication.class, args);
	}

}
