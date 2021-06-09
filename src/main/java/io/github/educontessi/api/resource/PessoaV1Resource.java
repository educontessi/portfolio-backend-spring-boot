package io.github.educontessi.api.resource;

import io.github.educontessi.api.dataconverter.PessoaV1DataConverter;
import io.github.educontessi.api.dto.PessoaV1Dto;
import io.github.educontessi.domain.filter.PessoaFilter;
import io.github.educontessi.domain.model.Pessoa;
import io.github.educontessi.domain.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Endpoints para {@link Pessoa}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@RestController
@RequestMapping("/v1/pessoas")
@Api(produces = "application/json", value = "PessoaV1Resource")
public class PessoaV1Resource extends BaseResource {

	@Autowired
	private PessoaService service;

	@Autowired
	private PessoaV1DataConverter converter;

	@GetMapping
	@ApiOperation(value = "Busca todos", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Recuperou com sucesso todos os recursos"),
			@ApiResponse(code = 401, message = "Você não está autorizado a visualizar o recurso"),
			@ApiResponse(code = 403, message = "É proibido acessar o recurso que você está tentando acessar"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public List<PessoaV1Dto> findAll(String expandir) {
		List<Pessoa> lista = service.findAll();
		return lista.stream().map(x -> converter.convertToDto(x, expandir)).collect(Collectors.toList());
	}

	@GetMapping("pesquisar")
	@ApiOperation(value = "Pesquisar recursos", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Recuperou com sucesso todos os recursos da pesquisa"),
			@ApiResponse(code = 401, message = "Você não está autorizado a visualizar o recurso"),
			@ApiResponse(code = 403, message = "É proibido acessar o recurso que você está tentando acessar"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public Page<PessoaV1Dto> search(PessoaFilter filter, Pageable pageable, String expandir) {
		Page<Pessoa> lista = service.search(filter, pageable);
		return new PageImpl<>(
				lista.getContent().stream().map(x -> converter.convertToDto(x, expandir)).collect(Collectors.toList()),
				lista.getPageable(), lista.getTotalElements());
	}

	@GetMapping("/cpf-cnpj/{cpfCnpj}")
	@ApiOperation(value = "Busca por CPF / CNPJ", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Recurso recuperado com sucesso"),
			@ApiResponse(code = 401, message = "Você não está autorizado a visualizar o recurso"),
			@ApiResponse(code = 403, message = "É proibido acessar o recurso que você está tentando acessar"),
			@ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<PessoaV1Dto> findByCpfCnpj(@PathVariable String cpfCnpj, String expandir) {
		Pessoa entity = service.findByCpfCnpj(cpfCnpj);
		return ResponseEntity.ok(converter.convertToDto(entity, expandir));
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Busca por ID", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Recurso recuperado com sucesso"),
			@ApiResponse(code = 401, message = "Você não está autorizado a visualizar o recurso"),
			@ApiResponse(code = 403, message = "É proibido acessar o recurso que você está tentando acessar"),
			@ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<PessoaV1Dto> findById(@PathVariable Long id, String expandir) {
		Pessoa entity = service.findById(id);
		return ResponseEntity.ok(converter.convertToDto(entity, expandir));
	}

	@PostMapping
	@ApiOperation(value = "Incluir", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Recurso salvo com sucesso"),
			@ApiResponse(code = 401, message = "Você não está autorizado a salvar o recurso"),
			@ApiResponse(code = 403, message = "É proibido acessar o recurso que você está tentando acessar"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> save(@Valid @RequestBody PessoaV1Dto dto, HttpServletResponse response) {
		Pessoa entity = new Pessoa();
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
	public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody PessoaV1Dto dto) {
		try {
			Pessoa entity = service.findById(id);
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
