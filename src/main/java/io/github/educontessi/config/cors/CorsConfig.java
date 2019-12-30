package io.github.educontessi.config.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/api/portfolio/**").allowedOrigins("http://localhost:8000")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD").allowCredentials(true);
	}

}
