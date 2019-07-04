package io.github.educontessi;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
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
