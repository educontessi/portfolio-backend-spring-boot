package io.github.educontessi.api.dataconverter;

import org.springframework.stereotype.Component;

import io.github.educontessi.api.dto.BairroV1Dto;
import io.github.educontessi.api.dto.CidadeV1Dto;
import io.github.educontessi.api.dto.EstadoV1Dto;
import io.github.educontessi.api.dto.RuaV1Dto;
import io.github.educontessi.api.dto.ViaCepV1Dto;
import io.github.educontessi.domain.exception.negocio.NegocioException;
import io.github.educontessi.domain.model.ViaCepResposta;

@Component
public class ViaCepV1DataConverter extends DataConverter<ViaCepResposta, ViaCepV1Dto> {

	@Override
	public void copyToEntity(ViaCepResposta entity, ViaCepV1Dto dto) {
		throw new NegocioException("Conversão inválida");
	}

	@Override
	public ViaCepV1Dto convertToDto(ViaCepV1Dto dto, ViaCepResposta entity, String expandir) {
		dto.setCep(entity.getCep());

		if (entity.getRua() != null) {
			RuaV1Dto rua = new RuaV1Dto();
			rua.setId(entity.getRua().getId());
			rua.setNome(entity.getRua().getNome());
			dto.setRua(rua);
		}

		if (entity.getBairro() != null) {
			BairroV1Dto bairro = new BairroV1Dto();
			bairro.setId(entity.getBairro().getId());
			bairro.setNome(entity.getBairro().getNome());
			dto.setBairro(bairro);
		}

		if (entity.getCidade() != null) {
			CidadeV1Dto cidade = new CidadeV1Dto();
			cidade.setId(entity.getCidade().getId());
			cidade.setNome(entity.getCidade().getNome());
			dto.setCidade(cidade);
		}

		if (entity.getEstado() != null) {
			EstadoV1Dto estado = new EstadoV1Dto();
			estado.setId(entity.getEstado().getId());
			estado.setNome(entity.getEstado().getNome());
			dto.setEstado(estado);
		}

		return dto;
	}

	public ViaCepV1Dto convertToDto(ViaCepResposta entity) {
		return convertToDto(new ViaCepV1Dto(), entity, null);
	}

}
