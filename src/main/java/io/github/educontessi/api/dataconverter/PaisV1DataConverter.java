package io.github.educontessi.api.dataconverter;

import io.github.educontessi.api.dto.PaisV1Dto;
import io.github.educontessi.domain.model.Pais;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PaisV1DataConverter extends DataConverter<Pais, PaisV1Dto> {

	@Override
	public void copyToEntity(Pais entity, PaisV1Dto dto) {
		BeanUtils.copyProperties(dto, entity, getIgnoreProperties());
		isValid(entity);
	}

	@Override
	public PaisV1Dto convertToDto(PaisV1Dto dto, Pais entity, String expandir) {
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	public PaisV1Dto convertToDto(Pais entity) {
		return convertToDto(new PaisV1Dto(), entity, null);
	}

	public PaisV1Dto convertToDto(PaisV1Dto dto, Pais entity) {
		return convertToDto(dto, entity, null);
	}

	public Pais copyToEntity(PaisV1Dto dto) {
		Pais entity = new Pais();
		copyToEntity(entity, dto);
		return entity;
	}

}
