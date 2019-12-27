package io.github.educontessi.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import io.github.educontessi.dataconverter.PaisV1DataConverter;
import io.github.educontessi.dto.PaisV1Dto;
import io.github.educontessi.model.Pais;
import io.github.educontessi.service.PaisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Endpoints para {@link Pais}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@RestController
@RequestMapping("/v1/paises")
@Api(produces = "application/json", value = "PaisV1Resource")
public class PaisV1Resource extends BaseResource {

	@Autowired
	private PaisService service;

	@Autowired(required = true)
	private PaisV1DataConverter converter;

	@GetMapping
	@ApiOperation(value = "Busca todos", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Recuperou com sucesso todos os recursos"),
			@ApiResponse(code = 401, message = "Você não está autorizado a visualizar o recurso"),
			@ApiResponse(code = 403, message = "É proibido acessar o recurso que você está tentando acessar"),
			@ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public List<PaisV1Dto> findAll() {
		List<Pais> lista = service.findAll();
		List<PaisV1Dto> listaDto = new ArrayList<>();
		listaDto.addAll(lista.stream().map(x -> converter.convertToDto(x)).collect(Collectors.toList()));

		return listaDto;
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Busca por ID", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Recurso recuperado com sucesso"),
			@ApiResponse(code = 401, message = "Você não está autorizado a visualizar o recurso"),
			@ApiResponse(code = 403, message = "É proibido acessar o recurso que você está tentando acessar"),
			@ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<PaisV1Dto> findById(
			@ApiParam(value = "Código do país", required = true) @PathVariable Long id) {
		Optional<Pais> categoria = service.findById(id);
		return categoria.isPresent() ? ResponseEntity.ok(converter.convertToDto(categoria.get()))
				: ResponseEntity.notFound().build();
	}

	@PostMapping
	@ApiOperation(value = "Salvar", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Recurso salvo com sucesso"),
			@ApiResponse(code = 401, message = "Você não está autorizado a salvar o recurso"),
			@ApiResponse(code = 403, message = "É proibido acessar o recurso que você está tentando acessar"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> save(
			@ApiParam(value = "Objeto DTO país para salvar", required = true) @Valid @RequestBody PaisV1Dto dto,
			HttpServletResponse response) {
		Pais entity = new Pais();
		converter.copyToEntity(entity, dto);
		entity = service.save(entity);

		converter.convertToDto(dto, entity);
		return created(entity.getId(), response, dto);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Alterar", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Recurso alterado com sucesso"),
			@ApiResponse(code = 401, message = "Você não está autorizado a alterar o recurso"),
			@ApiResponse(code = 403, message = "É proibido acessar o recurso que você está tentando acessar"),
			@ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> update(@ApiParam(value = "Código do país", required = true) @PathVariable Long id,
			@ApiParam(value = "Objeto DTO país para alterar", required = true) @Valid @RequestBody Pais entity) {
		try {
			Pais pais = service.update(id, entity);
			return ok(pais);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Alterar", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Recurso excluído com sucesso"),
			@ApiResponse(code = 401, message = "Você não está autorizado a excluir o recurso"),
			@ApiResponse(code = 403, message = "É proibido acessar o recurso que você está tentando acessar"),
			@ApiResponse(code = 404, message = "O recurso que você estava tentando excluir não foi encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
