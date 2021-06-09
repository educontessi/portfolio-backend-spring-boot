package io.github.educontessi.api.resource;

import io.github.educontessi.domain.helpers.event.RecursoCriadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;

import javax.servlet.http.HttpServletResponse;

public abstract class BaseResource {

	@Autowired
	private ApplicationEventPublisher publisher;

	protected ResponseEntity<Object> created(Long id, HttpServletResponse response, Object body) {
		publisher.publishEvent(new RecursoCriadoEvent(this, response, id));
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}

	protected ResponseEntity<Object> ok() {
		BodyBuilder builder = ResponseEntity.ok();
		return ok(builder);
	}

	protected ResponseEntity<Object> ok(Object body) {
		return ResponseEntity.ok(body);
	}
}
