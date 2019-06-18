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
import io.github.educontessi.model.Bairro;
import io.github.educontessi.repository.BairroRepository;
import io.github.educontessi.service.BairroService;

@RestController
@RequestMapping("/bairros")
public class BairroResource {

	@Autowired
	private BairroRepository repository;

	@Autowired
	private BairroService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Bairro> findAll() {
		return repository.findAll();
	}

	@GetMapping("/cidade/{cidadeId}")
	public List<Bairro> findByEstadoId(@PathVariable Long cidadeId) {
		return repository.findByCidadeId(cidadeId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Bairro> findById(@PathVariable Long id) {
		Optional<Bairro> entity = repository.findById(id);
		return entity.isPresent() ? ResponseEntity.ok(entity.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Bairro> save(@Valid @RequestBody Bairro entity, HttpServletResponse response) {
		Bairro cidade = repository.save(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, cidade.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Bairro> update(@PathVariable Long id, @Valid @RequestBody Bairro entity) {
		try {
			Bairro cidade = service.update(id, entity);
			return ResponseEntity.ok(cidade);
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
