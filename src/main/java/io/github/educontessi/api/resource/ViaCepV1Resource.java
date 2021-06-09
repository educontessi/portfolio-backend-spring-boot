package io.github.educontessi.api.resource;

import io.github.educontessi.api.dataconverter.ViaCepV1DataConverter;
import io.github.educontessi.api.dto.ViaCepV1Dto;
import io.github.educontessi.domain.model.ViaCepResposta;
import io.github.educontessi.domain.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoints para busca de cadastros por CEP
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@RestController
@RequestMapping("/v1/busca-cep")
public class ViaCepV1Resource extends BaseResource {

	@Autowired
	private ViaCepService service;

	@Autowired
	private ViaCepV1DataConverter converter;

	@GetMapping("/{cep}")
	public ResponseEntity<ViaCepV1Dto> buscaCep(@PathVariable String cep) {
		ViaCepResposta entity = service.buscaEnderecoPorCep(cep);
		return ResponseEntity.ok(converter.convertToDto(entity));
	}

}
