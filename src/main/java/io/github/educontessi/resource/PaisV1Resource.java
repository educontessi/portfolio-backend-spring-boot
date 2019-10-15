package io.github.educontessi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.educontessi.helpers.event.RecursoCriadoEvent;
import io.github.educontessi.model.Pais;
import io.github.educontessi.service.PaisService;

@RestController
@RequestMapping("/v1/paises")
public class PaisV1Resource {

	private PaisService service;
	private ApplicationEventPublisher publisher;

	@Autowired
	public PaisV1Resource(PaisService service, ApplicationEventPublisher publisher) {
		this.service = service;
		this.publisher = publisher;
	}

	@GetMapping
	public List<Pais> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pais> findById(@PathVariable Long id) {
		Optional<Pais> categoria = service.findById(id);
		return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Pais> save(@Valid @RequestBody Pais entity, HttpServletResponse response) {
		Pais pais = service.save(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pais.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pais);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pais> update(@PathVariable Long id, @Valid @RequestBody Pais entity) {
		try {
			Pais pais = service.update(id, entity);
			return ResponseEntity.ok(pais);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
