package io.github.educontessi.domain.helpers.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Classe usada para adicionar o header location nos recursos criados
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public class RecursoCriadoEvent extends ApplicationEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private final transient  HttpServletResponse response;
	private final Long id;

	public RecursoCriadoEvent(Object source, HttpServletResponse response, Long id) {
		super(source);
		this.response = response;
		this.id = id;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getId() {
		return id;
	}
}
