package io.github.educontessi.core.config.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	/*
	 * @Override public void addCorsMappings(CorsRegistry registry) {
	 * 
	 * registry.addMapping("/api/portfolio/**").allowedOrigins(
	 * "http://localhost:8000") .allowedMethods("GET", "POST", "PUT", "DELETE",
	 * "HEAD").allowCredentials(true); }
	 */

	@Bean
	public Filter shallowEtagHeaderFilter() {
		return new ShallowEtagHeaderFilter();
	}

}
