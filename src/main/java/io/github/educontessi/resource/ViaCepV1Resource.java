package io.github.educontessi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.educontessi.model.ViaCepResposta;
import io.github.educontessi.service.ViaCepService;

/**
 * Endpoints para busca de cadastros por CEP
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@RestController
@RequestMapping("/v1/busca-cep")
public class ViaCepV1Resource {

	@Autowired
	private ViaCepService service;

	@GetMapping("/{cep}")
	public ResponseEntity<ViaCepResposta> findById(@PathVariable String cep) {
		ViaCepResposta entity = service.buscaEnderecoPorCep(cep);
		return (entity != null && entity.isValid()) ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
	}

}
