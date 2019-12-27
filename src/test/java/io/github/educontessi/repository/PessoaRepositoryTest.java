package io.github.educontessi.repository;

import static io.github.educontessi.helpers.util.FuncoesString.removeMascaraDeNumeros;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.educontessi.model.Pessoa;
import io.github.educontessi.model.enumeracoes.Status;
import io.github.educontessi.model.enumeracoes.TipoPessoa;

/**
 * Classe de teste para entidade {@link PessoaRepository}
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@SpringBootTest
public class PessoaRepositoryTest {

	private final String CPF = "34654886524";
	private final String CNPJ = "05702348000144";

	@Autowired
	private PessoaRepository repository;

	@BeforeEach
	public void setUp() {
	}

	@Test
	public void deveBuscarPessoaPorCpf() {
		// Arranjos
		Pessoa entity = getPessoaFisica();
		repository.save(entity);

		// Execução
		Optional<Pessoa> found = repository.findByCpfCnpj(removeMascaraDeNumeros(entity.getCpfCnpj()));
		Pessoa pessoa = found.get();

		// Resultados
		assertNotNull(pessoa);
		assertEquals(pessoa.getId(), entity.getId());
		assertEquals(pessoa.getNomeRazao(), entity.getNomeRazao());
		assertEquals(pessoa.getCpfCnpj(), entity.getCpfCnpj());

		// Final
		repository.delete(entity);
	}

	@Test
	public void deveBuscarPessoaPorCnpj() {
		// Arranjos
		Pessoa entity = getPessoaJuridica();
		repository.save(entity);

		// Execução
		Optional<Pessoa> found = repository.findByCpfCnpj(removeMascaraDeNumeros(entity.getCpfCnpj()));
		Pessoa pessoa = found.get();

		// Resultados
		assertNotNull(pessoa);
		assertEquals(pessoa.getId(), entity.getId());
		assertEquals(pessoa.getNomeRazao(), entity.getNomeRazao());
		assertEquals(pessoa.getCpfCnpj(), entity.getCpfCnpj());

		// Final
		repository.delete(entity);
	}

	private Pessoa getPessoaFisica() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNomeRazao("Integration Test");
		pessoa.setTipoPessoa(TipoPessoa.FISICA);
		pessoa.setStatus(Status.ATIVO);
		pessoa.setCpfCnpj(this.CPF);
		pessoa.setDataCadastro(LocalDate.now());
		pessoa.setDataNascimento(LocalDate.now());
		return pessoa;
	}

	private Pessoa getPessoaJuridica() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNomeRazao("Integration Test");
		pessoa.setTipoPessoa(TipoPessoa.JURIDICA);
		pessoa.setStatus(Status.ATIVO);
		pessoa.setCpfCnpj(this.CNPJ);
		pessoa.setDataCadastro(LocalDate.now());
		pessoa.setDataNascimento(LocalDate.now());
		return pessoa;
	}

}
