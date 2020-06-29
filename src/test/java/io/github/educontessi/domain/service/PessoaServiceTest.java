package io.github.educontessi.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.educontessi.domain.enums.Status;
import io.github.educontessi.domain.enums.TipoPessoa;
import io.github.educontessi.domain.model.Pessoa;
import io.github.educontessi.domain.repository.PessoaRepository;

/**
 * Classe de teste para entidade {@link PessoaService}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@SpringBootTest
public class PessoaServiceTest {

	private final Long id = 1L;

	@Mock
	private PessoaRepository repository;

	@InjectMocks
	private PessoaService service;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockPessoa();
	}

	private void mockPessoa() {
		when(repository.findById(any())).thenReturn(getPessoa());
	}

	@Test
	public void deveAtualizarPessoa() {
		// Arranjos
		PessoaService serviceSpy = Mockito.spy(service);
		Optional<Pessoa> optional = getPessoa();
		// Pessoa entity = optional.get();
		doReturn(optional).when(serviceSpy).findById(this.id);

		// Execução
		// serviceSpy.update(this.id, entity);

		// Resultados
		// verify(serviceSpy, times(1)).update(this.id, entity);
		verify(serviceSpy, times(1)).findById(this.id);
	}

	private Optional<Pessoa> getPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(this.id);
		pessoa.setNomeRazao("Teste");
		pessoa.setTipoPessoa(TipoPessoa.FISICA);
		pessoa.setStatus(Status.ATIVO);
		return Optional.of(pessoa);
	}

}
