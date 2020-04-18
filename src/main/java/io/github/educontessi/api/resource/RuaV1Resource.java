package io.github.educontessi.api.resource;

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

import io.github.educontessi.domain.helpers.event.RecursoCriadoEvent;
import io.github.educontessi.domain.model.Rua;
import io.github.educontessi.domain.service.RuaService;

/**
 * Endpoints para {@link Rua}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@RestController
@RequestMapping("/v1/ruas")
public class RuaV1Resource {

	@Autowired
	private RuaService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Rua> findAll() {
		return service.findAll();
	}

	@GetMapping("/cidade/{cidadeId}")
	public List<Rua> findByCidadeId(@PathVariable Long cidadeId) {
		return service.findByCidadeId(cidadeId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Rua> findById(@PathVariable Long id) {
		Optional<Rua> entity = service.findById(id);
		return entity.isPresent() ? ResponseEntity.ok(entity.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Rua> save(@Valid @RequestBody Rua entity, HttpServletResponse response) {
		Rua cidade = service.save(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, cidade.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Rua> update(@PathVariable Long id, @Valid @RequestBody Rua entity) {
		try {
			Rua cidade = service.update(id, entity);
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