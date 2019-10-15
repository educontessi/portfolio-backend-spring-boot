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
import io.github.educontessi.model.Estado;
import io.github.educontessi.service.EstadoService;

@RestController
@RequestMapping("/v1/estados")
public class EstadoV1Resource {

	private EstadoService service;
	private ApplicationEventPublisher publisher;

	@Autowired
	public EstadoV1Resource(EstadoService service, ApplicationEventPublisher publisher) {
		this.service = service;
		this.publisher = publisher;
	}

	@GetMapping
	public List<Estado> findAll() {
		return service.findAll();
	}

	@GetMapping("/pais/{paisId}")
	public List<Estado> findByPaisId(@PathVariable Long paisId) {
		return service.findByPaisId(paisId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Estado> findById(@PathVariable Long id) {
		Optional<Estado> entity = service.findById(id);
		return entity.isPresent() ? ResponseEntity.ok(entity.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping("/uf/{uf}")
	public ResponseEntity<Estado> findByUf(@PathVariable String uf) {
		Optional<Estado> entity = service.findByUf(uf);
		return entity.isPresent() ? ResponseEntity.ok(entity.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Estado> save(@Valid @RequestBody Estado entity, HttpServletResponse response) {
		Estado estado = service.save(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, estado.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(estado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Estado> update(@PathVariable Long id, @Valid @RequestBody Estado entity) {
		try {
			Estado estado = service.update(id, entity);
			return ResponseEntity.ok(estado);
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
