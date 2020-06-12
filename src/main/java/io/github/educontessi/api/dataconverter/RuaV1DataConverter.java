package io.github.educontessi.api.dataconverter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import io.github.educontessi.api.dto.CidadeV1Dto;
import io.github.educontessi.api.dto.RuaV1Dto;
import io.github.educontessi.domain.helpers.util.ExpandirUtil;
import io.github.educontessi.domain.model.Rua;

@Component
public class RuaV1DataConverter extends DataConverter<Rua, RuaV1Dto> {

	@Override
	public void copyToEntity(Rua entity, RuaV1Dto dto) {
		BeanUtils.copyProperties(dto, entity, getIgnoreProperties());
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

	protected String[] getIgnoreProperties() {
		List<String> list = new ArrayList<>();
		list.add("id");
		list.add("cidade");
		list.add("created");
		list.add("changed");
		list.add("deletedDate");

		return (String[]) list.toArray(new String[0]);
	}

	protected void setCidade(RuaV1Dto dto, Rua entity, String expandir) {
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
