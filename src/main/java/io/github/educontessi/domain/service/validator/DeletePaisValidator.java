package io.github.educontessi.domain.service.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.educontessi.domain.exception.PaisEmUsoException;
import io.github.educontessi.domain.model.Estado;
import io.github.educontessi.domain.model.Pais;
import io.github.educontessi.domain.repository.EstadoRepository;

@Service
public class DeletePaisValidator implements Validator {

	@Autowired
	private EstadoRepository estadoRepository;

	private Pais pais;

	@Override
	public void validate() {
		List<Estado> estados = estadoRepository.findByPaisId(pais.getId());
		if (estados.size() > 0) {
			throw new PaisEmUsoException(pais.getId());
		}
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}
