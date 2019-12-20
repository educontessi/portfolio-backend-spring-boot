package io.github.educontessi.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.github.educontessi.helpers.event.RecursoCriadoEvent;

public abstract class BaseResource {

	@Autowired
	private ApplicationEventPublisher publisher;

	protected ResponseEntity<Object> created(Long id, HttpServletResponse response, Object body) {
		publisher.publishEvent(new RecursoCriadoEvent(this, response, id));
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}

	protected ResponseEntity<Object> ok(Object body) {
		return ResponseEntity.ok(body);
	}
}
