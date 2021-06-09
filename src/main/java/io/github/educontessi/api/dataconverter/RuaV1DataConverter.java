package io.github.educontessi.api.dataconverter;

import io.github.educontessi.api.dto.RuaV1Dto;
import io.github.educontessi.domain.helpers.util.ExpandirUtil;
import io.github.educontessi.domain.model.Rua;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RuaV1DataConverter extends DataConverter<Rua, RuaV1Dto> {

	@Override
	public void copyToEntity(Rua entity, RuaV1Dto dto) {
		BeanUtils.copyProperties(dto, entity, getIgnoreProperties());
		entity.setCidadeId(getIdOrNull(dto.getCidade(), dto.getCidadeId()));
		isValid(entity);
	}

	@Override
	public RuaV1Dto convertToDto(RuaV1Dto dto, Rua entity, String expandir) {
		BeanUtils.copyProperties(entity, dto);
		setCidade(dto, entity, expandir);
		return dto;
	}

	public RuaV1Dto convertToDto(Rua entity, String expandir) {
		return convertToDto(new RuaV1Dto(), entity, expandir);
	}

	public RuaV1Dto convertToDto(RuaV1Dto dto, Rua entity) {
		return convertToDto(dto, entity, null);
	}

	protected void setCidade(RuaV1Dto dto, Rua entity, String expandir) {
		if (ExpandirUtil.contains("cidade", expandir)) {
			dto.setCidade(new CidadeV1DataConverter().convertToDto(entity.getCidade(),
					ExpandirUtil.extrairSubExpadir("cidade", expandir)));
			dto.setCidadeId(null); // otimizar retorno json
		} else {
			dto.setCidadeId(entity.getCidadeId());
		}
	}
}
