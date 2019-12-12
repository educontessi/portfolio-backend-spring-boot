package io.github.educontessi.dataconverter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import io.github.educontessi.dto.PaisV1Dto;
import io.github.educontessi.model.Pais;

@Component
public class PaisV1DataConverter extends DataConverter<Pais, PaisV1Dto> {

	@Override
	public void copyToEntity(Pais entity, PaisV1Dto dto) {
		BeanUtils.copyProperties(dto, entity);
	}

	@Override
	public PaisV1Dto convertToDto(PaisV1Dto dto, Pais entity) {
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	public PaisV1Dto convertToDto(Pais entity) {
		return convertToDto(new PaisV1Dto(), entity);
	}
}
