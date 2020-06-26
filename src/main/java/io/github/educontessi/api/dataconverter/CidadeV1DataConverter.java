package io.github.educontessi.api.dataconverter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import io.github.educontessi.api.dto.CidadeV1Dto;
import io.github.educontessi.api.dto.EstadoV1Dto;
import io.github.educontessi.domain.helpers.util.ExpandirUtil;
import io.github.educontessi.domain.model.Cidade;

@Component
public class CidadeV1DataConverter extends DataConverter<Cidade, CidadeV1Dto> {

	@Override
	public void copyToEntity(Cidade entity, CidadeV1Dto dto) {
		BeanUtils.copyProperties(dto, entity, getIgnoreProperties());
		entity.setEstadoId(getIdOrNull(dto.getEstado()));
	}

	@Override
	public CidadeV1Dto convertToDto(CidadeV1Dto dto, Cidade entity, String expandir) {
		BeanUtils.copyProperties(entity, dto);
		setEstado(dto, entity, expandir);
		return dto;
	}

	public CidadeV1Dto convertToDto(Cidade entity, String expandir) {
		return convertToDto(new CidadeV1Dto(), entity, expandir);
	}

	public CidadeV1Dto convertToDto(CidadeV1Dto dto, Cidade entity) {
		return convertToDto(dto, entity, null);
	}

	protected void setEstado(CidadeV1Dto dto, Cidade entity, String expandir) {
		if (ExpandirUtil.contains("estado", expandir)) {
			dto.setEstado(new EstadoV1DataConverter().convertToDto(entity.getEstado(),
					ExpandirUtil.extrairSubExpadir("estado", expandir)));
		} else {
			EstadoV1Dto estado = new EstadoV1Dto();
			estado.setId(entity.getEstadoId());
			dto.setEstado(estado);
		}
	}
}
