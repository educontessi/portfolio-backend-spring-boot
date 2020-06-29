package io.github.educontessi.api.dataconverter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import io.github.educontessi.api.dto.BairroV1Dto;
import io.github.educontessi.api.dto.CidadeV1Dto;
import io.github.educontessi.domain.helpers.util.ExpandirUtil;
import io.github.educontessi.domain.model.Bairro;

@Component
public class BairroV1DataConverter extends DataConverter<Bairro, BairroV1Dto> {

	@Override
	public void copyToEntity(Bairro entity, BairroV1Dto dto) {
		BeanUtils.copyProperties(dto, entity, getIgnoreProperties());
		entity.setCidadeId(getIdOrNull(dto.getCidade()));
		isValid(entity);
	}

	@Override
	public BairroV1Dto convertToDto(BairroV1Dto dto, Bairro entity, String expandir) {
		BeanUtils.copyProperties(entity, dto);
		setCidade(dto, entity, expandir);
		return dto;
	}

	public BairroV1Dto convertToDto(Bairro entity, String expandir) {
		return convertToDto(new BairroV1Dto(), entity, expandir);
	}

	public BairroV1Dto convertToDto(BairroV1Dto dto, Bairro entity) {
		return convertToDto(dto, entity, null);
	}

	protected void setCidade(BairroV1Dto dto, Bairro entity, String expandir) {
		if (ExpandirUtil.contains("cidade", expandir)) {
			dto.setCidade(new CidadeV1DataConverter().convertToDto(entity.getCidade(),
					ExpandirUtil.extrairSubExpadir("cidade", expandir)));
		} else {
			CidadeV1Dto cidade = new CidadeV1Dto();
			cidade.setId(entity.getCidadeId());
			dto.setCidade(cidade);
		}
	}
}
