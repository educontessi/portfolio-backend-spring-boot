package io.github.educontessi.api.dataconverter;

import io.github.educontessi.api.dto.BaseDto;
import io.github.educontessi.domain.exception.negocio.DtoInvalidoException;
import io.github.educontessi.domain.model.BaseEntity;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class DataConverter<E extends BaseEntity, D extends BaseDto> {

	/**
	 * Copiar os dados do DTO para a entidade
	 * 
	 * @param entity instance da entidade que vai receber os dados
	 * @param dto    objeto que contém os dados
	 */
	public abstract void copyToEntity(E entity, D dto);

	/**
	 * Copiar os dados da entidade para o DTO
	 * 
	 * @param dto    new DTO
	 * @param entity entidade que contém os dados
	 * @return dto
	 */
	public abstract D convertToDto(D dto, E entity, String expandir);

	protected Long getIdOrNull(BaseDto dto) {
		return dto == null ? null : dto.getId();
	}

	protected Long getIdOrNull(BaseDto dto, Long idPrioritario) {
		Long retorno = null;
		if (idPrioritario != null ) {
			retorno = idPrioritario;
		} else if(dto != null) {
			retorno = dto.getId();
		}
		return retorno;
	}

	protected String[] getIgnoreProperties() {
		List<String> list = new ArrayList<>();
		list.add("id");
		list.add("created");
		list.add("changed");
		list.add("deletedDate");

		return list.toArray(new String[0]);
	}

	protected void isValid(E entity) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<E>> violacoes = validator.validate(entity);
		if (!violacoes.isEmpty()) {
			StringBuilder builder = new StringBuilder();
			for (ConstraintViolation<E> violacao : violacoes) {
				builder.append("|");
				builder.append(violacao.getMessage().replace("{0}", violacao.getPropertyPath().toString()));
			}
			throw new DtoInvalidoException(builder);
		}
	}
}
