package io.github.educontessi.api.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

import io.github.educontessi.api.dataconverter.PaisV1DataConverter;
import io.github.educontessi.api.dto.PaisV1Dto;
import io.github.educontessi.domain.filter.PaisFilter;
import io.github.educontessi.domain.model.Pais;
import io.github.educontessi.domain.service.PaisService;
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

	private PaisService service;
	private PaisV1DataConverter converter;

	@Autowired
	public PaisV1Resource(PaisService service, PaisV1DataConverter converter) {
		this.service = service;
		this.converter = converter;
	}

	@GetMapping
	@ApiOperation(value = "Busca todos", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Recuperou com sucesso todos os recursos"),
			@ApiResponse(code = 401, message = "Você não está autorizado a visualizar o recurso"),
			@ApiResponse(code = 403, message = "É proibido acessar o recurso que você está tentando acessar"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public List<PaisV1Dto> findAll() {
		List<Pais> lista = service.findAll();
		List<PaisV1Dto> listaDto = new ArrayList<>();
		listaDto.addAll(lista.stream().map(x -> converter.convertToDto(x)).collect(Collectors.toList()));
		return listaDto;
	}

	@GetMapping("/pesquisar")
	@ApiOperation(value = "Pesquisar recursos", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Recuperou com sucesso todos os recursos da pesquisa"),
			@ApiResponse(code = 401, message = "Você não está autorizado a visualizar o recurso"),
			@ApiResponse(code = 403, message = "É proibido acessar o recurso que você está tentando acessar"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public Page<PaisV1Dto> pesquisar(PaisFilter filter, Pageable pageable) {
		Page<Pais> lista = service.pesquisar(filter, pageable);
		Page<PaisV1Dto> listaDto = new PageImpl<>(
				lista.getContent().stream().map(x -> converter.convertToDto(x)).collect(Collectors.toList()),
				lista.getPageable(), lista.getTotalElements());
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
		Pais entity = service.findById(id);
		return ResponseEntity.ok(converter.convertToDto(entity));
	}

	@PostMapping
	@ApiOperation(value = "Incluir", response = Iterable.class)
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
			@ApiParam(value = "Objeto DTO país para alterar", required = true) @Valid @RequestBody PaisV1Dto dto) {
		try {
			Pais entity = service.findById(id);
			converter.copyToEntity(entity, dto);
			entity = service.save(entity);

			converter.convertToDto(dto, entity);
			return ok(dto);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Excluir", response = Iterable.class)
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
