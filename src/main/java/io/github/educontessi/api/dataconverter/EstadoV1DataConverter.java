package io.github.educontessi.api.dataconverter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import io.github.educontessi.api.dto.EstadoV1Dto;
import io.github.educontessi.api.dto.PaisV1Dto;
import io.github.educontessi.domain.helpers.util.ExpandirUtil;
import io.github.educontessi.domain.model.Estado;

@Component
public class EstadoV1DataConverter extends DataConverter<Estado, EstadoV1Dto> {

	@Override
	public void copyToEntity(Estado entity, EstadoV1Dto dto) {
		BeanUtils.copyProperties(dto, entity, getIgnoreProperties());
		entity.setPaisId(getIdOrNull(dto.getPais()));
	}

	@Override
	public EstadoV1Dto convertToDto(EstadoV1Dto dto, Estado entity, String expandir) {
		BeanUtils.copyProperties(entity, dto);
		setPais(dto, entity, expandir);
		return dto;
	}

	public EstadoV1Dto convertToDto(Estado entity, String expandir) {
		return convertToDto(new EstadoV1Dto(), entity, expandir);
	}

	public EstadoV1Dto convertToDto(EstadoV1Dto dto, Estado entity) {
		return convertToDto(dto, entity, null);
	}

	protected void setPais(EstadoV1Dto dto, Estado entity, String expandir) {
		if (ExpandirUtil.contains("pais", expandir)) {
			dto.setPais(new PaisV1DataConverter().convertToDto(entity.getPais()));
		} else {
			PaisV1Dto pais = new PaisV1Dto();
			pais.setId(entity.getPaisId());
			dto.setPais(pais);
		}
	}
}
