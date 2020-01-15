package io.github.educontessi.service.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import io.github.educontessi.model.Estado;
import io.github.educontessi.model.Pais;
import io.github.educontessi.repository.EstadoRepository;

@Service
public class DeletePaisValidator implements Validator {

	@Autowired
	private EstadoRepository estadoRepository;

	private Pais pais;

	@Override
	public void validate() {
		List<Estado> estados = estadoRepository.findByPaisId(pais.getId());
		if (estados.size() > 0) {
			throw new DataIntegrityViolationException("País em uso");
		}
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}
