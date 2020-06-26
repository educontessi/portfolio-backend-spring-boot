package io.github.educontessi.api.dataconverter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import io.github.educontessi.api.dto.BairroV1Dto;
import io.github.educontessi.api.dto.CidadeV1Dto;
import io.github.educontessi.api.dto.PessoaV1Dto;
import io.github.educontessi.api.dto.RuaV1Dto;
import io.github.educontessi.domain.helpers.util.ExpandirUtil;
import io.github.educontessi.domain.model.Pessoa;

@Component
public class PessoaV1DataConverter extends DataConverter<Pessoa, PessoaV1Dto> {

	@Override
	public void copyToEntity(Pessoa entity, PessoaV1Dto dto) {
		BeanUtils.copyProperties(dto, entity, getIgnoreProperties());
		entity.setRuaId(getIdOrNull(dto.getRua()));
		entity.setBairroId(getIdOrNull(dto.getBairro()));
		entity.setCidadeId(getIdOrNull(dto.getCidade()));
	}

	@Override
	public PessoaV1Dto convertToDto(PessoaV1Dto dto, Pessoa entity, String expandir) {
		BeanUtils.copyProperties(entity, dto);
		setRua(dto, entity, expandir);
		setBairro(dto, entity, expandir);
		setCidade(dto, entity, expandir);
		return dto;
	}

	public PessoaV1Dto convertToDto(Pessoa entity, String expandir) {
		return convertToDto(new PessoaV1Dto(), entity, expandir);
	}

	public PessoaV1Dto convertToDto(PessoaV1Dto dto, Pessoa entity) {
		return convertToDto(dto, entity, null);
	}

	protected void setRua(PessoaV1Dto dto, Pessoa entity, String expandir) {
		if (ExpandirUtil.contains("rua", expandir)) {
			dto.setRua(new RuaV1DataConverter().convertToDto(entity.getRua(),
					ExpandirUtil.extrairSubExpadir("rua", expandir)));
		} else {
			RuaV1Dto rua = new RuaV1Dto();
			rua.setId(entity.getRuaId());
			dto.setRua(rua);
		}
	}

	protected void setBairro(PessoaV1Dto dto, Pessoa entity, String expandir) {
		if (ExpandirUtil.contains("bairro", expandir)) {
			dto.setBairro(new BairroV1DataConverter().convertToDto(entity.getBairro(),
					ExpandirUtil.extrairSubExpadir("bairro", expandir)));
		} else {
			BairroV1Dto bairro = new BairroV1Dto();
			bairro.setId(entity.getBairroId());
			dto.setBairro(bairro);
		}
	}

	protected void setCidade(PessoaV1Dto dto, Pessoa entity, String expandir) {
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
