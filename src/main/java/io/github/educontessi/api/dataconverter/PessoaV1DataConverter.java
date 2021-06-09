package io.github.educontessi.api.dataconverter;

import io.github.educontessi.api.dto.PessoaV1Dto;
import io.github.educontessi.domain.helpers.util.ExpandirUtil;
import io.github.educontessi.domain.model.Pessoa;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PessoaV1DataConverter extends DataConverter<Pessoa, PessoaV1Dto> {

	@Override
	public void copyToEntity(Pessoa entity, PessoaV1Dto dto) {
		BeanUtils.copyProperties(dto, entity, getIgnoreProperties());
		entity.setRuaId(getIdOrNull(dto.getRua(), dto.getRuaId()));
		entity.setBairroId(getIdOrNull(dto.getBairro(), dto.getBairroId()));
		entity.setCidadeId(getIdOrNull(dto.getCidade(), dto.getCidadeId()));
		isValid(entity);
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
		if (entity.getRuaId() != null) {
			if (ExpandirUtil.contains("rua", expandir)) {
				dto.setRua(new RuaV1DataConverter().convertToDto(entity.getRua(),
						ExpandirUtil.extrairSubExpadir("rua", expandir)));
				dto.setRuaId(null); // otimizar retorno json
			} else {
				dto.setRuaId(entity.getRuaId());
			}
		}
	}

	protected void setBairro(PessoaV1Dto dto, Pessoa entity, String expandir) {
		if (entity.getBairroId() != null) {
			if (ExpandirUtil.contains("bairro", expandir)) {
				dto.setBairro(new BairroV1DataConverter().convertToDto(entity.getBairro(),
						ExpandirUtil.extrairSubExpadir("bairro", expandir)));
				dto.setBairroId(null); // otimizar retorno json
			} else {
				dto.setBairroId(entity.getBairroId());
			}
		}
	}

	protected void setCidade(PessoaV1Dto dto, Pessoa entity, String expandir) {
		if (entity.getCidadeId() != null) {
			if (ExpandirUtil.contains("cidade", expandir)) {
				dto.setCidade(new CidadeV1DataConverter().convertToDto(entity.getCidade(),
						ExpandirUtil.extrairSubExpadir("cidade", expandir)));
				dto.setCidadeId(null); // otimizar retorno json
			} else {
				dto.setCidadeId(entity.getCidadeId());
			}
		}
	}
}
