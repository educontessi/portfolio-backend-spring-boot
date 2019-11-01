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
import io.github.educontessi.model.Cidade;
import io.github.educontessi.service.CidadeService;

/**
 * Endpoints para {@link Cidade}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@RestController
@RequestMapping("/v1/cidades")
public class CidadeV1Resource {

	@Autowired
	private CidadeService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Cidade> findAll() {
		return service.findAll();
	}

	@GetMapping("/estado/{estadoId}")
	public List<Cidade> findByEstadoId(@PathVariable Long estadoId) {
		return service.findByEstadoId(estadoId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cidade> findById(@PathVariable Long id) {
		Optional<Cidade> entity = service.findById(id);
		return entity.isPresent() ? ResponseEntity.ok(entity.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping("/ibge/{ibge}")
	public ResponseEntity<Cidade> findByIbge(@PathVariable Integer ibge) {
		Optional<Cidade> entity = service.findByIbge(ibge);
		return entity.isPresent() ? ResponseEntity.ok(entity.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Cidade> save(@Valid @RequestBody Cidade entity, HttpServletResponse response) {
		Cidade cidade = service.save(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, cidade.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cidade> update(@PathVariable Long id, @Valid @RequestBody Cidade entity) {
		try {
			Cidade cidade = service.update(id, entity);
			return ResponseEntity.ok(cidade);
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
