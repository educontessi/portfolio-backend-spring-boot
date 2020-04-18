package io.github.educontessi.api.dataconverter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import io.github.educontessi.api.dto.PaisV1Dto;
import io.github.educontessi.domain.model.Pais;

@Component
public class PaisV1DataConverter extends DataConverter<Pais, PaisV1Dto> {

	@Override
	public void copyToEntity(Pais entity, PaisV1Dto dto) {
		BeanUtils.copyProperties(dto, entity, getIgnoreProperties());
	}

	@Override
	public PaisV1Dto convertToDto(PaisV1Dto dto, Pais entity) {
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	public PaisV1Dto convertToDto(Pais entity) {
		return convertToDto(new PaisV1Dto(), entity);
	}

	protected String[] getIgnoreProperties() {
		List<String> list = new ArrayList<>();
		list.add("id");
		list.add("created");
		list.add("changed");
		list.add("deletedDate");

		return (String[]) list.toArray(new String[0]);
	}

}
