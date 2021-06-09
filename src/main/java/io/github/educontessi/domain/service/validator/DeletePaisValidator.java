package io.github.educontessi.domain.service.validator;

import io.github.educontessi.domain.exception.negocio.EntidadeEmUsoException;
import io.github.educontessi.domain.model.Estado;
import io.github.educontessi.domain.model.Pais;
import io.github.educontessi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeletePaisValidator implements Validator {

	@Autowired
	private EstadoRepository estadoRepository;

	private Pais pais;

	@Override
	public void validate() {
		List<Estado> estados = estadoRepository.findByPaisId(pais.getId());
		if (!estados.isEmpty()) {
			throw new EntidadeEmUsoException(pais.getId());
		}
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}
