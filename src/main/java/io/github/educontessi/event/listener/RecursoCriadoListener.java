package io.github.educontessi.event.listener;

import java.net.URI;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.github.educontessi.event.RecursoCriadoEvent;

public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

  @Override
  public void onApplicationEvent(RecursoCriadoEvent event) {
    HttpServletResponse response = event.getResponse();
    adicionarHeaderLocation(response, event.getId());
  }

  private void adicionarHeaderLocation(HttpServletResponse response, Long id) {
    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id)
        .toUri();
    response.setHeader("Location", uri.toASCIIString());
  }
}
