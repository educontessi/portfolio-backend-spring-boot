package io.github.educontessi.domain.service.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.educontessi.domain.exception.CidadeEmUsoException;
import io.github.educontessi.domain.model.Bairro;
import io.github.educontessi.domain.model.Cidade;
import io.github.educontessi.domain.model.Pessoa;
import io.github.educontessi.domain.repository.BairroRepository;
import io.github.educontessi.domain.repository.PessoaRepository;

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
		if (bairros.size() > 0) {
			throw new CidadeEmUsoException(cidade.getId());
		}

		List<Pessoa> pessoas = pessoaRepository.findByCidadeId(cidade.getId());
		if (pessoas.size() > 0) {
			throw new CidadeEmUsoException(cidade.getId());
		}
	}

	public void setCidade(Cidade Cidade) {
		this.cidade = Cidade;
	}

}
