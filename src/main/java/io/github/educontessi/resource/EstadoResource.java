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

import io.github.educontessi.event.RecursoCriadoEvent;
import io.github.educontessi.model.Estado;
import io.github.educontessi.repository.EstadoRepository;
import io.github.educontessi.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

	@Autowired
	private EstadoRepository repository;

	@Autowired
	private EstadoService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Estado> findAll() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Estado> findById(@PathVariable Long id) {
		Optional<Estado> categoria = repository.findById(id);
		return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Estado> save(@Valid @RequestBody Estado entity, HttpServletResponse response) {
		Estado pais = repository.save(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pais.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pais);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Estado> update(@PathVariable Long id, @Valid @RequestBody Estado entity) {
		try {
			Estado pais = service.update(id, entity);
			return ResponseEntity.ok(pais);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
