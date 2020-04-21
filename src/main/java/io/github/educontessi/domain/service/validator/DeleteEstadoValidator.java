package io.github.educontessi.domain.service.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.educontessi.domain.exception.EstadoEmUsoException;
import io.github.educontessi.domain.model.Cidade;
import io.github.educontessi.domain.model.Estado;
import io.github.educontessi.domain.repository.CidadeRepository;

@Service
public class DeleteEstadoValidator implements Validator {

	@Autowired
	private CidadeRepository cidadeRepository;

	private Estado estado;

	@Override
	public void validate() {
		List<Cidade> cidades = cidadeRepository.findByEstadoId(estado.getId());
		if (cidades.size() > 0) {
			throw new EstadoEmUsoException(estado.getId());
		}
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
