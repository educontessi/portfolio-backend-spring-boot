package io.github.educontessi.domain.service.validator;

import io.github.educontessi.domain.exception.negocio.EntidadeEmUsoException;
import io.github.educontessi.domain.model.Bairro;
import io.github.educontessi.domain.model.Cidade;
import io.github.educontessi.domain.model.Pessoa;
import io.github.educontessi.domain.repository.BairroRepository;
import io.github.educontessi.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteCidadeValidator implements Validator {

	@Autowired
	private BairroRepository bairroRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	private Cidade cidade;

	@Override
	public void validate() {
		List<Bairro> bairros = bairroRepository.findByCidadeId(cidade.getId());
		if (!bairros.isEmpty()) {
			throw new EntidadeEmUsoException(cidade.getId());
		}

		List<Pessoa> pessoas = pessoaRepository.findByCidadeId(cidade.getId());
		if (!pessoas.isEmpty()) {
			throw new EntidadeEmUsoException(cidade.getId());
		}
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
