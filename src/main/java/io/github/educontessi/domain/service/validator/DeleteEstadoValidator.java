package io.github.educontessi.domain.service.validator;

import io.github.educontessi.domain.exception.negocio.EntidadeEmUsoException;
import io.github.educontessi.domain.model.Cidade;
import io.github.educontessi.domain.model.Estado;
import io.github.educontessi.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteEstadoValidator implements Validator {

	@Autowired
	private CidadeRepository cidadeRepository;

	private Estado estado;

	@Override
	public void validate() {
		List<Cidade> cidades = cidadeRepository.findByEstadoId(estado.getId());
		if (!cidades.isEmpty()) {
			throw new EntidadeEmUsoException(estado.getId());
		}
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
