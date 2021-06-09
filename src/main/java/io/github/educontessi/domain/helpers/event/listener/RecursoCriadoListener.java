package io.github.educontessi.domain.helpers.event.listener;

import io.github.educontessi.domain.helpers.event.RecursoCriadoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

/**
 * ApplicationListener para o {@link RecursoCriadoEvent} usado para adicionar o
 * header location no recurso criado
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvent event) {
		HttpServletResponse response = event.getResponse();
		adicionarHeaderLocation(response, event.getId());
	}

	private void adicionarHeaderLocation(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}
}
